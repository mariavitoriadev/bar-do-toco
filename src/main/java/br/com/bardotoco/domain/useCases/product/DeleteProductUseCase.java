package br.com.bardotoco.domain.useCases.product;

import br.com.bardotoco.domain.entities.product.Product;
import br.com.bardotoco.domain.useCases.utils.EntityNotFoundException;

public class DeleteProductUseCase {
    private ProductDAO productDAO;

    public DeleteProductUseCase(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public boolean delete(Integer id) {
        if(id == null ||  productDAO.findOne(id).isEmpty())
            throw new EntityNotFoundException("Produto não encontrado.");

        return productDAO.deleteByKey(id);
    }

    public boolean delete(Product product) {
        if(product == null ||  productDAO.findOne(product.getId()).isEmpty())
            throw new EntityNotFoundException("Produto não encontrado.");

        return productDAO.delete(product);
    }
}
