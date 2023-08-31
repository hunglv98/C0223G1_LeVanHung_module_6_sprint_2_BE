package com.example.module_6_sprint_2.model;

import javax.persistence.*;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCustomer;
    private String nameCustomer;
    private String identityCardCustomer;
    private String telCustomer;
    private String emailCustomer;
    private boolean flagDelete;
    @OneToOne
    @JoinColumn(name = "id_account")
    private Account account;

    public Customer() {
    }

    public Customer(int idCustomer, String nameCustomer, String identityCardCustomer, String telCustomer,
                    String emailCustomer, boolean flagDelete, Account account) {
        this.idCustomer = idCustomer;
        this.nameCustomer = nameCustomer;
        this.identityCardCustomer = identityCardCustomer;
        this.telCustomer = telCustomer;
        this.emailCustomer = emailCustomer;
        this.flagDelete = flagDelete;
        this.account = account;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
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

    public boolean isFlagDelete() {
        return flagDelete;
    }

    public void setFlagDelete(boolean flagDelete) {
        this.flagDelete = flagDelete;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
