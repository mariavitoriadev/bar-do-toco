package br.com.bardotoco.domain.useCases.clientTable;

import br.com.bardotoco.domain.entities.clientTable.ClientTable;

public class CreateClientTableUseCase {

    private ClientTableDAO  clientTableDAO;

    public CreateClientTableUseCase(ClientTableDAO clientTableDAO) {
        this.clientTableDAO = clientTableDAO;
    }

    public Integer insert(ClientTable clientTable) {
        return clientTableDAO.create(clientTable);
    }

}
