module com.example.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires resources;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.run to javafx.fxml;
    exports com.example.run;
}