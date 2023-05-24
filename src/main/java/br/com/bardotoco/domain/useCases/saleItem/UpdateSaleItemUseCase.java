package br.com.bardotoco.domain.useCases.saleItem;

import br.com.bardotoco.domain.entities.cashier.Cashier;
import br.com.bardotoco.domain.entities.cashier.CashierStatus;
import br.com.bardotoco.domain.entities.product.Product;
import br.com.bardotoco.domain.entities.saleitem.SaleItem;
import br.com.bardotoco.domain.useCases.product.ProductDAO;
import br.com.bardotoco.domain.useCases.utils.CashierNotOpenedException;
import br.com.bardotoco.domain.useCases.utils.EntityNotFoundException;

public class UpdateSaleItemUseCase {
    private SaleItemDAO saleItemDAO;
    private ProductDAO productDAO;

    public UpdateSaleItemUseCase(SaleItemDAO saleItemDAO, ProductDAO productDAO) {
        this.saleItemDAO = saleItemDAO;
        this.productDAO = productDAO;
    }

    public boolean update(SaleItem saleItem, Product product, int quantity) {

        Cashier cashier = Cashier.getInstance();
        if(cashier.getCashierStatus() == CashierStatus.CLOSED)
            throw new CashierNotOpenedException("Operação não permitida, caixa está fechado.");

        if(saleItemDAO.findOne(saleItem.getId()).isEmpty())
            throw new EntityNotFoundException("Item de Venda não encontrado.");

        if(productDAO.findOne(product.getId()).isEmpty())
            throw new EntityNotFoundException("Produto não encontrado.");

        if(quantity <= 0)
            throw new IllegalArgumentException("Quantidade inválida.");

        saleItem.setProduct(product);
        saleItem.setQuantity(quantity);
        saleItem.updateTotalAmount();

        return saleItemDAO.update(saleItem);

    }
}
