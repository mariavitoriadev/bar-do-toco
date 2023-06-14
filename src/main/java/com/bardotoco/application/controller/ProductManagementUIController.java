package com.bardotoco.application.controller;

import com.bardotoco.application.view.WindowLoader;
import com.bardotoco.domain.entities.product.Product;
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

public class ProductManagementUIController {

    @FXML
    private TableView<Product> tableView;
    @FXML
    private TableColumn<Product, String> cName;
    @FXML
    private TableColumn<Product, String> cPrice;

    private ObservableList<Product> tableData;

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
        cName.setCellValueFactory(new PropertyValueFactory<>("name"));
        cPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    private void loadDataAndShow() {
        List<Product> products = searchProductUseCase.findAll();
        tableData.clear();
        tableData.addAll(products);
    }

    public void deleteProduct(ActionEvent actionEvent) {
        Product selectedItem = tableView.getSelectionModel().getSelectedItem();
        if(selectedItem != null) {
            deleteProductUseCase.delete(selectedItem);
            loadDataAndShow();
        }
    }

    public void editProduct(ActionEvent actionEvent) throws IOException {
        Product selectedItem = tableView.getSelectionModel().getSelectedItem();
        if(selectedItem != null) {
            WindowLoader.setRoot("ProductUI");
            ProductUIController controller = (ProductUIController) WindowLoader.getController();

            controller.setProduct(selectedItem);
        }
    }

    public void createProduct(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("ProductUI");
    }

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("MainUI");
    }
}
