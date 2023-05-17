package br.com.bardotoco.model.entities;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Account {
    private int id;
    private LocalDateTime openingTime;
    private LocalDateTime closingTime;
    private double totalAmount;
    private double paidAmount;
    private ArrayList<Timestamp> itemsForPayment;
    private int paymentAmount;
    private ClientTable clientTable;

    public Account(int id, LocalDateTime openingTime, LocalDateTime closingTime, double totalAmount, double paidAmount) {
        this.id = id;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.totalAmount = totalAmount;
        this.paidAmount = paidAmount;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getOpeningTime() {
        return openingTime;
    }

    public LocalDateTime getClosingTime() {
        return closingTime;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public ArrayList<Timestamp> getItemsForPayment() {
        return itemsForPayment;
    }

    public int getPaymentAmount() {
        return paymentAmount;
    }

    public ClientTable getClientTable() {
        return clientTable;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOpeningTime(LocalDateTime openingTime) {
        this.openingTime = openingTime;
    }

    public void setClosingTime(LocalDateTime closingTime) {
        this.closingTime = closingTime;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setPaidAmount(double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public void setItemsForPayment(ArrayList<Timestamp> itemsForPayment) {
        this.itemsForPayment = itemsForPayment;
    }

    public void setPaymentAmount(int paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public void setClientTable(ClientTable clientTable) {
        this.clientTable = clientTable;
    }

}
