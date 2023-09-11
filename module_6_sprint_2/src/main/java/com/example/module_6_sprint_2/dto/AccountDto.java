package com.example.module_6_sprint_2.dto;

import com.example.module_6_sprint_2.model.Role;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class AccountDto implements Serializable {
    private static final long serialVersionUID = 5926468583005150707L;
    private int idAccount;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private boolean flagDelete;
    private Role role;

    public AccountDto() {
    }

    public AccountDto(int idAccount, String username, String password, boolean flagDelete, Role role) {
        this.idAccount = idAccount;
        this.username = username;
        this.password = password;
        this.flagDelete = flagDelete;
        this.role = role;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
