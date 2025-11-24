package com.example.sklad_s_nalichnost.controllers;

import com.example.sklad_s_nalichnost.DataList;
import com.example.sklad_s_nalichnost.models.Stock;
import com.example.sklad_s_nalichnost.models.Storage;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class SellStockController {

    @FXML
    private ComboBox<Stock> stockDropdown;

    @FXML
    private TextField nameField;

    @FXML
    private TextField quantityField;

    @FXML
    private Label maxLabel;

    @FXML
    private Button confirmButton;

    @FXML
    public void initialize() {

        // Fill dropdown with stock items
        stockDropdown.setItems(Storage.instance.getAvailableStock());

        // When user selects a stock
        stockDropdown.valueProperty().addListener((obs, old, selected) -> {
            if (selected != null) {
                nameField.setText(selected.getName());
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

        // Call your event here
        // --------------------------------------------------
        System.out.println("Selected: " + selected.getName() +
                " | Quantity: " + quantity);
        // --------------------------------------------------

        showInfo("You selected " + quantity + " of " + selected.getName());
    }

    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR, msg, ButtonType.OK);
        alert.showAndWait();
    }

    private void showInfo(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, msg, ButtonType.OK);
        alert.showAndWait();
    }
}
