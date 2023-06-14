package com.bardotoco.application.controller;

import com.bardotoco.application.view.WindowLoader;
import com.bardotoco.domain.entities.clientTable.ClientTable;
import static com.bardotoco.application.main.Main.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;


public class ClientTableManagementUIController {

    @FXML
    private TableView<ClientTable> tableView;
    @FXML
    private TableColumn<ClientTable, String> cTable;

    private ObservableList<ClientTable> tableData;

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
        cTable.setCellValueFactory(new PropertyValueFactory<>("id"));
    }

    private void loadDataAndShow() {
        List<ClientTable> clientTables = searchClientTableUseCase.findAll();
        tableData.clear();
        tableData.addAll(clientTables);
    }
    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("MainUI");
    }

    public void deleteClientTable(ActionEvent actionEvent) {
        ClientTable selectedItem = tableView.getSelectionModel().getSelectedItem();
        if(selectedItem != null) {
            deleteClientTableUseCase.delete(selectedItem);
            loadDataAndShow();
        }
    }

    public void createClientTable(ActionEvent actionEvent) {
        ClientTable clientTable = new ClientTable();
        createClientTableUseCase.insert(clientTable);
        loadDataAndShow();
    }
}
