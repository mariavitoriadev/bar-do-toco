package com.bardotoco.application.main;

import com.bardotoco.application.view.WindowLoader;

import com.bardotoco.application.repository.sqlite.SqliteAccountDAO;
import com.bardotoco.application.repository.sqlite.SqliteProductDAO;
import com.bardotoco.application.repository.sqlite.SqliteClientTableDAO;
import com.bardotoco.application.repository.sqlite.SqliteSaleItemDAO;
import com.bardotoco.application.repository.sqlite.DatabaseBuilder;
import com.bardotoco.domain.useCases.account.*;
import com.bardotoco.domain.useCases.cashier.*;
import com.bardotoco.domain.useCases.clientTable.*;
import com.bardotoco.domain.useCases.product.*;
import com.bardotoco.domain.useCases.saleItem.*;


public class Main {

    public static OpenCashierUseCase openCashierUseCase;
    public static CloseCashierUseCase closeCashierUseCase;

    public static CreateProductUseCase createProductUseCase;
    public static DeleteProductUseCase deleteProductUseCase;
    public static SearchProductUseCase searchProductUseCase;
    public static UpdateProductUseCase updateProductUseCase;

    public static CreateClientTableUseCase createClientTableUseCase;
    public static DeleteClientTableUseCase deleteClientTableUseCase;
    public static SearchClientTableUseCase searchClientTableUseCase;

    public static CreateAccountUseCase createAccountUseCase;
    public static PaymentByValueUseCase paymentByValueUseCase;
    public static PaymentBySaleItemUseCase paymentBySaleItemUseCase;
    public static CloseAccountUseCase closeAccountUseCase;
    public static SearchAccountUseCase searchAccountUseCase;

    public static CreateSaleItemUseCase createSaleItemUseCase;
    public static SearchSaleItemUseCase searchSaleItemUseCase;
    public static DeleteSaleItemUseCase deleteSaleItemUseCase;
    public static UpdateSaleItemUseCase updateSaleItemUseCase;

    public static void main(String[] args) {
        configureInjection();
        setupDatabase();
        WindowLoader.main(args);
    }

    private static void setupDatabase() {
        DatabaseBuilder dbBuilder = new DatabaseBuilder();
        dbBuilder.buildDatabaseIfMissing();
    }

    private static void configureInjection() {
        AccountDAO accountDAO = new SqliteAccountDAO();
        ProductDAO productDAO = new SqliteProductDAO();
        ClientTableDAO clientTableDAO = new SqliteClientTableDAO();
        SaleItemDAO saleItemDAO = new SqliteSaleItemDAO();

        openCashierUseCase = new OpenCashierUseCase();
        closeCashierUseCase = new CloseCashierUseCase(accountDAO);

        createProductUseCase = new CreateProductUseCase(productDAO);
        updateProductUseCase = new UpdateProductUseCase(productDAO);
        deleteProductUseCase = new DeleteProductUseCase(productDAO);
        searchProductUseCase = new SearchProductUseCase(productDAO);

        createClientTableUseCase = new CreateClientTableUseCase(clientTableDAO);
        deleteClientTableUseCase = new DeleteClientTableUseCase(clientTableDAO);
        searchClientTableUseCase = new SearchClientTableUseCase(clientTableDAO);

        createAccountUseCase = new CreateAccountUseCase(accountDAO, clientTableDAO);
        searchAccountUseCase = new SearchAccountUseCase(accountDAO);
        closeAccountUseCase = new CloseAccountUseCase(accountDAO);
        paymentBySaleItemUseCase = new PaymentBySaleItemUseCase(accountDAO, saleItemDAO);
        paymentByValueUseCase = new PaymentByValueUseCase(accountDAO, saleItemDAO);

        createSaleItemUseCase = new CreateSaleItemUseCase(saleItemDAO, productDAO, accountDAO);
        searchSaleItemUseCase = new SearchSaleItemUseCase(saleItemDAO);
        deleteSaleItemUseCase = new DeleteSaleItemUseCase(saleItemDAO, accountDAO);
        updateSaleItemUseCase = new UpdateSaleItemUseCase(saleItemDAO, accountDAO);
    }

}