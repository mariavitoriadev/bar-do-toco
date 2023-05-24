package br.com.bardotoco.domain.useCases.product;

import br.com.bardotoco.domain.entities.cashier.Cashier;
import br.com.bardotoco.domain.entities.cashier.CashierStatus;
import br.com.bardotoco.domain.entities.product.Product;
import br.com.bardotoco.domain.useCases.utils.CashierNotClosedException;
import br.com.bardotoco.domain.useCases.utils.EntityNotFoundException;
import br.com.bardotoco.domain.useCases.utils.Notification;
import br.com.bardotoco.domain.useCases.utils.Validator;

public class UpdateProductUseCase {
    private ProductDAO productDAO;
    private Cashier cashier = Cashier.getInstance();

    public UpdateProductUseCase(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public boolean update(Product product) {
        if(cashier.getCashierStatus() == CashierStatus.OPENED)
            throw new CashierNotClosedException("Operação não permitida enquanto o caixa estiver aberto");

        Integer id = product.getId();
        if(productDAO.findOne(id).isEmpty())
            throw new EntityNotFoundException("Produto não encontrado.");

        Validator<Product> validator = new ProductInputRequestValidator();
        Notification notification = validator.validate(product);

        if(notification.hasErros())
            throw new IllegalArgumentException(notification.errorMessage());

        return productDAO.update(product);
    }
}

