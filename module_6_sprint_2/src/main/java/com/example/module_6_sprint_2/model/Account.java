package com.example.module_6_sprint_2.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Account{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAccount;
    private String username;
    private boolean flagDelete;
    private String password;
    @ManyToOne
    @JoinColumn(name = "id_role")
    private Role role;

    public Account() {
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Account(Integer idAccount, String username, boolean flagDelete, String password, Role role) {
        this.idAccount = idAccount;
        this.username = username;
        this.flagDelete = flagDelete;
        this.password = password;
        this.role = role;
    }

    public Integer getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Integer idAccount) {
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
