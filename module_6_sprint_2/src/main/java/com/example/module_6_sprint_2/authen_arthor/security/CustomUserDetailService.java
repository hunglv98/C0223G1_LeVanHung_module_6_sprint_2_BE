package com.example.module_6_sprint_2.authen_arthor.security;

import com.example.module_6_sprint_2.model.Account;
import com.example.module_6_sprint_2.repository.IAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private IAccountRepo accountRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepo.findByUsername(username);
        if(account == null){
            throw new UsernameNotFoundException("Not Found");
        }
        return CustomUserDetail.MapAccountToUserDetail(account);
    }
}
