package br.com.bardotoco.persistence.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.bardotoco.model.entities.Conta;
import br.com.bardotoco.persistence.utils.ConnectionFactory;

public class ContaSQLiteDAO implements ContaDAO {
    @Override
    public void criarTabela() {

        String sql = "CREATE TABLE IF NOT EXISTS conta (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "mesa INTEGER NOT NULL," +
                "horarioAbertura TEXT NOT NULL, " +
                "horarioFechamento TEXT, " +
                "valorTotal REAL, " +
                "valorPago REAL, " +
                "FOREIGN KEY(mesa) REFERENCES mesa(codigo))";

        try{
            PreparedStatement stmt = ConnectionFactory.createStatement(sql);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // apenas cria a conta, precisa criar outra pra atualizar valores etc
    @Override
    public void salvar(Conta conta) {
        String sql = "INSERT INTO conta(horarioAbertura, mesa)  VALUES (?,?)";

        try(
            PreparedStatement stmt = ConnectionFactory.createStatement(sql)){
            stmt.setString(1,conta.getHorarioAbertura().toString());
            stmt.setInt(2,conta.getMesa().getCodigo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    };

    @Override
    public void atualizar(Conta conta) {
        String sql = "UPDATE conta SET horarioAbertura=?, horarioFechamento=?, valorTotal=?, valorPago=?, mesa=? WHERE id=?";

        try(
            PreparedStatement stmt = ConnectionFactory.createStatement(sql)){
            stmt.setString(1,conta.getHorarioAbertura().toString());
            stmt.setString(2,conta.getHorarioFechamento().toString());
            stmt.setDouble(3,conta.getValorTotal());
            stmt.setDouble(4,conta.getValorPago());
            stmt.setInt(5,conta.getMesa().getCodigo());
            stmt.setInt(6,conta.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    };

    @Override
    public void apagar(Conta conta) {
        String sql = "DELETE FROM conta WHERE id=?";

        try(
            PreparedStatement stmt = ConnectionFactory.createStatement(sql)){
            stmt.setInt(1,conta.getId());
            stmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    };
}
