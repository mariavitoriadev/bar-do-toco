package com.bardotoco.application.repository.sqlite;

import com.bardotoco.domain.entities.account.Account;
import com.bardotoco.domain.entities.product.Product;
import com.bardotoco.domain.entities.saleitem.SaleItem;
import com.bardotoco.domain.useCases.account.AccountDAO;
import com.bardotoco.domain.useCases.product.ProductDAO;
import com.bardotoco.domain.useCases.saleItem.SaleItemDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqliteSaleItemDAO implements SaleItemDAO {
    @Override
    public Integer create(SaleItem saleItem) {
        String sql = "INSERT INTO SaleItem( name, quantity, totalAmount, paidAmount, account, product) " +
                "VALUES(?, ?, ?, ?, ?, ?)";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setString(1, saleItem.getName());
            stmt.setInt(2, saleItem.getQuantity());
            stmt.setDouble(3, saleItem.getTotalAmount());
            stmt.setDouble(4, saleItem.getPaidAmount());
            stmt.setInt(5, saleItem.getAccount().getId());
            stmt.setInt(6, saleItem.getProduct().getId());
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
    public Optional<SaleItem> findOne(Integer key) {
        String sql = "SELECT * FROM SaleItem WHERE id = ?";
        SaleItem saleItem = null;

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setInt(1, key);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                saleItem = resultSetToEntity(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(saleItem);
    }

    private SaleItem resultSetToEntity(ResultSet rs) throws SQLException {
        SaleItem saleItem = new SaleItem(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("quantity"),
                rs.getDouble("totalAmount"),
                rs.getDouble("paidAmount")
        );

        AccountDAO accountDAO = new SqliteAccountDAO();
        Account account = accountDAO.findOne(rs.getInt("account")).get();
        saleItem.setAccount(account);

        ProductDAO productDAO = new SqliteProductDAO();
        Product product = productDAO.findOne(rs.getInt("product")).get();
        saleItem.setProduct(product);

        return saleItem;
    }

    @Override
    public List<SaleItem> findByAccount(Account account) {
        String sql = "SELECT * FROM SaleItem WHERE account = ?";
        List<SaleItem> saleItems = new ArrayList<>();

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setInt(1, account.getId());
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                SaleItem saleItem = resultSetToEntity(rs);
                saleItems.add(saleItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return saleItems;
    }

    @Override
    public List<SaleItem> findAll() {
        String sql = "SELECT * FROM SaleItem";
        List<SaleItem> saleItems = new ArrayList<>();

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                SaleItem saleItem = resultSetToEntity(rs);
                saleItems.add(saleItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return saleItems;
    }

    @Override
    public boolean update(SaleItem saleItem) {
        String sql = "UPDATE SaleItem SET name = ?, quantity = ?, totalAmount = ?, paidAmount = ?, account = ?, product = ? " +
                "WHERE id = ?";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setString(1, saleItem.getName());
            stmt.setInt(2, saleItem.getQuantity());
            stmt.setDouble(3, saleItem.getTotalAmount());
            stmt.setDouble(4, saleItem.getPaidAmount());
            stmt.setInt(5, saleItem.getAccount().getId());
            stmt.setInt(6, saleItem.getProduct().getId());
            stmt.setInt(7, saleItem.getId());
            stmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByKey(Integer key) {
        String sql = "DELETE FROM SaleItem WHERE id = ?";
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
    public boolean delete(SaleItem saleItem) {
        if (saleItem == null || saleItem.getId() == null)
            throw new IllegalArgumentException("SaleItem and saleItem ID must not be null.");
        return deleteByKey(saleItem.getId());
    }
}
