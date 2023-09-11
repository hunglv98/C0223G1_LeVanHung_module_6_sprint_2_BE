package com.example.module_6_sprint_2.authen_arthor.payload.request;

import com.example.module_6_sprint_2.model.Role;

public class SignupRequest {
    private String username;
    private String password;
    private boolean flagDelete;
    private Role role;

    private String nameCustomer;
    private String identityCardCustomer;
    private String telCustomer;
    private String emailCustomer;

    public SignupRequest() {
    }

    public SignupRequest(String username, String password, boolean flagDelete, Role role, String nameCustomer, String identityCardCustomer, String telCustomer, String emailCustomer) {
        this.username = username;
        this.password = password;
        this.flagDelete = flagDelete;
        this.role = role;
        this.nameCustomer = nameCustomer;
        this.identityCardCustomer = identityCardCustomer;
        this.telCustomer = telCustomer;
        this.emailCustomer = emailCustomer;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public String getIdentityCardCustomer() {
        return identityCardCustomer;
    }

    public void setIdentityCardCustomer(String identityCardCustomer) {
        this.identityCardCustomer = identityCardCustomer;
    }

    public String getTelCustomer() {
        return telCustomer;
    }

    public void setTelCustomer(String telCustomer) {
        this.telCustomer = telCustomer;
    }

    public String getEmailCustomer() {
        return emailCustomer;
    }

    public void setEmailCustomer(String emailCustomer) {
        this.emailCustomer = emailCustomer;
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
