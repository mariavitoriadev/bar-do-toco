package br.com.bardotoco.main;

import br.com.bardotoco.model.entities.*;

import br.com.bardotoco.persistence.dao.DAOAccount;
import br.com.bardotoco.persistence.dao.DAOClientTable;
import br.com.bardotoco.persistence.dao.DAOProduct;
import br.com.bardotoco.persistence.dao.DAOSaleItem;
import br.com.bardotoco.persistence.utils.DatabaseBuilder;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        DatabaseBuilder dbBuilder = new DatabaseBuilder();
        dbBuilder.buildDatabaseIfMissing();

        Product product = new Product(1, "Guaraná 2L", 6.80 );
        Product product2 = new Product(2, "Skol 1L", 11.50);
        Product product3 = new Product(3, "Batata Frita Pequena", 18.50);

        DAOProduct daoProduct = new DAOProduct();
        daoProduct.save(product);
        daoProduct.save(product2);
        daoProduct.save(product3);

        Cashier cashier = Cashier.getInstance();
        cashier.openCashier();

        ClientTable clientTable = new ClientTable(1);
        ClientTable clientTable2 = new ClientTable(2);
        ClientTable clientTable3 = new ClientTable(3);

        DAOClientTable daoClientTable = new DAOClientTable();
        daoClientTable.save(clientTable);
        daoClientTable.save(clientTable2);
        daoClientTable.save(clientTable3);

        Account account = new Account(1, LocalDateTime.now(), null, 0, 0 );
        account.setClientTable(clientTable);

        Account account2 = new Account(2, LocalDateTime.now(), null, 0, 0 );
        account2.setClientTable(clientTable2);

        Account account3 = new Account(3, LocalDateTime.now(), null, 0, 0 );
        account3.setClientTable(clientTable3);

        DAOAccount daoAccount = new DAOAccount();
        daoAccount.save(account);
        daoAccount.save(account2);
        daoAccount.save(account3);

        SaleItem saleItem = new SaleItem(Timestamp.valueOf(LocalDateTime.now()), product.getName(), 1, 0, 0);
        saleItem.setProduct(product);
        saleItem.setAccount(account);

        SaleItem saleItem2 = new SaleItem(Timestamp.valueOf(LocalDateTime.now()), product2.getName(), 2, 0, 0);
        saleItem2.setProduct(product2);
        saleItem2.setAccount(account2);

        SaleItem saleItem3 = new SaleItem(Timestamp.valueOf(LocalDateTime.now()), product3.getName(), 3, 0, 0);
        saleItem3.setProduct(product3);
        saleItem3.setAccount(account3);

        DAOSaleItem daoSaleItem = new DAOSaleItem();
        daoSaleItem.save(saleItem);
        daoSaleItem.save(saleItem2);
        daoSaleItem.save(saleItem3);


    }



}