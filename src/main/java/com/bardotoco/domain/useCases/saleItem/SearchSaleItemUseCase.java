package com.bardotoco.domain.useCases.saleItem;

import com.bardotoco.domain.entities.account.Account;
import com.bardotoco.domain.entities.saleitem.SaleItem;

import java.util.List;

public class SearchSaleItemUseCase {
    private SaleItemDAO saleItemDAO;

    public SearchSaleItemUseCase(SaleItemDAO saleItemDAO) {
        this.saleItemDAO = saleItemDAO;
    }

    public List<SaleItem> findAll(){
        return saleItemDAO.findAll();
    }

    public List<SaleItem> findByAccount(Account account){
        return saleItemDAO.findByAccount(account);
    }
}
