package com.bardotoco.domain.entities.cashier;

public enum CashierStatus {

    OPENED("Aberto"),
    CLOSED("Fechado");

    private String label;

    CashierStatus(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
