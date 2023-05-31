package main.java.br.com.bardotoco.domain.useCases.product;

import main.java.br.com.bardotoco.domain.useCases.utils.DAO;
import main.java.br.com.bardotoco.domain.entities.product.Product;

import java.util.Optional;

public interface ProductDAO extends DAO<Product, Integer> {
    Optional<Product> findByName(String name);
}