package com.bardotoco.domain.useCases.product;

import com.bardotoco.domain.entities.product.Product;
import com.bardotoco.domain.useCases.utils.Validator;

import java.util.List;
import java.util.Optional;

public class SearchProductUseCase {
    private ProductDAO productDAO;

    public SearchProductUseCase(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public Optional<Product> findOne(Integer id){
        if (id == null)
            throw new IllegalArgumentException("ID do produto nulo.");
        return productDAO.findOne(id);
    }

    public Optional<Product> findByName(String name){
        if(Validator.nullOrEmpty(name))
            throw new IllegalArgumentException("Nome do produto nulo ou vazio.");
        return productDAO.findByName(name);
    }

    public List<Product> findAll(){
        return productDAO.findAll();
    }
}
