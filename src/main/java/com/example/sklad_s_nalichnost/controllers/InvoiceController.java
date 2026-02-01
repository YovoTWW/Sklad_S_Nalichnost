package com.example.sklad_s_nalichnost.controllers;

import com.example.sklad_s_nalichnost.DataList;
import com.example.sklad_s_nalichnost.models.Stock;
import com.example.sklad_s_nalichnost.models.Storage;
import com.example.sklad_s_nalichnost.models.Supplier;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.UUID;

public class InvoiceController {
    @FXML
    private TableView tableView;

    @FXML
    private TableColumn<Supplier, UUID> idCol;
    @FXML
    private TableColumn<Supplier, String> textCol;

    @FXML
    public void initialize() {

        // Connect table columns to Invoice getters
        idCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
        textCol.setCellValueFactory(new PropertyValueFactory<>("InvoiceText"));

        // Add data to table
        tableView.setItems(DataList.instance.Invoices);
    }

    @FXML
    public void goBack(ActionEvent event) throws IOException {
        Parent homeView = FXMLLoader.load(getClass().getResource("/com/example/sklad_s_nalichnost/home-view.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(homeView,500,600));
        stage.show();
    }
}
