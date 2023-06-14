package com.bardotoco.application.controller;

import com.bardotoco.application.view.WindowLoader;
import com.bardotoco.domain.entities.account.Account;
import com.bardotoco.domain.entities.clientTable.ClientTable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.io.IOException;
import java.util.List;

import static com.bardotoco.application.main.Main.*;

public class AccountUIController {

    @FXML
    private ComboBox<ClientTable> cbClientTable;

    Account account;

    @FXML
    private void initialize(){
        List<ClientTable> clientTables = searchClientTableUseCase.findAll();
        cbClientTable.getItems().setAll(clientTables);
    }

    private void getEntityToView(){
        if (account == null) {
            account = new Account();
        }

        account.setClientTable(cbClientTable.getValue());
    }

    public void saveOrUpdate(ActionEvent actionEvent) throws IOException {
        getEntityToView();
        createAccountUseCase.insert(account);

        WindowLoader.setRoot("AccountManagementUI");

    }

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("AccountManagementUI");
    }
}
