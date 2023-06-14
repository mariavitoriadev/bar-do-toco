package com.bardotoco.domain.useCases.account;

import com.bardotoco.domain.entities.account.Account;
import com.bardotoco.domain.entities.cashier.Cashier;
import com.bardotoco.domain.entities.cashier.CashierStatus;
import com.bardotoco.domain.useCases.utils.CashierNotOpenedException;
import com.bardotoco.domain.useCases.utils.EntityNotFoundException;

public class CheckPaidAmountUseCase {
    private AccountDAO accountDAO;
    private Cashier cashier = Cashier.getInstance();

    public CheckPaidAmountUseCase( AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public boolean checkPaidAmount(Account account) {
        if(cashier.getCashierStatus() == CashierStatus.CLOSED)
            throw new CashierNotOpenedException("Operação não permitida enquanto o caixa estiver fechado.");

        if(account.getClosingTime() != null)
            throw new IllegalArgumentException("Conta já está fechada.");

        if(account == null ||  accountDAO.findOne(account.getId()).isEmpty())
            throw new EntityNotFoundException("Conta não encontrada.");

        double paidAmount = account.getPaidByValueAmount() + account.getPaidBySaleItemAmount();

        if(paidAmount != account.getTotalAmount())
            throw new RuntimeException("Valor pago insuficiente.");

        return true;
    }
}
