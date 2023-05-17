package br.com.bardotoco.persistence.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.time.LocalDateTime;

import br.com.bardotoco.model.entities.Account;
import br.com.bardotoco.persistence.utils.AbstractTemplateSqlDAO;

public class DAOAccount extends AbstractTemplateSqlDAO<Account, Integer>{

    @Override
    protected String createSaveSql() {
        return "INSERT INTO Account(openingTime, closingTime, totalAmount, paidAmount, clientTable) " +
                "VALUES(?, ?)";
    }

    @Override
    protected String createUpdateSql() {
        return "UPDATE Account SET openingTime = ?, closingTime = ?, totalAmount = ?, paidAmount = ?, clientTable = ? " +
                "WHERE id = ?";
    }

    @Override
    protected String createDeleteSql() {
        return "DELETE FROM Account WHERE id = ?";
    }

    @Override
    protected String createSelectSql() {
        return "SELECT * FROM Account WHERE id = ?";
    }

    @Override
    protected String createSelectAllSql() {
        return "SELECT * FROM Account";
    }

    @Override
    protected String createSelectBySql(String field) {
        return "SELECT * FROM Account WHERE " + field + " = ?";
    }

    @Override
    protected void setEntityToPreparedStatement(Account entity, PreparedStatement stmt) throws SQLException {
        stmt.setString(1, entity.getOpeningTime().toString());
        stmt.setString(1, entity.getClosingTime().toString());
        stmt.setDouble(1, entity.getTotalAmount());
        stmt.setDouble(1, entity.getPaidAmount());
        stmt.setInt(3, entity.getClientTable().getId());
    }

    @Override
    protected void setKeyToPreparedStatement( Integer key, PreparedStatement stmt) throws SQLException {
        stmt.setInt(1, key);
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
    protected Account getEntityFromResultSet(ResultSet rs) throws SQLException {

        Account account = new Account(
                rs.getInt("id"),
                LocalDateTime.parse(rs.getString("openingTime")),
                LocalDateTime.parse(rs.getString("closingTime")),
                rs.getDouble("totalAmount"),
                rs.getDouble("paidAmount"));
        return account;
    }

    @Override
    protected Integer getEntityKey( Account entity) {
        return entity.getId();
    }
}
