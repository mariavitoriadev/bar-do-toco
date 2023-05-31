package main.java.br.com.bardotoco.domain.useCases.product;

import main.java.br.com.bardotoco.domain.entities.cashier.Cashier;
import main.java.br.com.bardotoco.domain.entities.cashier.CashierStatus;
import main.java.br.com.bardotoco.domain.entities.product.Product;
import main.java.br.com.bardotoco.domain.useCases.utils.CashierNotClosedException;
import main.java.br.com.bardotoco.domain.useCases.utils.EntityAlreadyExistsException;
import main.java.br.com.bardotoco.domain.useCases.utils.Notification;
import main.java.br.com.bardotoco.domain.useCases.utils.Validator;

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
