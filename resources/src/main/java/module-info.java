module resources {
    requires java.sql;
    requires jakarta.persistence;
    exports org.example.collections;
    exports org.example.tools;
    exports org.example.transmission;
}