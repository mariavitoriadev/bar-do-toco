package com.bardotoco.domain.useCases.saleItem;

import com.bardotoco.domain.entities.cashier.Cashier;
import com.bardotoco.domain.entities.cashier.CashierStatus;
import com.bardotoco.domain.entities.product.Product;
import com.bardotoco.domain.entities.saleitem.SaleItem;
import com.bardotoco.domain.useCases.account.AccountDAO;
import com.bardotoco.domain.useCases.product.ProductDAO;
import com.bardotoco.domain.useCases.utils.CashierNotOpenedException;
import com.bardotoco.domain.useCases.utils.EntityNotFoundException;

public class UpdateSaleItemUseCase {
    private SaleItemDAO saleItemDAO;
    private AccountDAO accountDAO;

    public UpdateSaleItemUseCase(SaleItemDAO saleItemDAO, AccountDAO accountDAO) {
        this.saleItemDAO = saleItemDAO;
        this.accountDAO = accountDAO;
    }

    public boolean update(SaleItem saleItem) {

        Cashier cashier = Cashier.getInstance();
        if(cashier.getCashierStatus() == CashierStatus.CLOSED)
            throw new CashierNotOpenedException("Operação não permitida, caixa está fechado.");

        if(saleItem.getAccount().getClosingTime() != null)
            throw new IllegalArgumentException("Conta já está fechada.");

        if(saleItemDAO.findOne(saleItem.getId()).isEmpty())
            throw new EntityNotFoundException("Item de Venda não encontrado.");

        saleItem.updateTotalAmount();
        SaleItem previusSaleITem = saleItemDAO.findOne(saleItem.getId()).get();

        saleItem.getAccount().subtractFromTotalAmount(previusSaleITem.getTotalAmount());
        saleItem.getAccount().addToTotalAmount(saleItem.getTotalAmount());

        accountDAO.update(saleItem.getAccount());

        return saleItemDAO.update(saleItem);

    }
}
