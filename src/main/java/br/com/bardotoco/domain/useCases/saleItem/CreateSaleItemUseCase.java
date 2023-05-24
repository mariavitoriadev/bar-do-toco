package br.com.bardotoco.domain.useCases.saleItem;

import br.com.bardotoco.domain.entities.account.Account;
import br.com.bardotoco.domain.entities.cashier.Cashier;
import br.com.bardotoco.domain.entities.cashier.CashierStatus;
import br.com.bardotoco.domain.entities.product.Product;
import br.com.bardotoco.domain.entities.saleitem.SaleItem;
import br.com.bardotoco.domain.useCases.account.AccountDAO;
import br.com.bardotoco.domain.useCases.product.ProductDAO;
import br.com.bardotoco.domain.useCases.utils.*;

public class CreateSaleItemUseCase {
    private SaleItemDAO saleItemDAO;
    private ProductDAO productDAO;
    private AccountDAO accountDAO;

    public CreateSaleItemUseCase(SaleItemDAO saleItemDAO, ProductDAO productDAO, AccountDAO accountDAO) {
        this.saleItemDAO = saleItemDAO;
        this.productDAO = productDAO;
        this.accountDAO = accountDAO;
    }

    public Integer insert(Product product, int quantity, Account account) {

        Cashier cashier = Cashier.getInstance();
        if(cashier.getCashierStatus() == CashierStatus.CLOSED)
            throw new CashierNotOpenedException("Operação não permitida, caixa está fechado.");

        if(productDAO.findOne(product.getId()).isEmpty())
            throw new EntityNotFoundException("Produto não encontrado.");

        if(accountDAO.findOne(account.getId()).isEmpty())
            throw new EntityNotFoundException("Conta não encontrada.");

        if(quantity <= 0)
            throw new IllegalArgumentException("Quantidade inválida.");


        SaleItem saleItem = new SaleItem(product.getName(), quantity);
        saleItem.setProduct(product);
        saleItem.setAccount(account);

        saleItem.updateTotalAmount();
        account.updateTotalAmount(saleItem.getTotalAmount());

        return saleItemDAO.create(saleItem);
    }
}
