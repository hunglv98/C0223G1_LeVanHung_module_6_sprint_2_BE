package com.example.module_6_sprint_2.service.impl;

import com.example.module_6_sprint_2.model.Customer;
import com.example.module_6_sprint_2.repository.ICustomerRepo;
import com.example.module_6_sprint_2.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerRepo customerRepo;
    @Override
    public void save(Customer customer) {
        customerRepo.save(customer);
    }

    @Override
    public Customer getCustomerByAccount_Username(String username) {
        return customerRepo.getCustomerByAccount_Username(username);
    }
}
