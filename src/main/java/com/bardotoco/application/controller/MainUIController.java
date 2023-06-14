package com.bardotoco.application.controller;

import com.bardotoco.domain.entities.cashier.Cashier;
import com.bardotoco.application.view.WindowLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

import static com.bardotoco.application.main.Main.closeCashierUseCase;
import static com.bardotoco.application.main.Main.openCashierUseCase;


public class MainUIController {

    @FXML
    private Label lbStatus;
    @FXML
    private Button btnToggleCashier;
    @FXML
    private Button btnManageProducts;
    @FXML
    private Button btnManageAccounts;

    private Cashier cashier = Cashier.getInstance();

    @FXML
    private void initialize(){
        updateCashierStatus();
    }

    public void manageTables(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("ClientTableManagementUI");
    }

    public void manageProducts(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("ProductManagementUI");
    }

    public void manageAccounts(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("AccountManagementUI");
    }

    public void updateCashierStatus(){
        if(cashier.isCashierOpened()) {
            lbStatus.setText("Aberto");
            configureOpenedCashierMode();
        } else {
            lbStatus.setText("Fechado");
            configureClosedCashierMode();
        }
    }

    public void toggleCashier(ActionEvent actionEvent) {
        if(cashier.isCashierOpened()) {
            closeCashierUseCase.closeCashier();

        } else {
            openCashierUseCase.openCashier();
        }

        updateCashierStatus();
    }

    private void configureOpenedCashierMode() {
        btnManageProducts.setVisible(false);
        btnManageAccounts.setVisible(true);
        btnToggleCashier.setText("Fechar Caixa");
    }

    private void configureClosedCashierMode() {
        btnManageProducts.setVisible(true);
        btnManageAccounts.setVisible(false);
        btnToggleCashier.setText("Abrir Caixa");
    }

}
