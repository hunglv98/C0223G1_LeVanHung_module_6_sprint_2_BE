package com.example.module_6_sprint_2.service.impl;

import com.example.module_6_sprint_2.model.Account;
import com.example.module_6_sprint_2.repository.IAccountRepo;
import com.example.module_6_sprint_2.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private IAccountRepo accountRepo;
    @Autowired
    private PasswordEncoder bcryptEncoder;
    @Override
    public Account save(Account account) {
        account.setPassword(bcryptEncoder.encode(account.getPassword()));
        return accountRepo.save(account);
    }

    @Override
    public Account findByUsername(String username) {
        return accountRepo.findByUsername(username);
    }

    @Override
    public boolean existsAccountByUsername(String username) {
        return accountRepo.existsAccountByUsername(username);
    }
}
