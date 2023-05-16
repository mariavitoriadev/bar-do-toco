package Dao;

import Model.Mesa;

public interface MesaDAO {

    void criarTabela();
    void salvar(Mesa mesa);
    void atualizar (Mesa mesa);
    void apagar (Mesa mesa);

}


