package com.bardotoco.domain.useCases.product;

import com.bardotoco.domain.entities.cashier.Cashier;
import com.bardotoco.domain.entities.cashier.CashierStatus;
import com.bardotoco.domain.entities.product.Product;
import com.bardotoco.domain.useCases.utils.CashierNotClosedException;
import com.bardotoco.domain.useCases.utils.EntityAlreadyExistsException;
import com.bardotoco.domain.useCases.utils.Notification;
import com.bardotoco.domain.useCases.utils.Validator;

public class CreateProductUseCase {
    private ProductDAO productDAO;
    private Cashier cashier = Cashier.getInstance();

    public CreateProductUseCase(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public Integer insert(Product product) {
        if(cashier.getCashierStatus() == CashierStatus.OPENED)
            throw new CashierNotClosedException("Operação não permitida enquanto o caixa estiver aberto.");

        Validator<Product> validator = new ProductInputRequestValidator();
        Notification notification = validator.validate(product);

        if(notification.hasErros())
            throw new IllegalArgumentException(notification.errorMessage());

        String name = product.getName();
        if(!productDAO.findByName(name).isEmpty())
            throw new EntityAlreadyExistsException("Já existe outro produto com esse nome.");

        return productDAO.create(product);
    }
}
