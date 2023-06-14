package com.bardotoco.domain.useCases.clientTable;

import com.bardotoco.domain.entities.clientTable.ClientTable;
import com.bardotoco.domain.entities.product.Product;

import java.util.List;
import java.util.Optional;

public class SearchClientTableUseCase {

    private ClientTableDAO  clientTableDAO;

    public SearchClientTableUseCase(ClientTableDAO clientTableDAO) {
        this.clientTableDAO = clientTableDAO;
    }

    public Optional<ClientTable> findOne(Integer id){
        if (id == null)
            throw new IllegalArgumentException("ID da mesa nulo.");
        return clientTableDAO.findOne(id);
    }

    public List<ClientTable> findAll(){
        return clientTableDAO.findAll();
    }
}


