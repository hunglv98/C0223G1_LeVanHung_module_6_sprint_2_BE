package com.example.module_6_sprint_2.repository;

import com.example.module_6_sprint_2.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepo extends JpaRepository<Customer,Integer> {
    Customer getCustomerByAccount_Username(String username);

    Customer getCustomerByEmailCustomer(String email);

}
