module server {
    requires com.example.client;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;

    opens server.database to org.hibernate.orm.core;
}