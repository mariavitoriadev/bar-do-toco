package com.bardotoco.application.repository.sqlite;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.sqlite.SQLiteDataSource;

public class ConnectionFactory implements AutoCloseable {

    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static Statement statement;

    private static void instantiateConnectionIfNull() throws SQLException {

        SQLiteDataSource ds = new SQLiteDataSource();
        ds.setUrl("jdbc:sqlite:bardotoco.db");

        if(connection == null) {
            connection = ds.getConnection();
        }
    }

    public static Connection createConnection() {
        try {
            instantiateConnectionIfNull();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static PreparedStatement createPreparedStatement(String sql) {
        try {
            preparedStatement = createConnection().prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }

    public static Statement createStatement() {
        try {
            statement = createConnection().createStatement();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    @Override
    public void close() throws Exception {
        closeStatementsIfNotNull();
        closeConnectionIfNotNull();
    }

    private void closeConnectionIfNotNull() throws SQLException {
        if(connection != null)
            connection.close();
    }

    private void closeStatementsIfNotNull() throws SQLException {
        if(preparedStatement != null)
            preparedStatement.close();
        if(statement != null)
            statement.close();
    }

}