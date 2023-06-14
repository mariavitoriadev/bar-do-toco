package com.bardotoco.domain.useCases.account;

import com.bardotoco.domain.entities.account.Account;
import com.bardotoco.domain.entities.cashier.Cashier;
import com.bardotoco.domain.entities.cashier.CashierStatus;
import com.bardotoco.domain.useCases.clientTable.ClientTableDAO;
import com.bardotoco.domain.useCases.utils.CashierNotOpenedException;
import com.bardotoco.domain.useCases.utils.EntityNotFoundException;

public class CreateAccountUseCase {
    private AccountDAO accountDAO;
    private ClientTableDAO clientTableDAO;
    private Cashier cashier = Cashier.getInstance();


    public CreateAccountUseCase(AccountDAO accountDAO, ClientTableDAO clientTableDAO) {
        this.accountDAO = accountDAO;
        this.clientTableDAO = clientTableDAO;
    }

    public Integer insert(Account account) {
        if(cashier.getCashierStatus() == CashierStatus.CLOSED)
            throw new CashierNotOpenedException("Operação não permitida enquanto o caixa estiver fechado.");

        if(clientTableDAO.findOne(account.getClientTable().getId()).isEmpty())
            throw new EntityNotFoundException("Mesa não encontrada.");

        account.getClientTable().setAccount(account);

        return accountDAO.create(account);
    }
}
