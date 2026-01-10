module com.example.sklad_s_nalichnost {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.desktop;
    requires java.sql;

    opens com.example.sklad_s_nalichnost to javafx.fxml;
    exports com.example.sklad_s_nalichnost;
    exports com.example.sklad_s_nalichnost.controllers;
    opens com.example.sklad_s_nalichnost.controllers to javafx.fxml;
    opens com.example.sklad_s_nalichnost.models to javafx.base;
}
