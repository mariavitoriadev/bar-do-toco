package br.com.bardotoco.model.entities;

public class ClientTable {
    private int id;
    private Account account;

    public ClientTable(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
        if(account != null )
            this.account.setClientTable(this);
    }
}
