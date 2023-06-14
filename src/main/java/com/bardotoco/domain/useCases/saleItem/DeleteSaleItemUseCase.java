package com.bardotoco.domain.useCases.saleItem;

import com.bardotoco.domain.entities.account.Account;
import com.bardotoco.domain.entities.saleitem.SaleItem;
import com.bardotoco.domain.useCases.account.AccountDAO;
import com.bardotoco.domain.useCases.utils.EntityNotFoundException;

public class DeleteSaleItemUseCase {
    private SaleItemDAO saleItemDAO;
    private AccountDAO accountDAO;

    public DeleteSaleItemUseCase(SaleItemDAO saleItemDAO, AccountDAO accountDAO) {
        this.saleItemDAO = saleItemDAO;
        this.accountDAO = accountDAO;
    }

    public boolean delete(Integer id) {
        if(id == null ||  saleItemDAO.findOne(id).isEmpty())
            throw new EntityNotFoundException("Item de Venda não encontrado.");

        return saleItemDAO.deleteByKey(id);
    }

    public boolean delete(SaleItem saleItem) {
        if(saleItem == null ||  saleItemDAO.findOne(saleItem.getId()).isEmpty())
            throw new EntityNotFoundException("Item de Venda não encontrado.");

        if(saleItem.getAccount().getClosingTime() != null)
            throw new IllegalArgumentException("Conta já está fechada.");

        if(saleItem.getPaidAmount() > 0)
            throw new IllegalArgumentException("Produto já iniciou o pagamento.");

        saleItem.getAccount().subtractFromTotalAmount(saleItem.getTotalAmount());
        accountDAO.update(saleItem.getAccount());

        return saleItemDAO.delete(saleItem);
    }
}
