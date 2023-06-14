package com.bardotoco.application.controller;

import com.bardotoco.application.view.WindowLoader;
import com.bardotoco.domain.entities.saleitem.SaleItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

import static com.bardotoco.application.main.Main.paymentBySaleItemUseCase;

public class SaleItemPaymentUIController {

    @FXML
    private TextField txtQuantity;

    SaleItem saleItem;

    public void setSaleItem(SaleItem saleItem) {
        if(saleItem == null)
            throw new IllegalArgumentException("Sale Item can not be null.");

        this.saleItem = saleItem;
    }

    public void paymentBySaleItem(ActionEvent actionEvent) throws IOException {
        paymentBySaleItemUseCase.payBySaleItem(saleItem, Double.valueOf(txtQuantity.getText()));
        WindowLoader.setRoot("SaleItemManagementUI");
        SaleItemManagementUIController controller = (SaleItemManagementUIController) WindowLoader.getController();
        controller.setAccount(saleItem.getAccount());
    }

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("SaleItemManagementUI");
        SaleItemManagementUIController controller = (SaleItemManagementUIController) WindowLoader.getController();
        controller.setAccount(saleItem.getAccount());
    }
}
