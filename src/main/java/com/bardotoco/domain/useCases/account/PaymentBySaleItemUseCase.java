package com.bardotoco.domain.useCases.account;

import com.bardotoco.domain.entities.account.Account;
import com.bardotoco.domain.entities.cashier.Cashier;
import com.bardotoco.domain.entities.cashier.CashierStatus;
import com.bardotoco.domain.entities.saleitem.SaleItem;
import com.bardotoco.domain.useCases.saleItem.SaleItemDAO;
import com.bardotoco.domain.useCases.utils.CashierNotOpenedException;
import com.bardotoco.domain.useCases.utils.EntityNotFoundException;

public class PaymentBySaleItemUseCase {
    private AccountDAO accountDAO;
    private SaleItemDAO saleItemDAO;
    private Cashier cashier = Cashier.getInstance();

    public PaymentBySaleItemUseCase(AccountDAO accountDAO, SaleItemDAO saleItemDAO) {
        this.accountDAO = accountDAO;
        this.saleItemDAO = saleItemDAO;
    }

    public boolean payBySaleItem(SaleItem saleItem, double quantity) {
        double paymentAmount = saleItem.getProduct().getPrice() * quantity;

        if(cashier.getCashierStatus() == CashierStatus.CLOSED)
            throw new CashierNotOpenedException("Operação não permitida enquanto o caixa estiver fechado.");

        Account account = saleItem.getAccount();
        if(account == null ||  accountDAO.findOne(saleItem.getAccount().getId()).isEmpty())
            throw new EntityNotFoundException("Conta não encontrada.");

        if(saleItem == null ||  saleItemDAO.findOne(saleItem.getId()).isEmpty())
            throw new EntityNotFoundException("Item de Venda não encontrado.");

        if(account.getClosingTime() != null)
            throw new IllegalArgumentException("Conta já está fechada.");

        if(quantity <= 0)
            throw new IllegalArgumentException("Quantidade inválida.");

        if(!account.checkIfHasValueToPay()) {
            throw new IllegalArgumentException("Não existe valor a ser pago.");
        }

        if(!account.checkIfValueToPayIsValid(paymentAmount)) {
            throw new IllegalArgumentException("Valor a ser pago ultrapassa o valor total.");
        }

        saleItem.setPaidAmount(paymentAmount + saleItem.getPaidAmount());

        account.updatePaidBySaleItemAmount(paymentAmount);
        accountDAO.update(account);

        return saleItemDAO.update(saleItem);

    }
}


