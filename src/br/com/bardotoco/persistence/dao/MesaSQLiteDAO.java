package br.com.bardotoco.persistence.dao;

import java.sql.*;

import br.com.bardotoco.model.entities.Mesa;
import br.com.bardotoco.persistence.utils.ConnectionFactory;

public class MesaSQLiteDAO implements MesaDAO {


    @Override
    public void salvar(Mesa mesa) {
        String sql = "INSERT INTO mesa VALUES (?)";

        try(
            PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setInt(1,mesa.getCodigo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    };

    @Override
    public void atualizar(Mesa mesa) {
        String sql = "UPDATE mesa SET codigo=? WHERE codigo=?";

        try(
            PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setInt(1,mesa.getCodigo());
            stmt.setInt(2,mesa.getCodigo());
            stmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    };

    @Override
    public void apagar(Mesa mesa) {
        String sql = "DELETE FROM mesa WHERE codigo=?";

        try(
            PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setInt(1,mesa.getCodigo());
            stmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    };
}
