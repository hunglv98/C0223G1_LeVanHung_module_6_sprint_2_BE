package com.example.module_6_sprint_2.model;

import javax.persistence.*;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEmployee;
    private String nameEmployee;
    private String identityCardEmployee;
    private String telEmployee;
    private String emailEmployee;
    private boolean flagDelete;
    @OneToOne
    @JoinColumn(name = "id_account")
    private Account account;

    public Employee() {
    }

    public Employee(int idEmployee, String nameEmployee, String identityCardEmployee, String telEmployee,
                    String emailEmployee, boolean flagDelete, Account account) {
        this.idEmployee = idEmployee;
        this.nameEmployee = nameEmployee;
        this.identityCardEmployee = identityCardEmployee;
        this.telEmployee = telEmployee;
        this.emailEmployee = emailEmployee;
        this.flagDelete = flagDelete;
        this.account = account;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getNameEmployee() {
        return nameEmployee;
    }

    public void setNameEmployee(String nameEmployee) {
        this.nameEmployee = nameEmployee;
    }

    public String getIdentityCardEmployee() {
        return identityCardEmployee;
    }

    public void setIdentityCardEmployee(String identityCardEmployee) {
        this.identityCardEmployee = identityCardEmployee;
    }

    public String getTelEmployee() {
        return telEmployee;
    }

    public void setTelEmployee(String telEmployee) {
        this.telEmployee = telEmployee;
    }

    public String getEmailEmployee() {
        return emailEmployee;
    }

    public void setEmailEmployee(String emailEmployee) {
        this.emailEmployee = emailEmployee;
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
