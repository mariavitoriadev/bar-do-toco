package main.java.br.com.bardotoco.domain.useCases.product;

import main.java.br.com.bardotoco.domain.entities.product.Product;
import main.java.br.com.bardotoco.domain.useCases.utils.Validator;
import main.java.br.com.bardotoco.domain.useCases.utils.Notification;

public class ProductInputRequestValidator extends Validator<Product> {

    @Override
    public Notification validate(Product product) {
        Notification notification = new Notification();

        if (product == null) {
            notification.addError("Produto nulo.");
            return notification;
        }
        if(nullOrEmpty(product.getName()))
            notification.addError("Nome do produto nulo ou vazio.");
        if(product.getPrice() == 0)
            notification.addError("Preço do produto vazio.");

        return notification;
    }
}

