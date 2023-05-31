package main.java.br.com.bardotoco.domain.useCases.cashier;

import main.java.br.com.bardotoco.domain.entities.account.Account;
import main.java.br.com.bardotoco.domain.entities.cashier.Cashier;
import main.java.br.com.bardotoco.domain.entities.cashier.CashierStatus;
import main.java.br.com.bardotoco.domain.useCases.account.AccountDAO;
import main.java.br.com.bardotoco.domain.useCases.utils.CashierNotOpenedException;

public class CloseCashierUseCase {
    private Cashier cashier = Cashier.getInstance();
    private AccountDAO accountDAO;

    public CloseCashierUseCase(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public Cashier closeCashier() {

        if(cashier.getCashierStatus() == CashierStatus.CLOSED)
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
