package br.com.bardotoco.model.entities;
import java.sql.Timestamp;

public class SaleItem {
    private Timestamp id;
    private String name;
    private int quantity;
    private double totalAmount;
    private double paidAmount;
    private Account account;
    private Product product;

    public SaleItem(Timestamp id, String name, int quantity, double totalAmount, double paidAmount) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.paidAmount = paidAmount;
    }

    public void updateTotalAmount() {
        if (product != null)
            setTotalAmount(product.getPrice() * quantity);
    }

    public Timestamp getId() {
        return id;
    }

    public void setId(Timestamp id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
        updateTotalAmount();
    }
}
