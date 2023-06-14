package com.bardotoco.application.controller;

import com.bardotoco.application.view.WindowLoader;
import com.bardotoco.domain.entities.account.Account;
import com.bardotoco.domain.entities.product.Product;
import com.bardotoco.domain.entities.saleitem.SaleItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.List;

import static com.bardotoco.application.main.Main.*;

public class SaleItemUIController {

    @FXML
    private ComboBox<Product> cbProduct;
    @FXML
    private TextField txtQuantity;

    SaleItem saleItem;
    Account account;

    @FXML
    private void initialize(){
        List<Product> products = searchProductUseCase.findAll();
        cbProduct.getItems().setAll(products);
    }

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("SaleItemManagementUI");
        SaleItemManagementUIController controller = (SaleItemManagementUIController) WindowLoader.getController();
        controller.setAccount(account);
    }

    private void setEntityIntoView(){
        txtQuantity.setText(String.valueOf(saleItem.getQuantity()));
        cbProduct.setValue(saleItem.getProduct());
    }

    private void getEntityToView(){
        if (saleItem == null) {
            saleItem = new SaleItem();
        }

        saleItem.setName(cbProduct.getValue().getName());
        saleItem.setQuantity(Integer.valueOf(txtQuantity.getText()));
        saleItem.setProduct(cbProduct.getValue());
        saleItem.setAccount(account);
    }

    public void saveOrUpdate(ActionEvent actionEvent) throws IOException {
        getEntityToView();
        if(saleItem.getId() == null)
            createSaleItemUseCase.insert(saleItem);
        else
            updateSaleItemUseCase.update(saleItem);

        WindowLoader.setRoot("SaleItemManagementUI");
        SaleItemManagementUIController controller = (SaleItemManagementUIController) WindowLoader.getController();
        controller.setAccount(account);

    }

    public void setAccount(Account account) {
        if(account == null)
            throw new IllegalArgumentException("Account can not be null.");

        this.account = account;
    }

    public void setSaleItem(SaleItem saleItem) {
        if(saleItem == null)
            throw new IllegalArgumentException("Sale Item can not be null.");

        this.saleItem = saleItem;
        setEntityIntoView();
    }
}
