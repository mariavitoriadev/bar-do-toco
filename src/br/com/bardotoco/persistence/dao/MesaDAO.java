package br.com.bardotoco.persistence.dao;

import br.com.bardotoco.model.entities.Mesa;

public interface MesaDAO {

    void criarTabela();
    void salvar(Mesa mesa);
    void atualizar (Mesa mesa);
    void apagar (Mesa mesa);

}


