package br.com.bardotoco.persistence.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Timestamp;

import br.com.bardotoco.model.entities.SaleItem;
import br.com.bardotoco.persistence.utils.AbstractTemplateSqlDAO;

public class DAOSaleItem extends AbstractTemplateSqlDAO<SaleItem, Timestamp>{

    @Override
    protected String createSaveSql() {
        return "INSERT INTO SaleItem(id, name, quantity, totalAmount, paidAmount, account, product) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected String createUpdateSql() {
        return "UPDATE SaleItem SET name = ?, quantity = ?, totalAmount = ?, paidAmount = ?, account = ?, product = ? " +
                "WHERE id = ?";
    }

    @Override
    protected String createDeleteSql() {
        return "DELETE FROM SaleItem WHERE id = ?";
    }

    @Override
    protected String createSelectSql() {
        return "SELECT * FROM SaleItem WHERE id = ?";
    }

    @Override
    protected String createSelectAllSql() {
        return "SELECT * FROM SaleItem";
    }

    @Override
    protected String createSelectBySql(String field) {
        return "SELECT * FROM SaleItem WHERE " + field + " = ?";
    }

    @Override
    protected void setEntityToPreparedStatement(SaleItem entity, PreparedStatement stmt) throws SQLException {
        stmt.setString(1, entity.getId().toString());
        stmt.setString(2, entity.getName());
        stmt.setInt(3, entity.getQuantity());
        stmt.setDouble(4, entity.getTotalAmount());
        stmt.setDouble(5, entity.getPaidAmount());
        stmt.setInt(6, entity.getProduct().getId());
        stmt.setInt(7, entity.getAccount().getId());
    }

    @Override
    protected void setKeyToPreparedStatement( Timestamp key, PreparedStatement stmt) throws SQLException {
        stmt.setString(1, key.toString());
    }

    @Override
    protected void setFilterToPreparedStatement( Object filter, PreparedStatement stmt) throws SQLException {
        if(filter instanceof String)
            stmt.setString(1, filter.toString());
        else if(filter instanceof Integer)
            stmt.setInt(1, (Integer)filter);
        else
            throw new SQLException("O tipo do filtro fornecido não é suportado pela consulta");
    }

    @Override
    protected SaleItem getEntityFromResultSet( ResultSet rs) throws SQLException {

        SaleItem saleItem = new SaleItem(
                Timestamp.valueOf(rs.getString("id")),
                rs.getString("name"),
                rs.getInt("quantity"),
                rs.getDouble("totalAmount"),
                rs.getDouble("paidAmount"));
        return saleItem;
    }

    @Override
    protected Timestamp getEntityKey( SaleItem entity) {
        return entity.getId();
    }
}
