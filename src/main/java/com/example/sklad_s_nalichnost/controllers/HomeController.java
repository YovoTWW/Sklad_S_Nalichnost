package com.example.sklad_s_nalichnost.controllers;

import com.example.sklad_s_nalichnost.models.PayDesk;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;

public class HomeController {

    @FXML
    protected void ShowStock(javafx.event.ActionEvent event) throws IOException{
        //Button_Show_Stock.setText("Available Stock:");
        Parent stockView = FXMLLoader.load(getClass().getResource("/com/example/sklad_s_nalichnost/stock-view.fxml"));

        // Get current stage
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Replace scene
        stage.setScene(new Scene(stockView,500,500));
        stage.show();
    }

    @FXML
    private Label outputText;

    @FXML
    public void ShowBalance() {
        outputText.setText("Current Balance :"+ PayDesk.instance.getBalance() + "$");
    }
}