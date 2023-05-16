package br.com.bardotoco.persistence.dao;

import br.com.bardotoco.model.entities.Conta;

public interface ContaDAO {

        void salvar(Conta conta);
        void atualizar (Conta conta);
        void apagar (Conta conta);

}
