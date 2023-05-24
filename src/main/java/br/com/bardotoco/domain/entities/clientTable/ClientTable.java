package br.com.bardotoco.domain.entities.clientTable;
import br.com.bardotoco.domain.entities.account.Account;

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
}
