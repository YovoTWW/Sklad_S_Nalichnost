package com.example.sklad_s_nalichnost.controllers;

import com.example.sklad_s_nalichnost.DataList;
import com.example.sklad_s_nalichnost.MainApplication;
import com.example.sklad_s_nalichnost.models.Client;
import com.example.sklad_s_nalichnost.repositories.ClientRepository;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

public class ClientController {

    @FXML
    private TableView<Client> tableView;
    @FXML
    private TableColumn<Client, UUID> idCol;
    @FXML
    private TableColumn<Client, String> nameCol;
    @FXML
    private TableColumn<Client, Void> sellCol;
    @FXML
    private TextField nameField;

    private final ClientRepository clientRepo = new ClientRepository();

    @FXML
    public void initialize() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        sellCol.setCellFactory(col -> new TableCell<>() {

            private final Button btn = new Button("Sell Stock to Client");

            {
                btn.setOnAction(event -> {
                    Client client = getTableView().getItems().get(getIndex());
                    sellToClient(client);// your method
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
        if(!MainApplication.usesDB) {
            tableView.setItems(DataList.instance.Clients);
        }
        else {
            tableView.setItems(
                    FXCollections.observableArrayList(clientRepo.getAll())
            );
        }
    }

    @FXML
    public void addClient() {
            String name = nameField.getText();
            if(name.isBlank())
            {
                showError("Invalid Name", "Client name cannot be empty.");
                return;
            }
        if(!MainApplication.usesDB) {
            DataList.instance.Clients.add(new Client(name));
        }
        else {
            clientRepo.add(new Client(UUID.randomUUID(), name));
            refreshTable();
            nameField.clear();
        }
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void sellToClient(Client client){
        try {
            // Save selected buyer
            DataList.instance.setCurrentBuyer(client);

            // Load sell-stock-view.fxml
            Parent sellView = FXMLLoader.load(getClass().getResource("/com/example/sklad_s_nalichnost/sell-stock-view.fxml"));

            // Get current stage
            Stage stage = (Stage) tableView.getScene().getWindow();

            // Switch scene
            stage.setScene(new Scene(sellView));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            showError("Error", "Failed to open sell stock view.");
        }
    }

    @FXML
    public void goBack(ActionEvent event) throws IOException {
        Parent homeView = FXMLLoader.load(getClass().getResource("/com/example/sklad_s_nalichnost/home-view.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(homeView,320,240));
        stage.show();
    }

}
