package main.java.br.com.bardotoco.domain.useCases.saleItem;

import main.java.br.com.bardotoco.domain.entities.account.Account;
import main.java.br.com.bardotoco.domain.entities.saleitem.SaleItem;
import main.java.br.com.bardotoco.domain.useCases.utils.DAO;

import java.util.List;

public interface SaleItemDAO extends DAO<SaleItem,Integer> {
    List<SaleItem> findByAccount(Account account);
}
