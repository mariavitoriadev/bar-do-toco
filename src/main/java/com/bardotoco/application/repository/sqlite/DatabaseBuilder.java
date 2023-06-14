package com.bardotoco.application.repository.sqlite;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseBuilder {

    public void buildDatabaseIfMissing(){
        if(!isDatabaseAvailable()){
            System.out.println("Database is missing. Building database: \n");
            buildTables();
        }
    }

    private boolean isDatabaseAvailable(){
        return Files.exists(Paths.get("bardotoco.db"));
    }


    private void buildTables() {
        try (Statement stmt = ConnectionFactory.createStatement()) {
            stmt.addBatch(createProductTableSql());
            stmt.addBatch(createClientTableSql());
            stmt.addBatch(createAccountTableSql());
            stmt.addBatch(createSaleItemTableSql());

            stmt.executeBatch();

            System.out.println("Database successfully created.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private String createProductTableSql(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Product (\n");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT, \n");
        builder.append("name TEXT NOT NULL, \n");
        builder.append("price REAL NOT NULL \n");
        builder.append("); \n");

        return builder.toString();
    }

    private String createClientTableSql(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE ClientTable (\n");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT \n");
        builder.append("); \n");

        return builder.toString();
    }

    private String createAccountTableSql(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Account (\n");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT, \n");
        builder.append("clientTable INTEGER NOT NULL, \n");
        builder.append("openingTime TEXT NOT NULL, \n");
        builder.append("closingTime TEXT, \n");
        builder.append("totalAmount REAL, \n");
        builder.append("paidAmount REAL, \n");
        builder.append("paidByValueAmount REAL, \n");
        builder.append("paidBySaleItemAmount REAL, \n");
        builder.append("FOREIGN KEY(clientTable) REFERENCES ClientTable(id) on delete cascade\n");
        builder.append("); \n");

        return builder.toString();
    }

    private String createSaleItemTableSql(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE SaleItem (\n");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT, \n");
        builder.append("name TEXT, \n");
        builder.append("quantity INTEGER NOT NULL, \n");
        builder.append("totalAmount REAL, \n");
        builder.append("paidAmount REAL, \n");
        builder.append("account INTEGER NOT NULL, \n");
        builder.append("product INTEGER NOT NULL, \n");
        builder.append("FOREIGN KEY(account) REFERENCES Account(id)\n");
        builder.append("FOREIGN KEY(product) REFERENCES Product(id) \n");
        builder.append("); \n");

        return builder.toString();
    }

}