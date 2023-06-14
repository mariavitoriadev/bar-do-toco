package com.bardotoco.application.controller;

import com.bardotoco.application.view.WindowLoader;
import com.bardotoco.domain.entities.account.Account;
import com.bardotoco.domain.entities.saleitem.SaleItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.bardotoco.application.main.Main.searchAccountUseCase;
import static com.bardotoco.application.main.Main.searchSaleItemUseCase;

public class AccountStatusUIController {

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

    @FXML
    private Label lbOpeningHour;
    @FXML
    private Label lbClosingHour;
    @FXML
    private Label lbTotalValue;
    @FXML
    private Label lbPaidValue;

    private ObservableList<SaleItem> tableData;

    Account account;

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

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatOpening = account.getOpeningTime().format(formatter);
        String formatClosing = account.getClosingTime() != null ? account.getClosingTime().format(formatter) : "";


        lbOpeningHour.setText(formatOpening);
        lbClosingHour.setText(formatClosing);
        lbTotalValue.setText(String.valueOf(account.getTotalAmount()));
        lbPaidValue.setText(String.valueOf(account.getPaidAmount()));

        tableData.clear();
        tableData.addAll(saleItems);
    }

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("AccountManagementUI");
    }

    public void setAccount(Account account) {
        if(account == null)
            throw new IllegalArgumentException("Product can not be null.");

        this.account = account;

        bindColumnsToValueSources();
        loadDataAndShow();
    }
}
