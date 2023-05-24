package br.com.bardotoco.domain.useCases.account;

import br.com.bardotoco.domain.entities.account.Account;
import br.com.bardotoco.domain.useCases.utils.DAO;

public interface AccountDAO extends DAO<Account,Integer> {
}