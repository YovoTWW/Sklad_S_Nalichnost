package com.example.sklad_s_nalichnost.controllers;

import com.example.sklad_s_nalichnost.DataList;
import com.example.sklad_s_nalichnost.models.Client;
import com.example.sklad_s_nalichnost.models.Stock;
import com.example.sklad_s_nalichnost.models.Storage;
import com.example.sklad_s_nalichnost.models.Supplier;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.UUID;

public class SupplierController {

    @FXML
    private TableView<Supplier> tableView;
    @FXML
    private TableColumn<Supplier, UUID> idCol;
    @FXML
    private TableColumn<Supplier, String> nameCol;
    @FXML
    private TableColumn<Supplier, Void> buyCol;
    @FXML
    private TextField nameField;

    @FXML
    public void initialize() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        buyCol.setCellFactory(col -> new TableCell<>() {

            private final Button btn = new Button("Buy Stock from Supplier");

            {
                btn.setOnAction(event -> {
                    Supplier supplier = getTableView().getItems().get(getIndex());
                    buyFromSupplier(supplier);// your method
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btn);
                }
            }

        });
        refreshTable();
    }

    private void refreshTable() {
        tableView.setItems(DataList.instance.Suppliers);
    }

    @FXML
    public void buyFromSupplier(Supplier supplier){
        try {
            // Save selected seller
            //DataList.instance.setCurrentSeller(supplier);

            // Load buy-stock-view.fxml
            Parent buyView = FXMLLoader.load(getClass().getResource("/com/example/sklad_s_nalichnost/buy-stock-view.fxml"));

            // Get current stage
            Stage stage = (Stage) tableView.getScene().getWindow();

            // Switch scene
            stage.setScene(new Scene(buyView));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            showError("Error", "Failed to open buy stock view.");
        }
    }

    @FXML
    public void addSupplier() {
        String name = nameField.getText();
        if(name.isBlank())
        {
            showError("Invalid Name", "Supplier name cannot be empty.");
            return;
        }
        //DataList.instance.Suppliers.add(new Supplier(name));
        refreshTable();
        nameField.clear();
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void goBack(ActionEvent event) throws IOException {
        Parent homeView = FXMLLoader.load(getClass().getResource("/com/example/sklad_s_nalichnost/home-view.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(homeView,320,240));
        stage.show();
    }
}
