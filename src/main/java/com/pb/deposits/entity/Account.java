package com.pb.deposits.entity;

import javax.persistence.*;

@Entity
public class Account {

    @Id
    private String accountNumber;

    private String bankName;
    private String country;
    private int amount;
    private int profitability;
    private int timeConstraints;
    private int typeDeposit;

    @ManyToOne
    @JoinColumn(name = "customer", nullable = false)
    private Customer customer;

    public Account() {}

    public Account(String accountNumber, String bankName, String country, int amount, int profitability, int timeConstraints, int typeDeposit, Customer customer) {
        this.accountNumber = accountNumber;
        this.bankName = bankName;
        this.country = country;
        this.amount = amount;
        this.profitability = profitability;
        this.timeConstraints = timeConstraints;
        this.typeDeposit = typeDeposit;
        this.customer = customer;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getProfitability() {
        return profitability;
    }

    public void setProfitability(int profitability) {
        this.profitability = profitability;
    }

    public int getTimeConstraints() {
        return timeConstraints;
    }

    public void setTimeConstraints(int timeConstraints) {
        this.timeConstraints = timeConstraints;
    }

    public int getTypeDeposit() {
        return typeDeposit;
    }

    public void setTypeDeposit(int typeDeposit) {
        this.typeDeposit = typeDeposit;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
