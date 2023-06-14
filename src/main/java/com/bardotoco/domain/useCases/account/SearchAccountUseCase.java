package com.bardotoco.domain.useCases.account;

import com.bardotoco.domain.entities.account.Account;

import java.util.List;

public class SearchAccountUseCase {
    private AccountDAO accountDAO;

    public SearchAccountUseCase(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public List<Account> findAll(){
        return accountDAO.findAll();
    }

}



