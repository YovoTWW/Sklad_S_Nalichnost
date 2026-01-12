package com.example.sklad_s_nalichnost.controllers;

import com.example.sklad_s_nalichnost.DataList;
import com.example.sklad_s_nalichnost.models.Stock;
import com.example.sklad_s_nalichnost.models.Storage;
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
import java.util.UUID;

public class SellStockController {

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
        stockDropdown.setItems(Storage.instance.getAvailableStock());

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

    @FXML
    public void onConfirm() {
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
        // Call your event here
        // --------------------------------------------------
        System.out.println("Selected: " + selected.getName() +
                " | Quantity: " + quantity);
        // --------------------------------------------------

        showInfo("You selected " + quantity + " of " + selected.getName());
        //DataList.instance.SellStock(stockId,quantity);
        resetFields();
    }

    private void resetFields() {
        stockDropdown.getSelectionModel().clearSelection();  // clear dropdown selection
        idField.clear();                                   // clear name field
        quantityField.clear();                               // clear quantity input
        maxLabel.setText("(max: -)");                        // reset max label
        confirmButton.setVisible(false);                     // hide confirm button
    }

    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR, msg, ButtonType.OK);
        alert.showAndWait();
    }

    private void showInfo(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, msg, ButtonType.OK);
        alert.showAndWait();
    }

    @FXML
    public void goBack(ActionEvent event) throws IOException {
        Parent homeView = FXMLLoader.load(getClass().getResource("/com/example/sklad_s_nalichnost/client-view.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(homeView,500,500));
        stage.show();
    }
}
