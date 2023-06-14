package com.bardotoco.application.repository.sqlite;

import com.bardotoco.domain.entities.clientTable.ClientTable;
import com.bardotoco.domain.useCases.clientTable.ClientTableDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqliteClientTableDAO implements ClientTableDAO {
    @Override
    public Integer create(ClientTable clientTable) {
        String sql = "INSERT INTO ClientTable( id ) " +
                "VALUES(NULL)";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
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
    public Optional<ClientTable> findOne(Integer key) {
        String sql = "SELECT * FROM ClientTable WHERE id = ?";
        ClientTable clientTable = null;

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setInt(1, key);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                clientTable = resultSetToEntity(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(clientTable);
    }

    private ClientTable resultSetToEntity(ResultSet rs) throws SQLException {
        return new ClientTable(
                rs.getInt("id")
        );
    }

    @Override
    public List<ClientTable> findAll() {
        String sql = "SELECT * FROM ClientTable";
        List<ClientTable> clientTables = new ArrayList<>();

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                ClientTable clientTable = resultSetToEntity(rs);
                clientTables.add(clientTable);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientTables;
    }

    @Override
    public boolean update(ClientTable clientTable) {
        String sql = "UPDATE ClientTable SET id = ? " +
                "WHERE id = ?";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setInt(1, clientTable.getId());
            stmt.setInt(2, clientTable.getId());
            stmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByKey(Integer key) {
        String sql = "DELETE FROM ClientTable WHERE id = ?";
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
    public boolean delete(ClientTable clientTable) {
        if (clientTable == null || clientTable.getId() == null)
            throw new IllegalArgumentException("Client Table and Client Table ID must not be null.");
        return deleteByKey(clientTable.getId());
    }
}
