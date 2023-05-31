module com.example.client {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.hibernate.orm.core;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires jakarta.persistence;

    opens com.example.run to javafx.fxml;
    exports com.example.run;
    exports com.example.collections;
    opens com.example.controllers to javafx.fxml;
    opens com.example.collections to org.hibernate.orm.core;
}