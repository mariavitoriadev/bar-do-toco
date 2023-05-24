package br.com.bardotoco.application.main;

import br.com.bardotoco.domain.entities.account.Account;
import br.com.bardotoco.domain.entities.clientTable.ClientTable;
import br.com.bardotoco.domain.entities.product.Product;
import br.com.bardotoco.domain.entities.cashier.Cashier;


import br.com.bardotoco.application.repository.sqlite.SqliteAccountDAO;
import br.com.bardotoco.application.repository.sqlite.SqliteProductDAO;
import br.com.bardotoco.application.repository.sqlite.SqliteClientTableDAO;
import br.com.bardotoco.application.repository.sqlite.SqliteSaleItemDAO;
import br.com.bardotoco.application.repository.sqlite.DatabaseBuilder;
import br.com.bardotoco.domain.useCases.account.AccountDAO;
import br.com.bardotoco.domain.useCases.account.CreateAccountUseCase;
import br.com.bardotoco.domain.useCases.clientTable.ClientTableDAO;
import br.com.bardotoco.domain.useCases.clientTable.CreateClientTableUseCase;
import br.com.bardotoco.domain.useCases.product.CreateProductUseCase;
import br.com.bardotoco.domain.useCases.product.ProductDAO;
import br.com.bardotoco.domain.useCases.saleItem.CreateSaleItemUseCase;
import br.com.bardotoco.domain.useCases.saleItem.SaleItemDAO;


import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Main {

    public static CreateProductUseCase createProductUseCase;
    public static CreateClientTableUseCase createClientTableUseCase;
    public static CreateAccountUseCase createAccountUseCase;
    public static CreateSaleItemUseCase createSaleItemUseCase;

    public static void main(String[] args) {
        DatabaseBuilder dbBuilder = new DatabaseBuilder();
        dbBuilder.buildDatabaseIfMissing();

        ProductDAO productDAO = new SqliteProductDAO();
        ClientTableDAO clientTableDAO = new SqliteClientTableDAO();
        AccountDAO accountDAO = new SqliteAccountDAO();
        SaleItemDAO saleItemDAO = new SqliteSaleItemDAO();

        // Create Product
        Product product = new Product(1, "Guaraná 2L", 6.80 );
        Product product2 = new Product(2, "Skol 1L", 11.50);
        Product product3 = new Product(3, "Batata Frita Pequena", 18.50);

        createProductUseCase = new CreateProductUseCase(productDAO);
        product.setId(createProductUseCase.insert(product));
        product2.setId(createProductUseCase.insert(product2));
        product3.setId(createProductUseCase.insert(product3));

        // Open Cashier
        Cashier cashier = Cashier.getInstance();
        cashier.openCashier();

        // Create Client Tables
        ClientTable clientTable = new ClientTable(1);
        ClientTable clientTable2 = new ClientTable(2);
        ClientTable clientTable3 = new ClientTable(3);

        createClientTableUseCase = new CreateClientTableUseCase(clientTableDAO);
        createClientTableUseCase.insert(clientTable);
        createClientTableUseCase.insert(clientTable2);
        createClientTableUseCase.insert(clientTable3);

        // Create Accounts
        Account account = new Account(LocalDateTime.now());
        account.setClientTable(clientTable);

        Account account2 = new Account(LocalDateTime.now());
        account2.setClientTable(clientTable2);

        Account account3 = new Account(LocalDateTime.now());
        account3.setClientTable(clientTable3);

        createAccountUseCase = new CreateAccountUseCase(accountDAO);
        account.setId(createAccountUseCase.insert(account));
        account2.setId(createAccountUseCase.insert(account2));
        account3.setId(createAccountUseCase.insert(account3));

        // Create Sale Items
        createSaleItemUseCase = new CreateSaleItemUseCase(saleItemDAO, productDAO, accountDAO);
        createSaleItemUseCase.insert(product, 1, account);
        createSaleItemUseCase.insert(product2, 2, account2);
        createSaleItemUseCase.insert(product3, 3, account3);

    }

}