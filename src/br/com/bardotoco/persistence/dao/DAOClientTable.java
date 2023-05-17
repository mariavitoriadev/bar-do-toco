package br.com.bardotoco.persistence.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import br.com.bardotoco.model.entities.ClientTable;
import br.com.bardotoco.persistence.utils.AbstractTemplateSqlDAO;

public class DAOClientTable extends AbstractTemplateSqlDAO<ClientTable, Integer>{

    @Override
    protected String createSaveSql() {
        return "INSERT INTO ClientTable(id) " +
                "VALUES(?)";
    }

    @Override
    protected String createUpdateSql() {
        return "UPDATE ClientTable SET id = ?" +
                "WHERE id = ?";
    }

    @Override
    protected String createDeleteSql() {
        return "DELETE FROM ClientTable WHERE id = ?";
    }

    @Override
    protected String createSelectSql() {
        return "SELECT * FROM ClientTable WHERE id = ?";
    }

    @Override
    protected String createSelectAllSql() {
        return "SELECT * FROM ClientTable";
    }

    @Override
    protected String createSelectBySql(String field) {
        return "SELECT * FROM SaleItem WHERE " + field + " = ?";
    }

    @Override
    protected void setEntityToPreparedStatement(ClientTable entity, PreparedStatement stmt) throws SQLException {
        stmt.setInt(1, entity.getId());
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
    protected ClientTable getEntityFromResultSet(ResultSet rs) throws SQLException {

        ClientTable clientTable = new ClientTable(
                rs.getInt("id"));
        return clientTable;
    }

    @Override
    protected Integer getEntityKey( ClientTable entity) {
        return entity.getId();
    }
}
