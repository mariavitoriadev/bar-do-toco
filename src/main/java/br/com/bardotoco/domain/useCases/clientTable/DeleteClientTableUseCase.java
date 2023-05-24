package br.com.bardotoco.domain.useCases.clientTable;

import br.com.bardotoco.domain.entities.clientTable.ClientTable;
import br.com.bardotoco.domain.useCases.utils.EntityNotFoundException;

public class DeleteClientTableUseCase {

    private ClientTableDAO  clientTableDAO;

    public DeleteClientTableUseCase(ClientTableDAO clientTableDAO) {
        this.clientTableDAO = clientTableDAO;
    }

    public boolean delete(Integer id) {
        if(id == null ||  clientTableDAO.findOne(id).isEmpty())
            throw new EntityNotFoundException("Mesa não encontrada.");

        return clientTableDAO.deleteByKey(id);
    }

    public boolean delete(ClientTable clientTable) {
        if(clientTable == null ||  clientTableDAO.findOne(clientTable.getId()).isEmpty())
            throw new EntityNotFoundException("Mesa não encontrada.");

        return clientTableDAO.delete(clientTable);
    }

}

