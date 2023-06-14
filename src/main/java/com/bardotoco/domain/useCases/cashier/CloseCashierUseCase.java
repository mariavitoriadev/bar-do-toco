package com.bardotoco.domain.useCases.cashier;

import com.bardotoco.domain.entities.account.Account;
import com.bardotoco.domain.entities.cashier.Cashier;
import com.bardotoco.domain.entities.cashier.CashierStatus;
import com.bardotoco.domain.useCases.account.AccountDAO;
import com.bardotoco.domain.useCases.utils.CashierNotOpenedException;

public class CloseCashierUseCase {
    private Cashier cashier = Cashier.getInstance();
    private AccountDAO accountDAO;

    public CloseCashierUseCase(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public Cashier closeCashier() {

        if(!cashier.isCashierOpened())
            throw new CashierNotOpenedException("Caixa ja está fechado.");


        for (Account item : accountDAO.findNotClosedAccounts()) {
            System.out.println(item.getId().toString());
        }

        if(!accountDAO.findNotClosedAccounts().isEmpty())
            throw new IllegalArgumentException("Caixa ainda possui contas em aberto.");

        cashier.closeCashier();

        return cashier;
    }
}
