package Dao;

import Model.Conta;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class ContaSqliteDao implements ContaDao{
    @Override
    public void criarTabela(){

        String sql = "CREATE TABLE IF NOT EXISTS conta (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "horarioAbertura DATETIME NOT NULL, " +
                "horarioFechamento DATETIME NOT NULL, " +
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
        String sql = "INSERT INTO conta (horarioAbertura, horarioFechamento, valorTotal, valorPago, valorPagamento) values (?,?,?,?,?)";

        try (PreparedStatement stmt = ConnectionFactory.createStatement(sql)){
                stmt.setTimestamp(1, Timestamp.valueOf(C.getHorarioAbertura()));
                stmt.setTimestamp(2, Timestamp.valueOf(C.getHorarioFechamento()));
                stmt.setFloat(3, C.getValorTotal());
                stmt.setFloat(4, C.getValorPago());
                stmt.setFloat(5, C.getValorPagamento());
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
            //stmt.setInt(4, C.getNumeroMesa()); // Falta add a mesa na classe conta
            stmt.executeUpdate();
        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void apagar(Conta C) {
        String sql = "DELETE FROM conta WHERE numeromesa=?";

        try (PreparedStatement stmt = ConnectionFactory.createStatement(sql)){
            stmt.setInt(1, C.getNumeromesa());
            stmt.executeUpdate();
        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<LocalDateTime> mostraHora(){
        List<LocalDateTime> datasHorasAbertura = new ArrayList<>();

        String sql = "SELECT horarioAbertura FROM conta";
        try {PreparedStatement stmt = ConnectionFactory.createStatement(sql);
             ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Timestamp dataHoraAbertura = rs.getTimestamp("horarioAbertura");
                LocalDateTime horaFormatada = dataHoraAbertura.toLocalDateTime();
                datasHorasAbertura.add(horaFormatada);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        return datasHorasAbertura;
    }
}
