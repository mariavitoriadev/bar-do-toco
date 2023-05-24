package br.com.bardotoco.domain.useCases.product;

import br.com.bardotoco.domain.useCases.utils.DAO;
import br.com.bardotoco.domain.entities.product.Product;

import java.util.Optional;

public interface ProductDAO extends DAO<Product, Integer> {
    Optional<Product> findByName(String name);
}