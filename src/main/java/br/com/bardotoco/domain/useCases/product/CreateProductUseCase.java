package br.com.bardotoco.domain.useCases.product;

import br.com.bardotoco.domain.entities.product.Product;
import br.com.bardotoco.domain.useCases.utils.EntityAlreadyExistsException;
import br.com.bardotoco.domain.useCases.utils.Notification;
import br.com.bardotoco.domain.useCases.utils.Validator;

public class CreateProductUseCase {
    private ProductDAO productDAO;

    public CreateProductUseCase(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public Integer insert(Product product) {

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
