package Dao;

import Model.Conta;

import java.util.List;

public interface ContaDao {
    void criarTabela();
    void salvar(Conta C);
    void atualizar (Conta C);
    void apagar (Conta C);
    <LocalDateTime> List<java.time.LocalDateTime> mostraHora();

}
