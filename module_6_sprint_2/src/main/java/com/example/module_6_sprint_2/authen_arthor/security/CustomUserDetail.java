package com.example.module_6_sprint_2.authen_arthor.security;

import com.example.module_6_sprint_2.model.Account;
import com.example.module_6_sprint_2.model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetail implements UserDetails {
    private int idAccount;
    private String username;
    private String password;
    private boolean flagDelete;
    private Collection<? extends GrantedAuthority> authorities;

    //Từ thông tin account chuyển tất cả qua CustomUserDetail
    public static CustomUserDetail MapAccountToUserDetail(Account account){
        //Lấy quyền cho đối tượng account
        List<GrantedAuthority> listAuthority = new ArrayList<>();
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(account.getRole().getNameRole());
        listAuthority.add(simpleGrantedAuthority);
        return new CustomUserDetail(
                account.getIdAccount(),
                account.getUsername(),
                account.getPassword(),
                account.isFlagDelete(),
                listAuthority
        );
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public CustomUserDetail() {
    }

    public CustomUserDetail(int idAccount, String username, String password, boolean flagDelete, Collection<? extends GrantedAuthority> authorities) {
        this.idAccount = idAccount;
        this.username = username;
        this.password = password;
        this.flagDelete = flagDelete;
        this.authorities = authorities;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isFlagDelete() {
        return flagDelete;
    }

    public void setFlagDelete(boolean flagDelete) {
        this.flagDelete = flagDelete;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
