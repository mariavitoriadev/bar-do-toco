package br.com.bardotoco.persistence.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.time.LocalDateTime;

import br.com.bardotoco.model.entities.Conta;
import br.com.bardotoco.persistence.utils.AbstractTemplateSqlDAO;

public class DAOAccount extends AbstractTemplateSqlDAO<Conta, Integer>{

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
    protected void setEntityToPreparedStatement(Conta entity, PreparedStatement stmt) throws SQLException {
        stmt.setString(1, entity.getHorarioAbertura().toString());
        stmt.setString(1, entity.getHorarioFechamento().toString());
        stmt.setDouble(1, entity.getValorTotal());
        stmt.setDouble(1, entity.getValorPago());
        stmt.setInt(3, entity.getMesa().getCodigo());
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
    protected Conta getEntityFromResultSet( ResultSet rs) throws SQLException {

        Conta account = new Conta(
                rs.getInt("id"),
                LocalDateTime.parse(rs.getString("openingTime")),
                LocalDateTime.parse(rs.getString("closingTime")),
                rs.getDouble("totalAmount"),
                rs.getDouble("paidAmount"));
        return account;
    }

    @Override
    protected Integer getEntityKey( Conta entity) {
        return entity.getId();
    }
}
