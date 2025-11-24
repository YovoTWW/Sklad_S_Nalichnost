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
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class SupplierController {

    @FXML
    private TableView tableView;
    @FXML
    private TableColumn<Supplier, String> nameCol;
    @FXML
    private TextField nameField;

    @FXML
    public void initialize() {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        refreshTable();
    }

    private void refreshTable() {
        tableView.setItems(DataList.instance.Suppliers);
    }

    @FXML
    public void addSupplier() {
        String name = nameField.getText();
        if(name.isBlank())
        {
            showError("Invalid Name", "Supplier name cannot be empty.");
            return;
        }
        DataList.instance.Suppliers.add(new Supplier(name));
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
