package com.example.sklad_s_nalichnost.controllers;

import com.example.sklad_s_nalichnost.DataList;
import com.example.sklad_s_nalichnost.MainApplication;
import com.example.sklad_s_nalichnost.models.PayDesk;
import com.example.sklad_s_nalichnost.models.Stock;
import com.example.sklad_s_nalichnost.models.Storage;
import com.example.sklad_s_nalichnost.repositories.ClientRepository;
import com.example.sklad_s_nalichnost.repositories.PaydeskRepository;
import com.example.sklad_s_nalichnost.repositories.StockRepository;
import com.example.sklad_s_nalichnost.repositories.SupplierStockRepository;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

public class BuyStockController {

    @FXML
    private ComboBox<Stock> stockDropdown;

    @FXML
    private TextField idField;

    @FXML
    private TextField quantityField;

    @FXML
    private Label maxLabel;

    @FXML
    private Button confirmButton;

    private final StockRepository stockRepo = new StockRepository();
    private final PaydeskRepository paydeskRepo = new PaydeskRepository();
    private final SupplierStockRepository supplierStockRepo = new SupplierStockRepository();

    @FXML
    public void initialize() {

        // Convert Stock object to a readable name in the dropdown
        stockDropdown.setConverter(new StringConverter<>() {
            @Override
            public String toString(Stock stock) {
                return stock != null ? stock.getName() : "";
            }

            @Override
            public Stock fromString(String s) {
                return null; // not used
            }
        });

        // Fill dropdown with stock items
        stockDropdown.setItems(DataList.instance.BuyableStock);
        refreshTable();

        // When user selects a stock
        stockDropdown.valueProperty().addListener((obs, old, selected) -> {
            if (selected != null) {
                idField.setText(selected.getId().toString());
                maxLabel.setText("(max: " + selected.getAvailableQuantity() + ")");
                confirmButton.setVisible(true);
                quantityField.clear();
            }
        });

        // Validate quantity input dynamically
        quantityField.textProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue.isBlank()) return;

            if (!newValue.matches("\\d+")) {
                quantityField.setText(oldValue);
                return;
            }

            int max = stockDropdown.getValue().getAvailableQuantity();
            int value = Integer.parseInt(newValue);

            if (value < 1) {
                quantityField.setText("1");
            } else if (value > max) {
                quantityField.setText(String.valueOf(max));
            }
        });
    }

    private void refreshTable() {
        if(!MainApplication.usesDB) {
            stockDropdown.setItems(DataList.instance.BuyableStock);
        }
        else {
            stockDropdown.setItems(
                    FXCollections.observableArrayList(stockRepo.findAllBySupplier(DataList.instance.currentSeller))
            );
        }
    }

    @FXML
    public void onConfirm() throws SQLException {
        Stock selected = stockDropdown.getValue();

        if (selected == null) {
            showError("Please select a stock item.");
            return;
        }

        if (quantityField.getText().isBlank()) {
            showError("Enter a quantity.");
            return;
        }

        int quantity = Integer.parseInt(quantityField.getText());
        UUID stockId = selected.getId();

        System.out.println("Selected: " + selected.getName() +
                " | Quantity: " + quantity);
        // --------------------------------------------------
        if(!MainApplication.usesDB) {
            DataList.instance.BuyStock(stockId, quantity);
        }
        else
        {
            int deliveryPrice = stockRepo.getDeliveryPrice(stockId);
            double balance = paydeskRepo.getBalance();

            if(balance >= deliveryPrice * (double)quantity) {
                int supplierQuantity = supplierStockRepo.getSupplierStockQuantity(DataList.instance.currentSeller.getId(), stockId);
                int availableQuantity = stockRepo.getQuantity(stockId);
                stockRepo.updateQuantity(stockId, availableQuantity + quantity);
                paydeskRepo.updateBalance(balance - deliveryPrice * (double)quantity);
                supplierStockRepo.updateSupplierStock(DataList.instance.currentSeller.getId(), stockId, supplierQuantity - quantity);
            }
        }
        resetFields();
    }

    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR, msg, ButtonType.OK);
        alert.showAndWait();
    }

    private void resetFields() {
        stockDropdown.getSelectionModel().clearSelection();  // clear dropdown selection
        idField.clear();                                   // clear name field
        quantityField.clear();                               // clear quantity input
        maxLabel.setText("(max: -)");                        // reset max label
        confirmButton.setVisible(false);                     // hide confirm button
    }

    @FXML
    public void goBack(ActionEvent event) throws IOException {
        Parent homeView = FXMLLoader.load(getClass().getResource("/com/example/sklad_s_nalichnost/supplier-view.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(homeView,500,600));
        stage.show();
    }
}
