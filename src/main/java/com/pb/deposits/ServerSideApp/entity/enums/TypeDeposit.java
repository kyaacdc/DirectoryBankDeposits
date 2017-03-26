package com.pb.deposits.ServerSideApp.entity.enums;

public enum TypeDeposit {
    PosteRestante(1), Urgent(2), Rated(3), Cumulative(4), Savings(5), Metallic(6);

    private int value;

    TypeDeposit(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
