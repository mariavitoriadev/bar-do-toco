package com.bardotoco.domain.useCases.account;

import com.bardotoco.domain.entities.account.Account;
import com.bardotoco.domain.entities.cashier.Cashier;
import com.bardotoco.domain.entities.cashier.CashierStatus;
import com.bardotoco.domain.entities.saleitem.SaleItem;
import com.bardotoco.domain.useCases.saleItem.SaleItemDAO;
import com.bardotoco.domain.useCases.utils.CashierNotOpenedException;
import com.bardotoco.domain.useCases.utils.EntityNotFoundException;

import java.util.List;

public class PaymentByValueUseCase {

    private AccountDAO accountDAO;
    private SaleItemDAO saleItemDAO;
    private Cashier cashier = Cashier.getInstance();

    public PaymentByValueUseCase(AccountDAO accountDAO, SaleItemDAO saleItemDAO) {
        this.accountDAO = accountDAO;
        this.saleItemDAO = saleItemDAO;
    }

    public boolean payByValue(Account account, double paymentAmount) {
        if(cashier.getCashierStatus() == CashierStatus.CLOSED)
            throw new CashierNotOpenedException("Operação não permitida enquanto o caixa estiver fechado.");

        if(account == null ||  accountDAO.findOne(account.getId()).isEmpty())
            throw new EntityNotFoundException("Conta não encontrada.");

        if(account.getClosingTime() != null)
            throw new IllegalArgumentException("Conta já está fechada.");

        if(!account.checkIfHasValueToPay()) {
           throw new IllegalArgumentException("Não existe valor a ser pago.");
        }

        if(!account.checkIfValueToPayIsValid(paymentAmount)) {
            throw new IllegalArgumentException("Valor a ser pago ultrapassa o valor total.");
        }

        account.updatePaidByValueAmount(paymentAmount);
        accountDAO.update(account);

        return accountDAO.update(account);
    }

}
