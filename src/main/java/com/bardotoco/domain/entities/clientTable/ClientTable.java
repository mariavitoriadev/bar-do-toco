package com.bardotoco.domain.entities.clientTable;
import com.bardotoco.domain.entities.account.Account;

public class ClientTable {
    private Integer id;
    private Account account;

    public ClientTable() {
        this(0);
    }

    public ClientTable(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
