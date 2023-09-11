package com.example.module_6_sprint_2.repository;

import com.example.module_6_sprint_2.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface IAccountRepo extends JpaRepository<Account,Integer> {
    Account findByUsername(String username);
    boolean existsAccountByUsername(String username);
}
