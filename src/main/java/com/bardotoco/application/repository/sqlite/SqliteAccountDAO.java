package com.bardotoco.application.repository.sqlite;

import com.bardotoco.domain.entities.account.Account;
import com.bardotoco.domain.entities.clientTable.ClientTable;
import com.bardotoco.domain.useCases.account.AccountDAO;
import com.bardotoco.domain.useCases.clientTable.ClientTableDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqliteAccountDAO implements AccountDAO {
    @Override
    public Integer create(Account account) {
        String sql = "INSERT INTO Account(openingTime, closingTime, totalAmount, paidAmount, paidByValueAmount, paidBySaleItemAmount, clientTable) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?)";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            LocalDateTime date = account.getClosingTime();

            stmt.setString(1, account.getOpeningTime().toString());
            stmt.setString(2, date!= null ? date.toString() : null);
            stmt.setDouble(3, account.getTotalAmount());
            stmt.setDouble(4, account.getPaidAmount());
            stmt.setDouble(5, account.getPaidByValueAmount());
            stmt.setDouble(6, account.getPaidBySaleItemAmount());
            stmt.setInt(7, account.getClientTable().getId());
            stmt.execute();

            ResultSet resultSet = stmt.getGeneratedKeys();
            int generatedKey = resultSet.getInt(1);
            return generatedKey;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<Account> findOne(Integer key) {
        String sql = "SELECT * FROM Account WHERE id = ?";
        Account account = null;

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setInt(1, key);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                account = resultSetToEntity(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(account);
    }

    @Override
    public List<Account> findNotClosedAccounts() {
        String sql = "SELECT * FROM Account WHERE closingTime IS NULL;";
        List<Account> accounts = new ArrayList<>();

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Account account = resultSetToEntity(rs);
                accounts.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    private Account resultSetToEntity(ResultSet rs) throws SQLException {
        Account account = new Account(
                rs.getInt("id"),
                LocalDateTime.parse(rs.getString("openingTime")),
                rs.getString("closingTime") != null ? LocalDateTime.parse(rs.getString("closingTime")) : null,
                rs.getDouble("totalAmount"),
                rs.getDouble("paidAmount"),
                rs.getDouble("paidByValueAmount"),
                rs.getDouble("paidBySaleItemAmount")
        );

        ClientTableDAO clientTableDAO = new SqliteClientTableDAO();
        ClientTable clientTable = clientTableDAO.findOne(rs.getInt("clientTable")).get();
        account.setClientTable(clientTable);

        return account;
    }

    @Override
    public List<Account> findAll() {
        String sql = "SELECT * FROM Account";
        List<Account> accounts = new ArrayList<>();

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Account account = resultSetToEntity(rs);
                accounts.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public boolean update(Account account) {
        String sql = "UPDATE Account SET openingTime = ?, closingTime = ?, totalAmount = ?, paidAmount = ?, paidByValueAmount =?, paidBySaleItemAmount =?, clientTable = ? " +
                "WHERE id = ?";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            LocalDateTime date = account.getClosingTime();

            stmt.setString(1, account.getOpeningTime().toString());
            stmt.setString(2, date!= null ? date.toString() : null);
            stmt.setDouble(3, account.getTotalAmount());
            stmt.setDouble(4, account.getPaidAmount());
            stmt.setDouble(5, account.getPaidByValueAmount());
            stmt.setDouble(6, account.getPaidBySaleItemAmount());
            stmt.setInt(7, account.getClientTable().getId());
            stmt.setInt(8, account.getId());
            stmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByKey(Integer key) {
        String sql = "DELETE FROM Account WHERE id = ?";
        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, key);
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Account account) {
        if (account == null || account.getId() == null)
            throw new IllegalArgumentException("Account and Account ID must not be null.");
        return deleteByKey(account.getId());
    }
}
