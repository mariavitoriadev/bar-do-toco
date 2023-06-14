package com.bardotoco.application.controller;

import static com.bardotoco.application.main.Main.*;

import com.bardotoco.application.view.WindowLoader;
import com.bardotoco.domain.entities.account.Account;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;

public class AccountManagementUIController {

       @FXML
    private TableView<Account> tableView;
    @FXML
    private TableColumn<Account, String> cOpening;
    @FXML
    private TableColumn<Account, String> cClosing;
    @FXML
    private TableColumn<Account, String> cTotalAmount;
    @FXML
    private TableColumn<Account, String> cPaidAmount;
    @FXML
    private TableColumn<Account, String> cTable;

    private ObservableList<Account> tableData;

    @FXML
    private void initialize(){
        bindTableViewToItemsList();
        bindColumnsToValueSources();
        loadDataAndShow();
    }

    private void bindTableViewToItemsList() {
        tableData = FXCollections.observableArrayList();
        tableView.setItems(tableData);
    }

    private void bindColumnsToValueSources() {
        cOpening.setCellValueFactory(new PropertyValueFactory<>("openingTime"));
        cClosing.setCellValueFactory(new PropertyValueFactory<>("closingTime"));
        cTotalAmount.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
        cPaidAmount.setCellValueFactory(new PropertyValueFactory<>("paidAmount"));
        cTable.setCellValueFactory(new PropertyValueFactory<>("clientTableId"));
    }

    private void loadDataAndShow() {
        List<Account> accounts = searchAccountUseCase.findAll();
        tableData.clear();
        tableData.addAll(accounts);
    }

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("MainUI");
    }

    public void createAccount(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("AccountUI");
    }

    public void closeAccount(ActionEvent actionEvent) {
        Account selectedItem = tableView.getSelectionModel().getSelectedItem();
        if(selectedItem != null) {
            closeAccountUseCase.closeAccount(selectedItem);
            loadDataAndShow();
        }
    }

    public void manageAccount(ActionEvent actionEvent) throws IOException {

        Account selectedItem = tableView.getSelectionModel().getSelectedItem();
        if(selectedItem != null) {
            WindowLoader.setRoot("SaleItemManagementUI");
            SaleItemManagementUIController controller = (SaleItemManagementUIController) WindowLoader.getController();

            controller.setAccount(selectedItem);
        }
    }

    public void payByValue(ActionEvent actionEvent) throws IOException {
        Account selectedItem = tableView.getSelectionModel().getSelectedItem();
        if(selectedItem != null) {
            WindowLoader.setRoot("AccountPaymentByValueUI");
            AccountPaymentByValueUIController controller = (AccountPaymentByValueUIController) WindowLoader.getController();
            controller.setAccount(selectedItem);
        }
    }

    public void generateAccountStatus(ActionEvent actionEvent) throws IOException {

        Account selectedItem = tableView.getSelectionModel().getSelectedItem();
        if(selectedItem != null) {
            WindowLoader.setRoot("AccountStatusUI");
            AccountStatusUIController controller = (AccountStatusUIController) WindowLoader.getController();
            controller.setAccount(selectedItem);
        }
    }
}
