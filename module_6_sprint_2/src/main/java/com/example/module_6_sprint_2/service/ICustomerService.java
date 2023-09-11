package com.example.module_6_sprint_2.service;

import com.example.module_6_sprint_2.model.Customer;

public interface ICustomerService {
    void save(Customer customer);
    Customer getCustomerByAccount_Username(String username);
}
