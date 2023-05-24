package br.com.bardotoco.domain.useCases.account;

import br.com.bardotoco.domain.entities.account.Account;
import br.com.bardotoco.domain.useCases.utils.Notification;
import br.com.bardotoco.domain.useCases.utils.Validator;

public class CreateAccountUseCase {
    private AccountDAO accountDAO;

    public CreateAccountUseCase(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public Integer insert(Account account) {

        Validator<Account> validator = new AccountInputRequestValidator();
        Notification notification = validator.validate(account);

        account.getClientTable().setAccount(account);

        return accountDAO.create(account);
    }
}
