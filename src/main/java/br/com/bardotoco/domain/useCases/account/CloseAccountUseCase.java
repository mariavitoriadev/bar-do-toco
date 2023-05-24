package br.com.bardotoco.domain.useCases.account;

import br.com.bardotoco.domain.entities.account.Account;
import br.com.bardotoco.domain.entities.cashier.Cashier;
import br.com.bardotoco.domain.entities.cashier.CashierStatus;
import br.com.bardotoco.domain.useCases.utils.CashierNotOpenedException;
import br.com.bardotoco.domain.useCases.utils.EntityNotFoundException;

import java.time.LocalDateTime;

public class CloseAccountUseCase {
    private AccountDAO accountDAO;
    private Cashier cashier = Cashier.getInstance();

    public CloseAccountUseCase(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public boolean closeAccount(Account account) {
        if(cashier.getCashierStatus() == CashierStatus.CLOSED)
            throw new CashierNotOpenedException("Operação não permitida enquanto o caixa estiver fechado.");

        if(account == null ||  accountDAO.findOne(account.getId()).isEmpty())
            throw new EntityNotFoundException("Conta não encontrada.");

        if(account.getClosingTime() != null)
            throw new IllegalArgumentException("Conta já está fechada.");

        CheckPaidAmountUseCase checkPaidAmount = new CheckPaidAmountUseCase(accountDAO);
        if(!checkPaidAmount.checkPaidAmount(account))
            throw new IllegalArgumentException("Conta ainda possui débitos.");

        account.setClosingTime(LocalDateTime.now());

        return accountDAO.update(account);
    }

}

