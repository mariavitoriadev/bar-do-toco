package br.com.bardotoco.persistence.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.bardotoco.model.entities.Produto;
import br.com.bardotoco.persistence.utils.ConnectionFactory;

public class ProdutoSQLiteDAO implements ProdutoDAO{


    @Override
    public void salvar(Produto produto) {
        String sql = "INSERT INTO produto values (?,?,?)";

        try(
            PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setInt(1,produto.getId());
            stmt.setString(2,produto.getNome());
            stmt.setFloat(3,produto.getValor());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    };

    @Override
    public void atualizar(Produto produto) {
        String sql = "UPDATE produto SET name=?, valor=? WHERE id=?";

        try(
            PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setString(1,produto.getNome());
            stmt.setFloat(2,produto.getValor());
            stmt.setInt(3,produto.getId());
            stmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    };

    @Override
    public void apagar(Produto produto) {
        String sql = "DELETE FROM produto WHERE id=?";

        try(
            PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setInt(1,produto.getId());
            stmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    };

}
