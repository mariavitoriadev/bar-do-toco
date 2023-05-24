package br.com.bardotoco.domain.useCases.product;

import br.com.bardotoco.domain.entities.product.Product;
import br.com.bardotoco.domain.useCases.utils.EntityNotFoundException;
import br.com.bardotoco.domain.useCases.utils.Notification;
import br.com.bardotoco.domain.useCases.utils.Validator;

public class UpdateProductUseCase {
    private ProductDAO productDAO;

    public UpdateProductUseCase(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public boolean update(Product product) {

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

