package br.com.bardotoco.domain.useCases.product;

import br.com.bardotoco.domain.entities.cashier.Cashier;
import br.com.bardotoco.domain.entities.cashier.CashierStatus;
import br.com.bardotoco.domain.entities.product.Product;
import br.com.bardotoco.domain.useCases.utils.CashierNotClosedException;
import br.com.bardotoco.domain.useCases.utils.EntityNotFoundException;

public class DeleteProductUseCase {
    private ProductDAO productDAO;
    private Cashier cashier = Cashier.getInstance();

    public DeleteProductUseCase(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public boolean delete(Integer id) {
        if(cashier.getCashierStatus() == CashierStatus.OPENED)
            throw new CashierNotClosedException("Operação não permitida enquanto o caixa estiver aberto");

        if(id == null ||  productDAO.findOne(id).isEmpty())
            throw new EntityNotFoundException("Produto não encontrado.");

        return productDAO.deleteByKey(id);
    }

    public boolean delete(Product product) {
        if(cashier.getCashierStatus() == CashierStatus.OPENED)
            throw new CashierNotClosedException("Operação não permitida enquanto o caixa estiver aberto");

        if(product == null ||  productDAO.findOne(product.getId()).isEmpty())
            throw new EntityNotFoundException("Produto não encontrado.");

        return productDAO.delete(product);
    }
}
