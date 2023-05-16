package br.com.bardotoco.persistence.dao;

import br.com.bardotoco.model.entities.Produto;

public interface ProdutoDAO {


    void salvar(Produto produto);
    void atualizar (Produto produto);
    void apagar (Produto produto);

}