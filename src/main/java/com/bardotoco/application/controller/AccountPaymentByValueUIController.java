package com.bardotoco.application.controller;

import com.bardotoco.application.view.WindowLoader;
import com.bardotoco.domain.entities.account.Account;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

import static com.bardotoco.application.main.Main.paymentByValueUseCase;

public class AccountPaymentByValueUIController {

    @FXML
    private TextField txtValue;

    Account account;

    public void payByValue(ActionEvent actionEvent) throws IOException {
        paymentByValueUseCase.payByValue(account, Double.valueOf(txtValue.getText()));
        WindowLoader.setRoot("AccountManagementUI");
    }

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("AccountManagementUI");
    }

    public void setAccount(Account account) {
        if(account == null)
            throw new IllegalArgumentException("Product can not be null.");

        this.account = account;
    }
}
