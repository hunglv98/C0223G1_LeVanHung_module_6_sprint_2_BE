package com.example.module_6_sprint_2.controller;

import com.example.module_6_sprint_2.authen_arthor.jwt.JwtTokenProvider;
import com.example.module_6_sprint_2.authen_arthor.payload.request.LoginRequest;
import com.example.module_6_sprint_2.authen_arthor.payload.request.SignupRequest;
import com.example.module_6_sprint_2.authen_arthor.payload.response.JwtResponse;
import com.example.module_6_sprint_2.authen_arthor.security.CustomUserDetail;
import com.example.module_6_sprint_2.model.Account;
import com.example.module_6_sprint_2.model.Customer;
import com.example.module_6_sprint_2.model.Role;
import com.example.module_6_sprint_2.service.IAccountService;
import com.example.module_6_sprint_2.service.IRoleService;
import com.example.module_6_sprint_2.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("")
public class AccountController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    private IAccountService accountService;
    @Autowired
    private IRoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ICustomerService customerService;

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody SignupRequest signupRequest){
        if (accountService.existsAccountByUsername(signupRequest.getUsername())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Account account = new Account();
        account.setUsername(signupRequest.getUsername());
        account.setPassword(signupRequest.getPassword());
        Role role = roleService.findByIdRole(1);
        account.setRole(role);
        accountService.save(account);
        Customer customer = new Customer();
        customer.setNameCustomer(signupRequest.getNameCustomer());
        customer.setEmailCustomer(signupRequest.getEmailCustomer());
        customer.setTelCustomer(signupRequest.getTelCustomer());
        customer.setIdentityCardCustomer(signupRequest.getIdentityCardCustomer());
        customer.setAccount(account);
        customerService.save(customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();
        //Sinh ra JWT tra ve client
        String jwt = tokenProvider.generateToken(customUserDetail);
        List<String> listRole = customUserDetail.getAuthorities().stream()
                .map(item -> item.getAuthority()).collect(Collectors.toList());
        return new ResponseEntity<>(new JwtResponse(jwt, customUserDetail.getUsername(), listRole),HttpStatus.OK);
    }
}
