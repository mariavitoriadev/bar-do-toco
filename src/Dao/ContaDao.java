package Dao;

import Model.Conta;
import Model.Produto;

public interface ContaDao {
    void criarTabela();
    void salvar(Conta C);
    void atualizar (Conta C);
    void apagar (Conta C);
}
