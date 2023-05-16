package Dao;

import Model.ItemVenda;

public interface ItemVendaDAO {

    void criarTabela();
    void salvar(ItemVenda itemVenda);
    void atualizar (ItemVenda itemVenda);
    void apagar (ItemVenda itemVenda);

}
