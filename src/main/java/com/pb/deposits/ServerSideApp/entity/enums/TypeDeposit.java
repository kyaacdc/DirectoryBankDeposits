package com.pb.deposits.ServerSideApp.entity.enums;

public enum TypeDeposit {
    posterestante(1), urgent(2), rated(3), cumulative(4), savings(5), metallic(6);

    private int value;

    TypeDeposit(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
