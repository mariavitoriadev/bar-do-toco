package Dao;

import Model.Mesa;
import java.util.List;

public interface MesaDAO {

    void criarTabela();
    void salvar(Mesa mesa);
    void atualizar (Mesa mesa);
    void apagar (Mesa mesa);

}


