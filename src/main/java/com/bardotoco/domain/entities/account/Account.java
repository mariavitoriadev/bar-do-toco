package com.bardotoco.domain.entities.account;
import com.bardotoco.domain.entities.clientTable.ClientTable;

import java.time.LocalDateTime;

public class Account {
    private Integer id;
    private LocalDateTime openingTime;
    private LocalDateTime closingTime;
    private double totalAmount = 0;
    private double paidAmount = 0;
    private double paidByValueAmount = 0 ;
    private double paidBySaleItemAmount = 0;
    private ClientTable clientTable;
    private Integer clientTableId;

    public Account(LocalDateTime openingTime, ClientTable clientTable) {
        this.openingTime = openingTime;
        this.clientTable = clientTable;
        this.clientTableId = clientTable.getId();
    }

    public Account() {
        this(0, LocalDateTime.now(), null, 0.0, 0.0, 0.0, 0.0);
    }

    public Account(Integer id, LocalDateTime openingTime, LocalDateTime closingTime, double totalAmount, double paidAmount, double paidByValueAmount, double paidBySaleItemAmount) {
        this.id = id;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.totalAmount = totalAmount;
        this.paidAmount = paidAmount;
        this.paidByValueAmount = paidByValueAmount;
        this.paidBySaleItemAmount = paidBySaleItemAmount;
    }

    public void addToTotalAmount(double totalAmount) {
        this.totalAmount += totalAmount;
    }

    public void subtractFromTotalAmount(double totalAmount) {
        this.totalAmount -= totalAmount;
    }

    public void updatePaidAmount() {
        paidAmount = paidByValueAmount + paidBySaleItemAmount;
    }

    public boolean checkIfHasValueToPay() {
        if (paidAmount < totalAmount)
            return true;
        return false;
    }

    public boolean checkIfValueToPayIsValid(double valueToPay) {
        if (paidAmount + valueToPay <= totalAmount)
            return true;
        return false;
    }

    public void updatePaidByValueAmount(double paymentValue) {
        if(paidByValueAmount == 0) {
            paidByValueAmount = paymentValue;
            updatePaidAmount();
            return;
        }
        paidByValueAmount += paymentValue;
        updatePaidAmount();
    }

    public void updatePaidBySaleItemAmount(double paymentValue) {
        if(paidBySaleItemAmount == 0) {
            paidBySaleItemAmount = paymentValue;
            updatePaidAmount();
            return;
        }
        paidBySaleItemAmount += paymentValue;
        updatePaidAmount();
    }

    public Integer getId() {
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

    public double getPaidByValueAmount() { return paidByValueAmount; }

    public double getPaidBySaleItemAmount() {
        return paidBySaleItemAmount;
    }

    public ClientTable getClientTable() {
        return clientTable;
    }

    public void setId(Integer id) {
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

    public void setClientTable(ClientTable clientTable) {
        this.clientTable = clientTable;
        this.clientTableId = clientTable.getId();
    }

    public Integer getClientTableId() {
        return clientTableId;
    }

    public void setClientTableId(Integer clientTableId) {
        this.clientTableId = clientTableId;
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
