package Dao;

import Model.Mesa;
import java.sql.*;

public class MesaSQLiteDAO implements MesaDAO {

    @Override
    public void criarTabela() {

        String sql = "CREATE TABLE IF NOT EXISTS mesa (" +
                "codigo INTEGER PRIMARY KEY NOT NULL UNIQUE)";

        try{
            PreparedStatement stmt = ConnectionFactory.createStatement(sql);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void salvar(Mesa mesa) {
        String sql = "INSERT INTO mesa VALUES (?)";

        try(
            PreparedStatement stmt = ConnectionFactory.createStatement(sql)){
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
            PreparedStatement stmt = ConnectionFactory.createStatement(sql)){
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
            PreparedStatement stmt = ConnectionFactory.createStatement(sql)){
            stmt.setInt(1,mesa.getCodigo());
            stmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    };
}
