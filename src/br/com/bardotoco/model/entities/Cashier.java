package br.com.bardotoco.model.entities;

public class Cashier {

    private Status status;
    private static Cashier INSTANCE;

    private Cashier(){
        this.status = Status.CLOSED;
    }

    public synchronized static Cashier getInstance(){
        if(INSTANCE == null) INSTANCE = new Cashier();
        return INSTANCE;
    }

    public Status getStatus() {
        return status;
    }

    public void openCashier() {
        status = Status.OPENED;
    }

    public void closeCashier() {
        status = Status.CLOSED;
    }

}
