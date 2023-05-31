package main.java.br.com.bardotoco.domain.useCases.saleItem;

import main.java.br.com.bardotoco.domain.entities.cashier.Cashier;
import main.java.br.com.bardotoco.domain.entities.cashier.CashierStatus;
import main.java.br.com.bardotoco.domain.entities.saleitem.SaleItem;
import main.java.br.com.bardotoco.domain.useCases.account.AccountDAO;
import main.java.br.com.bardotoco.domain.useCases.product.ProductDAO;
import main.java.br.com.bardotoco.domain.useCases.utils.*;

public class CreateSaleItemUseCase {
    private SaleItemDAO saleItemDAO;
    private ProductDAO productDAO;
    private AccountDAO accountDAO;

    public CreateSaleItemUseCase(SaleItemDAO saleItemDAO, ProductDAO productDAO, AccountDAO accountDAO) {
        this.saleItemDAO = saleItemDAO;
        this.productDAO = productDAO;
        this.accountDAO = accountDAO;
    }

    public Integer insert(SaleItem saleItem) {

        Cashier cashier = Cashier.getInstance();
        if(cashier.getCashierStatus() == CashierStatus.CLOSED)
            throw new CashierNotOpenedException("Operação não permitida, caixa está fechado.");

        if(productDAO.findOne(saleItem.getProduct().getId()).isEmpty())
            throw new EntityNotFoundException("Produto não encontrado.");

        if(accountDAO.findOne(saleItem.getAccount().getId()).isEmpty())
            throw new EntityNotFoundException("Conta não encontrada.");

        if(saleItem.getQuantity() <= 0)
            throw new IllegalArgumentException("Quantidade inválida.");

        saleItem.getAccount().updateTotalAmount(saleItem.getTotalAmount());

        return saleItemDAO.create(saleItem);
    }
}
