module com.example.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires resources;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.run to javafx.fxml;
    exports com.example.run;
    exports com.example.controllers;
    opens com.example.controllers to javafx.fxml;
}