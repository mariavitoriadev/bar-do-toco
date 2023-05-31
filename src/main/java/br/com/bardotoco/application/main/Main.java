package main.java.br.com.bardotoco.application.main;

import main.java.br.com.bardotoco.domain.entities.account.Account;
import main.java.br.com.bardotoco.domain.entities.cashier.CashierStatus;
import main.java.br.com.bardotoco.domain.entities.clientTable.ClientTable;
import main.java.br.com.bardotoco.domain.entities.product.Product;

import main.java.br.com.bardotoco.application.repository.sqlite.*;
/*import main.java.br.com.bardotoco.application.repository.sqlite.SqliteAccountDAO;
import main.java.br.com.bardotoco.application.repository.sqlite.SqliteClientTableDAO;
import main.java.br.com.bardotoco.application.repository.sqlite.SqliteSaleItemDAO;
import main.java.br.com.bardotoco.application.repository.sqlite.DatabaseBuilder;
import main.java.br.com.bardotoco.application.repository.sqlite.SqliteProductDAO;*/
import main.java.br.com.bardotoco.domain.entities.saleitem.SaleItem;
import main.java.br.com.bardotoco.domain.useCases.account.*;
import main.java.br.com.bardotoco.domain.useCases.cashier.CloseCashierUseCase;
import main.java.br.com.bardotoco.domain.useCases.cashier.OpenCashierUseCase;
import main.java.br.com.bardotoco.domain.useCases.clientTable.CheckClientTableDebit;
import main.java.br.com.bardotoco.domain.useCases.clientTable.ClientTableDAO;
import main.java.br.com.bardotoco.domain.useCases.clientTable.CreateClientTableUseCase;
import main.java.br.com.bardotoco.domain.useCases.product.CreateProductUseCase;
import main.java.br.com.bardotoco.domain.useCases.product.ProductDAO;
import main.java.br.com.bardotoco.domain.useCases.saleItem.CreateSaleItemUseCase;
import main.java.br.com.bardotoco.domain.useCases.saleItem.SaleItemDAO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main {

    public static OpenCashierUseCase openCashierUseCase;
    public static CloseCashierUseCase closeCashierUseCase;
    public static CreateProductUseCase createProductUseCase;
    public static CreateClientTableUseCase createClientTableUseCase;
    public static CreateAccountUseCase createAccountUseCase;
    public static CreateSaleItemUseCase createSaleItemUseCase;
    public static CheckClientTableDebit checkClientTableDebit;
    public static PaymentByValueUseCase paymentByValueUseCase;
    public static PaymentBySaleItemUseCase paymentBySaleItemUseCase;
    public static CloseAccountUseCase closeAccountUseCase;

    public static void main(String[] args) {
        DatabaseBuilder dbBuilder = new DatabaseBuilder();
        dbBuilder.buildDatabaseIfMissing();

        ProductDAO productDAO = new SqliteProductDAO();
        ClientTableDAO clientTableDAO = new SqliteClientTableDAO();
        AccountDAO accountDAO = new SqliteAccountDAO();
        SaleItemDAO saleItemDAO = new SqliteSaleItemDAO();

        // Create Product
        Product product = new Product(1, "Pepsi 2L", 6.80 );
        Product product2 = new Product(2, "Amstel 1L", 11.50);
        Product product3 = new Product(3, "Batata Frita Média", 18.50);

        createProductUseCase = new CreateProductUseCase(productDAO);
        product.setId(createProductUseCase.insert(product));
        product2.setId(createProductUseCase.insert(product2));
        product3.setId(createProductUseCase.insert(product3));

        // Open Cashier
        openCashierUseCase = new OpenCashierUseCase();
        openCashierUseCase.openCashier();

        // Create Client Tables
        ClientTable clientTable = new ClientTable(1);
        ClientTable clientTable2 = new ClientTable(2);
        ClientTable clientTable3 = new ClientTable(3);

        createClientTableUseCase = new CreateClientTableUseCase(clientTableDAO);
        createClientTableUseCase.insert(clientTable);
        createClientTableUseCase.insert(clientTable2);
        createClientTableUseCase.insert(clientTable3);

        // Create Accounts
        Account account = new Account(LocalDateTime.now(), clientTable);
        account.setClientTable(clientTable);

        Account account2 = new Account(LocalDateTime.now(), clientTable2);
        account2.setClientTable(clientTable2);

        Account account3 = new Account(LocalDateTime.now(), clientTable3);
        account3.setClientTable(clientTable3);

        createAccountUseCase = new CreateAccountUseCase(accountDAO, clientTableDAO);
        account.setId(createAccountUseCase.insert(account));
        account2.setId(createAccountUseCase.insert(account2));
        account3.setId(createAccountUseCase.insert(account3));

        // Create Sale Items
        SaleItem saleItem = new SaleItem(product.getName(), 1);
        saleItem.setProduct(product);
        saleItem.setAccount(account);

        SaleItem saleItem2 = new SaleItem(product2.getName(), 2);
        saleItem2.setProduct(product2);
        saleItem2.setAccount(account2);

        SaleItem saleItem3 = new SaleItem(product3.getName(), 3);
        saleItem3.setProduct(product3);
        saleItem3.setAccount(account3);

        SaleItem saleItem4 = new SaleItem(product2.getName(), 3);
        saleItem4.setProduct(product2);
        saleItem4.setAccount(account3);

        createSaleItemUseCase = new CreateSaleItemUseCase(saleItemDAO, productDAO, accountDAO);
        saleItem.setId(createSaleItemUseCase.insert(saleItem));
        saleItem2.setId(createSaleItemUseCase.insert(saleItem2));
        saleItem3.setId(createSaleItemUseCase.insert(saleItem3));
        saleItem4.setId(createSaleItemUseCase.insert(saleItem4));

        // Check Client Table Debits
        checkClientTableDebit = new CheckClientTableDebit(clientTableDAO, saleItemDAO);
        List<SaleItem> clientTableDebit = checkClientTableDebit.checkDebit(clientTable3);
        for (SaleItem item : clientTableDebit) {
            System.out.println(item.toString());
        }

        paymentBySaleItemUseCase = new PaymentBySaleItemUseCase(accountDAO, saleItemDAO);
        paymentBySaleItemUseCase.payBySaleItem(saleItem4,3);

        // Check Client Table Debits
        clientTableDebit = checkClientTableDebit.checkDebit(clientTable3);
        for (SaleItem item : clientTableDebit) {
            System.out.println(item.toString());
        }

        paymentByValueUseCase = new PaymentByValueUseCase(accountDAO, saleItemDAO);
        paymentByValueUseCase.payByValue(account3,55.5);

        System.out.println("Valor total da Conta 3: " + account3.getTotalAmount());
        System.out.println("Valor pago da Conta 3: " + account3.getPaidAmount());

        closeAccountUseCase = new CloseAccountUseCase(accountDAO);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/y h:m:s");

        if (closeAccountUseCase.closeAccount(account3))
            System.out.println("Conta 3 fechada " + account3.getClosingTime().format(formatter).toString() + " com sucesso.");

        //closeCashierUseCase = new CloseCashierUseCase(accountDAO);
        //closeCashierUseCase.closeCashier();

        paymentByValueUseCase.payByValue(account,6.80);
        System.out.println("Valor total da Conta 1: " + account3.getTotalAmount());
        System.out.println("Valor pago da Conta 1: " + account3.getPaidAmount());
        closeAccountUseCase.closeAccount(account);

        paymentByValueUseCase.payByValue(account2,23);
        System.out.println("Valor total da Conta 2: " + account3.getTotalAmount());
        System.out.println("Valor pago da Conta 2: " + account3.getPaidAmount());
        closeAccountUseCase.closeAccount(account2);

        closeCashierUseCase = new CloseCashierUseCase(accountDAO);
        if (closeCashierUseCase.closeCashier().getCashierStatus() == CashierStatus.CLOSED)
            System.out.println("Caixa fechado com sucesso.");

    }

}