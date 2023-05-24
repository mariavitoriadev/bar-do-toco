package br.com.bardotoco.domain.useCases.account;

import br.com.bardotoco.domain.entities.account.Account;
import br.com.bardotoco.domain.useCases.utils.Notification;
import br.com.bardotoco.domain.useCases.utils.Validator;

public class AccountInputRequestValidator extends Validator<Account> {

    @Override
    public Notification validate(Account account) {
        Notification notification = new Notification();

        if (account == null) {
            notification.addError("Conta inválida.");
            return notification;
        }
        if(account.getOpeningTime() != null)
            notification.addError("Horário de abertura inválido.");
        if(account.getClientTable() != null)
            notification.addError("Mesa vazia ou inválida.");

        return notification;
    }
}

