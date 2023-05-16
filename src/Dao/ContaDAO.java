package Dao;

import Model.Conta;

public interface ContaDAO {

        void criarTabela();
        void salvar(Conta conta);
        void atualizar (Conta conta);
        void apagar (Conta conta);

}
