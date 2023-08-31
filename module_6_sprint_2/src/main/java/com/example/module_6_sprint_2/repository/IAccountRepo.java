package com.example.module_6_sprint_2.repository;

import com.example.module_6_sprint_2.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountRepo extends JpaRepository<Account,Integer> {
}
