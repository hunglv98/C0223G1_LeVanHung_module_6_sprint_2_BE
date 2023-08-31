package com.example.module_6_sprint_2.model;

import javax.persistence.*;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAccount;
    private String username;
    private String password;
    private boolean flagDelete;
    @ManyToOne
    @JoinColumn(name = "id_role")
    private Role role;

    public Account() {
    }

    public Account(int idAccount, String username, String password, boolean flagDelete, Role role) {
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
