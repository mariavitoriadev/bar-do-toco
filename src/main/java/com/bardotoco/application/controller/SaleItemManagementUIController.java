package com.bardotoco.application.controller;
import static com.bardotoco.application.main.Main.*;

import com.bardotoco.application.view.WindowLoader;
import com.bardotoco.domain.entities.account.Account;
import com.bardotoco.domain.entities.saleitem.SaleItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;


public class SaleItemManagementUIController {

    @FXML
    private TableView<SaleItem> tableView;
    @FXML
    private TableColumn<SaleItem, String> cName;
    @FXML
    private TableColumn<SaleItem, String> cQuantity;
    @FXML
    private TableColumn<SaleItem, String> cTotalAmount;
    @FXML
    private TableColumn<SaleItem, String> cPaidAmount;

    private ObservableList<SaleItem> tableData;

    private Account account;

    @FXML
    private void initialize(){
        bindTableViewToItemsList();
    }

    private void bindTableViewToItemsList() {
        tableData = FXCollections.observableArrayList();
        tableView.setItems(tableData);
    }

    private void bindColumnsToValueSources() {
        cName.setCellValueFactory(new PropertyValueFactory<>("name"));
        cQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        cTotalAmount.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
        cPaidAmount.setCellValueFactory(new PropertyValueFactory<>("paidAmount"));
    }

    private void loadDataAndShow() {
        List<SaleItem> saleItems = searchSaleItemUseCase.findByAccount(account);
        tableData.clear();
        tableData.addAll(saleItems);
    }

    public void setAccount(Account account) {
        if(account == null)
            throw new IllegalArgumentException("Account can not be null.");

        this.account = account;

        bindColumnsToValueSources();
        loadDataAndShow();
    }

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("AccountManagementUI");
    }

    public void deleteSaleItem(ActionEvent actionEvent) {
        SaleItem selectedItem = tableView.getSelectionModel().getSelectedItem();
        if(selectedItem != null) {
            deleteSaleItemUseCase.delete(selectedItem);
            loadDataAndShow();
        }
    }

    public void editSaleItem(ActionEvent actionEvent) throws IOException {
        SaleItem selectedItem = tableView.getSelectionModel().getSelectedItem();
        if(account != null && selectedItem != null) {
            WindowLoader.setRoot("SaleItemUI");

            SaleItemUIController controller = (SaleItemUIController) WindowLoader.getController();

            controller.setSaleItem(selectedItem);
            controller.setAccount(account);
        }

    }

    public void createSaleItem(ActionEvent actionEvent) throws IOException {

        if(account != null) {
            WindowLoader.setRoot("SaleItemUI");

            SaleItemUIController controller = (SaleItemUIController) WindowLoader.getController();

            controller.setAccount(account);
        }
    }

    public void paySaleItem(ActionEvent actionEvent) throws IOException {
        SaleItem selectedItem = tableView.getSelectionModel().getSelectedItem();

        WindowLoader.setRoot("SaleItemPaymentUI");
        SaleItemPaymentUIController controller = (SaleItemPaymentUIController) WindowLoader.getController();

        controller.setSaleItem(selectedItem);
    }


}
