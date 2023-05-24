package br.com.bardotoco.domain.useCases.account;

import br.com.bardotoco.domain.entities.account.Account;
import br.com.bardotoco.domain.useCases.utils.DAO;

import java.util.List;

public interface AccountDAO extends DAO<Account,Integer> {
    List<Account> findNotClosedAccounts();
}