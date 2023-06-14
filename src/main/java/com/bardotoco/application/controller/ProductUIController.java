package com.bardotoco.application.controller;

import com.bardotoco.application.view.WindowLoader;
import com.bardotoco.domain.entities.product.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

import static com.bardotoco.application.main.Main.createProductUseCase;
import static com.bardotoco.application.main.Main.updateProductUseCase;

public class ProductUIController {

    @FXML
    private TextField txtName;
    @FXML
    private TextField txtPrice;

    private Product product;

    @FXML
    private void initialize(){
    }

    private void getEntityToView(){
        if (product == null) {
            product = new Product();
        }
        product.setName(txtName.getText());
        product.setPrice(Double.valueOf(txtPrice.getText()));
    }

    private void setEntityIntoView(){
        txtName.setText(product.getName());
        txtPrice.setText(String.valueOf(product.getPrice()));
    }

    public void saveOrUpdate(ActionEvent actionEvent) throws IOException {
        getEntityToView();
        if(product.getId() == null)
            createProductUseCase.insert(product);
        else
            updateProductUseCase.update(product);
        WindowLoader.setRoot("ProductManagementUI");
    }

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("ProductManagementUI");
    }

    public void setProduct(Product product) {
        if(product == null)
            throw new IllegalArgumentException("Product can not be null.");

        this.product = product;
        setEntityIntoView();
    }
}
