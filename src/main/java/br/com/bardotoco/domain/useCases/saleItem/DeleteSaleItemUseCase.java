package main.java.br.com.bardotoco.domain.useCases.saleItem;

import main.java.br.com.bardotoco.domain.entities.saleitem.SaleItem;
import main.java.br.com.bardotoco.domain.useCases.utils.EntityNotFoundException;

public class DeleteSaleItemUseCase {
    private SaleItemDAO saleItemDAO;

    public DeleteSaleItemUseCase(SaleItemDAO saleItemDAO) {
        this.saleItemDAO = saleItemDAO;
    }

    public boolean delete(Integer id) {
        if(id == null ||  saleItemDAO.findOne(id).isEmpty())
            throw new EntityNotFoundException("Item de Venda não encontrado.");

        return saleItemDAO.deleteByKey(id);
    }

    public boolean delete(SaleItem saleItem) {
        if(saleItem == null ||  saleItemDAO.findOne(saleItem.getId()).isEmpty())
            throw new EntityNotFoundException("Item de Venda não encontrado.");

        return saleItemDAO.delete(saleItem);
    }
}
