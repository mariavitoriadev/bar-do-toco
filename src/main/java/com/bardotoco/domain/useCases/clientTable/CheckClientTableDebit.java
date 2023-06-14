package com.bardotoco.domain.useCases.clientTable;

import com.bardotoco.domain.entities.account.Account;
import com.bardotoco.domain.entities.clientTable.ClientTable;
import com.bardotoco.domain.entities.saleitem.SaleItem;
import com.bardotoco.domain.useCases.saleItem.SaleItemDAO;
import com.bardotoco.domain.useCases.utils.EntityNotFoundException;

import java.util.List;

public class CheckClientTableDebit {

    private ClientTableDAO  clientTableDAO;
    private SaleItemDAO saleItemDAO;

    public CheckClientTableDebit(ClientTableDAO clientTableDAO, SaleItemDAO saleItemDAO) {
        this.clientTableDAO = clientTableDAO;
        this.saleItemDAO = saleItemDAO;
    }

    public List<SaleItem> checkDebit(ClientTable clientTable) {
        if(clientTable == null ||  clientTableDAO.findOne(clientTable.getId()).isEmpty())
            throw new EntityNotFoundException("Mesa não encontrada.");

        Account account = clientTable.getAccount();
        if(account == null)
            throw new EntityNotFoundException("Conta não encontrada.");

        List<SaleItem> accountSaleItems = saleItemDAO.findByAccount(account);
        if(accountSaleItems.isEmpty())
            throw new EntityNotFoundException("Nenhum Item de Venda encontrado.");

        return accountSaleItems;
    }
}
