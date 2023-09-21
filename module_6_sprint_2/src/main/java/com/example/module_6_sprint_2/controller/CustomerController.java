package com.example.module_6_sprint_2.controller;

import com.example.module_6_sprint_2.model.Customer;
import com.example.module_6_sprint_2.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @GetMapping("/{username}")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<?> getCustomerByUsername(@PathVariable String username) {
        Customer customer = customerService.getCustomerByAccount_Username(username);
        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> getCustomerByEmail(@RequestParam String email) {
        Customer customer = customerService.getCustomerByEmailCustomer(email);
        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
}

