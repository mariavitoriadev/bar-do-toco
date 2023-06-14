package com.bardotoco.domain.useCases.product;

import com.bardotoco.domain.useCases.utils.DAO;
import com.bardotoco.domain.entities.product.Product;

import java.util.Optional;

public interface ProductDAO extends DAO<Product, Integer> {
    Optional<Product> findByName(String name);
}