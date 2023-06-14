package com.bardotoco.domain.entities.saleitem;
import com.bardotoco.domain.entities.account.Account;
import com.bardotoco.domain.entities.product.Product;

public class SaleItem {
    private Integer id;
    private String name;
    private Integer quantity;
    private double totalAmount;
    private double paidAmount;
    private Account account;
    private Product product;

    public SaleItem() {
    }

    public SaleItem( String name, Integer quantity) {
        this.name = name;
        this.quantity = quantity;
        updateTotalAmount();
    }

    public SaleItem(Integer id, String name, Integer quantity, double totalAmount, double paidAmount) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.paidAmount = paidAmount;
        updateTotalAmount();
    }

    @Override
    public String toString() {
        return "Item de Venda: \n" +
                "Nome: " + name  +
                ", Quantidade: " + quantity +
                ", Valor unitário: " + product.getPrice() +
                ", Valor Total: " + totalAmount +
                ", Valor Pago: " + paidAmount +
                ", Valor Restante: " + (totalAmount - paidAmount);

    }

    public void updateTotalAmount() {
        if (product != null && quantity >= 0)
            setTotalAmount(product.getPrice() * quantity);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
        updateTotalAmount();
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
