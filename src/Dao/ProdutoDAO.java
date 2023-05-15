package Dao;

import Model.Produto;
import java.util.List;

public interface ProdutoDAO {

    void criarTabela() throws ClassNotFoundException;
    void salvar(Produto produto) throws ClassNotFoundException;
    void atualizar (Produto produto) throws ClassNotFoundException;
    void apagar (Produto produto) throws ClassNotFoundException;

}