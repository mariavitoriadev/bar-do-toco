package br.com.bardotoco.persistence.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import br.com.bardotoco.model.entities.Produto;
import br.com.bardotoco.persistence.utils.AbstractTemplateSqlDAO;

public class DAOProduct extends AbstractTemplateSqlDAO<Produto, Integer>{

    @Override
    protected String createSaveSql() {
        return "INSERT INTO Product(name, value) " +
                "VALUES(?, ?)";
    }

    @Override
    protected String createUpdateSql() {
        return "UPDATE Product SET name = ?, value = ? " +
                "WHERE id = ?";
    }

    @Override
    protected String createDeleteSql() {
        return "DELETE FROM Product WHERE id = ?";
    }

    @Override
    protected String createSelectSql() {
        return "SELECT * FROM Product WHERE id = ?";
    }

    @Override
    protected String createSelectAllSql() {
        return "SELECT * FROM Product";
    }

    @Override
    protected String createSelectBySql(String field) {
        return "SELECT * FROM Product WHERE " + field + " = ?";
    }

    @Override
    protected void setEntityToPreparedStatement( Produto entity, PreparedStatement stmt) throws SQLException {
        stmt.setString(1, entity.getNome());
        stmt.setDouble(2, entity.getValor());
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
    protected Produto getEntityFromResultSet( ResultSet rs) throws SQLException {
        Produto product = new Produto(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getDouble("value"));
        return product;
    }

    @Override
    protected Integer getEntityKey( Produto entity) {
        return entity.getId();
    }

}
