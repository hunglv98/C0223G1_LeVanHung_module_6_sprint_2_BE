package com.example.module_6_sprint_2.service;

import com.example.module_6_sprint_2.model.Account;

public interface IAccountService {
    Account save(Account account);
    Account findByUsername(String username);

    boolean existsAccountByUsername(String username);

}
