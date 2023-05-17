package br.com.bardotoco.persistence.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Timestamp;

import br.com.bardotoco.model.entities.ItemVenda;
import br.com.bardotoco.persistence.utils.AbstractTemplateSqlDAO;

public class DAOSaleItem extends AbstractTemplateSqlDAO<ItemVenda, Timestamp>{

    @Override
    protected String createSaveSql() {
        return "INSERT INTO SaleItem(id, name, quantity, totalAmout, paidAmount, account, product) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected String createUpdateSql() {
        return "UPDATE SaleItem SET name = ?, quantity = ?, totalAmout = ?, paidAmount = ?, account = ?, product = ? " +
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
    protected void setEntityToPreparedStatement(ItemVenda entity, PreparedStatement stmt) throws SQLException {
        stmt.setString(1, entity.getId().toString());
        stmt.setString(1, entity.getNome());
        stmt.setInt(1, entity.getQuantidade());
        stmt.setDouble(1, entity.getValorTotal());
        stmt.setDouble(1, entity.getValorPago());
        stmt.setInt(1, entity.getProduto().getId());
        stmt.setInt(1, entity.getConta().getId());
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
    protected ItemVenda getEntityFromResultSet( ResultSet rs) throws SQLException {

        ItemVenda saleItem = new ItemVenda(
                Timestamp.valueOf(rs.getString("id")),
                rs.getString("name"),
                rs.getInt("quantity"),
                rs.getDouble("totalAmount"),
                rs.getDouble("paidAmount"));
        return saleItem;
    }

    @Override
    protected Timestamp getEntityKey( ItemVenda entity) {
        return entity.getId();
    }
}
