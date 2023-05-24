package br.com.bardotoco.domain.useCases.account;

import br.com.bardotoco.domain.entities.account.Account;
import br.com.bardotoco.domain.useCases.utils.EntityNotFoundException;

public class CheckPaidAmountUseCase {
    private AccountDAO accountDAO;

    public CheckPaidAmountUseCase( AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public boolean checkPaidAmount(Account account) {
        if(account == null ||  accountDAO.findOne(account.getId()).isEmpty())
            throw new EntityNotFoundException("Conta não encontrada.");

        double paidAmount = account.getPaidByValueAmount() + account.getPaidBySaleItemAmount();

        if(paidAmount != account.getTotalAmount())
            throw new RuntimeException("Valor pago insuficiente.");

        return true;
    }
}
