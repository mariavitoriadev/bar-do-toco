package br.com.bardotoco.persistence.dao;

import br.com.bardotoco.model.entities.ItemVenda;

public interface ItemVendaDAO {

    void salvar(ItemVenda itemVenda);
    void atualizar (ItemVenda itemVenda);
    void apagar (ItemVenda itemVenda);

}
