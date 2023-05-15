package Dao;

import Model.Conta;
import Model.Produto;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;


public class ContaSqliteDao implements ContaDao{
    @Override
    public void criarTabela(){

        String sql = "CREATE TABLE IF NOT EXISTS conta (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "horarioAbertura DATETIME NOT NULL, " +
                "horarioFechamento DATETIME NOT NULL, +" +
                "valorTotal REAL NOT NULL, " +
                "valorPago REAL NOT NULL, " +
                "valorPagamento REAL NOT NULL)";

        try (PreparedStatement stmt = ConnectionFactory.createStatement(sql)){
            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void salvar(Conta C) {
        String sql = "INSERT INTO conta values (?,?,?,?,?,?)";

        try (PreparedStatement stmt = ConnectionFactory.createStatement(sql)){
                stmt.setInt(1, C.getId());
                stmt.setTime(2, java.sql.Time.valueOf(String.valueOf(LocalDateTime.now())));
                stmt.setTime(3, java.sql.Time.valueOf(String.valueOf(LocalDateTime.now())));
                stmt.setFloat(4, C.getValorTotal());
                stmt.setFloat(5, C.getValorPago());
                stmt.setFloat(6, C.getValorPagamento());
                stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Conta C) {
        String sql = "UPDATE conta SET valorTotal=?, valorPago=?, valorPagamento=? WHERE numeroMesa=?";

        try (PreparedStatement stmt = ConnectionFactory.createStatement(sql)){
            stmt.setFloat(1, C.getValorTotal());
            stmt.setFloat(2, C.getValorPago());
            stmt.setFloat(3, C.getValorPagamento());
            stmt.setInt(4, C.getNumeroMesa()); // Falta add a mesa na classe conta
            stmt.executeUpdate();
        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void apagar(Conta C) {
        String sql = "DELETE FROM conta WHERE id=?";

        try (PreparedStatement stmt = ConnectionFactory.createStatement(sql)){
            stmt.setInt(1, C.getId());
            stmt.executeUpdate();
        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
