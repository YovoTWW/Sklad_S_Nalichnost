package com.example.sklad_s_nalichnost.controllers;

import com.example.sklad_s_nalichnost.DataList;
import com.example.sklad_s_nalichnost.MainApplication;
import com.example.sklad_s_nalichnost.models.Stock;
import com.example.sklad_s_nalichnost.models.Storage;
import com.example.sklad_s_nalichnost.repositories.ClientRepository;
import com.example.sklad_s_nalichnost.repositories.StockRepository;
import javafx.collections.FXCollections;
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
    private final StockRepository stockRepo = new StockRepository();

    @FXML
    public void initialize() {

        // Connect table columns to Stock getters
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        availableQuantityCol.setCellValueFactory(new PropertyValueFactory<>("availableQuantity"));
        deliveryPriceCol.setCellValueFactory(new PropertyValueFactory<>("deliveryPrice"));
        sellingPriceCol.setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));

        // Add data to table
        tableView.setItems(Storage.instance.getAvailableStock());
        refreshTable();
    }

    private void refreshTable() {
        if(!MainApplication.usesDB) {
            tableView.setItems(DataList.instance.BuyableStock);
        }
        else {
            tableView.setItems(
                    FXCollections.observableArrayList(stockRepo.getAll())
            );
        }
    }

    @FXML
    public void goBack(ActionEvent event) throws IOException {
        Parent homeView = FXMLLoader.load(getClass().getResource("/com/example/sklad_s_nalichnost/home-view.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(homeView,500,600));
        stage.show();
    }
}
