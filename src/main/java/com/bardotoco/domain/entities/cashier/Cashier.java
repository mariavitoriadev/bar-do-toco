package com.bardotoco.domain.entities.cashier;

public class Cashier {

    private CashierStatus status;
    private static Cashier INSTANCE;

    private Cashier(){
        status = CashierStatus.CLOSED;
    }

    public synchronized static Cashier getInstance(){
        if(INSTANCE == null) INSTANCE = new Cashier();
        return INSTANCE;
    }

    public void openCashier() {
        this.status = CashierStatus.OPENED;
    }

    public void closeCashier() {
        this.status = CashierStatus.CLOSED;
    }

    public CashierStatus getCashierStatus() {
        return status;
    }

    public boolean isCashierOpened() {
        if(status == CashierStatus.OPENED)
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "Cashier{" +
                "status=" + status +
                '}';
    }
}
