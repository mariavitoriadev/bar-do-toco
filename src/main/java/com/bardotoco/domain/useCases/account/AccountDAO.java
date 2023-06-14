package com.bardotoco.domain.useCases.account;

import com.bardotoco.domain.entities.account.Account;
import com.bardotoco.domain.useCases.utils.DAO;

import java.util.List;

public interface AccountDAO extends DAO<Account,Integer> {
    List<Account> findNotClosedAccounts();
}