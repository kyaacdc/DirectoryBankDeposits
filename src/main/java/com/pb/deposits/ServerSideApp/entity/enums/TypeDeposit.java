package com.pb.deposits.ServerSideApp.entity.enums;

public enum TypeDeposit {
    posterestante(0), urgent(1), rated(2), cumulative(3), savings(4), metallic(5);

    private int value;

    TypeDeposit(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
