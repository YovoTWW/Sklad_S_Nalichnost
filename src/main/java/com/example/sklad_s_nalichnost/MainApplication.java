package com.example.sklad_s_nalichnost;

import com.example.sklad_s_nalichnost.models.PayDesk;
import com.example.sklad_s_nalichnost.models.Storage;
import com.example.sklad_s_nalichnost.tests.DatabaseConnectionTest;
import com.example.sklad_s_nalichnost.tests.Tester;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, SQLException {
        Tester.testAll();
        Storage.instance.Initialize();
        PayDesk.instance.Initialize();
        DataList.instance.initialize();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Output");
        stage.setScene(scene);
        stage.show();
    }

    public static boolean usesDB = false;

    public static void main(String[] args) {
        launch();
    }
}