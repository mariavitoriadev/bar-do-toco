package br.com.bardotoco.persistence.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.bardotoco.model.entities.ItemVenda;
import br.com.bardotoco.persistence.utils.ConnectionFactory;

public class ItemVendaSQLiteDAO implements ItemVendaDAO {
        @Override
    public void salvar(ItemVenda itemVenda) {
        String sql = "INSERT INTO ItemVenda values (?,?,?,?,?,?,?)";

        try(
            PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setString(1,itemVenda.getId().toString());
            stmt.setString(2,itemVenda.getNome());
            stmt.setInt(3,itemVenda.getQuantidade());
            stmt.setDouble(4,itemVenda.getValorPago());
            stmt.setDouble(5,itemVenda.getValorTotal());
            stmt.setInt(6,itemVenda.getConta().getId());
            stmt.setInt(7,itemVenda.getProduto().getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    };

    @Override
    public void atualizar(ItemVenda itemVenda) {
        String sql = "UPDATE ItemVenda SET nome=?, quantidade=?, valorPago=?, valorTotal=?, conta=?, produto=? WHERE id=?";

        try(
            PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setString(1,itemVenda.getNome());
            stmt.setInt(2,itemVenda.getQuantidade());
            stmt.setDouble(3,itemVenda.getValorPago());
            stmt.setDouble(4,itemVenda.getValorTotal());
            stmt.setInt(5,itemVenda.getConta().getId());
            stmt.setInt(6,itemVenda.getProduto().getId());
            stmt.setString(7,itemVenda.getId().toString());
            stmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    };

    @Override
    public void apagar(ItemVenda itemVenda) {
        String sql = "DELETE FROM ItemVenda WHERE id=?";

        try(
            PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setString(1,itemVenda.getId().toString());
            stmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    };
}
