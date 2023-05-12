package Dao;

import Model.Produto;
import java.util.List;

public interface ProdutoDAO {

    void criarTabela();
    void salvar(Produto produto);
    void atualizar (Produto produto);
    void apagar (Produto produto);

}