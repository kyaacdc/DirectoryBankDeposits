package com.pb.deposits.ServerSideApp.entity;

import com.pb.deposits.ServerSideApp.entity.enums.TypeDeposit;
import com.pb.deposits.ServerSideApp.entity.validators.Name;
import com.pb.deposits.ServerSideApp.entity.validators.Number;

import javax.persistence.*;

@Entity
public class Account {

    @Id
    @Number
    private String id;

    @Name
    private String bankName;

    @Name
    private String country;

    private int amount;
    private int profitability;
    private int timeConstraints;

    private TypeDeposit typeDeposit;

    @ManyToOne
    @JoinColumn(name = "depositor", nullable = false)
    private Depositor depositor;

    public Account() {}

    public Account(String id, String bankName, String country, int amount, int profitability,
                   int timeConstraints, TypeDeposit typeDeposit, Depositor depositor) {
        this.id = id;
        this.bankName = bankName;
        this.country = country;
        this.amount = amount;
        this.profitability = profitability;
        this.timeConstraints = timeConstraints;
        this.typeDeposit = typeDeposit;
        this.depositor = depositor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public TypeDeposit getTypeDeposit() {
        return typeDeposit;
    }

    public void setTypeDeposit(TypeDeposit typeDeposit) {
        this.typeDeposit = typeDeposit;
    }

    public Depositor getDepositor() {
        return depositor;
    }

    public void setDepositor(Depositor depositor) {
        this.depositor = depositor;
    }
}
