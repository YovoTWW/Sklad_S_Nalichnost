package com.example.sklad_s_nalichnost.controllers;

import com.example.sklad_s_nalichnost.models.Stock;
import com.example.sklad_s_nalichnost.models.Storage;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class StockController {
    @FXML
    private TableView tableView;
    @FXML
    private TableColumn<Stock, String> nameCol;
    @FXML
    private TableColumn<Stock, Integer> availableQuantityCol;
    @FXML
    private TableColumn<Stock, Double> deliveryPriceCol;
    @FXML
    private TableColumn<Stock, Double> sellingPriceCol;

    private Storage storage;

    @FXML
    public void initialize() {
        // Create storage and sample data
        storage = new Storage();
        storage.addStock(new Stock("Apples", 1.2, 2.0, 100));
        storage.addStock(new Stock("Bananas", 0.8, 1.5, 80));

        // Connect table columns to Stock getters
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        availableQuantityCol.setCellValueFactory(new PropertyValueFactory<>("availableQuantity"));
        deliveryPriceCol.setCellValueFactory(new PropertyValueFactory<>("deliveryPrice"));
        sellingPriceCol.setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));

        // Add data to table
        tableView.setItems(storage.getAvailableStock());
    }

    @FXML
    public void goBack(ActionEvent event) throws IOException {
        Parent homeView = FXMLLoader.load(getClass().getResource("/com/example/sklad_s_nalichnost/home-view.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(homeView,320,240));
        stage.show();
    }
}
