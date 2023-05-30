package server.database;

import org.example.collections.Dragon;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public abstract class HibernateUtils {
    private static SessionFactory sessionDragonFactory;
    private static SessionFactory sessionUsersFactory;

    private HibernateUtils() {}

    public static SessionFactory getDragonFactory() {
        if (sessionDragonFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Dragon.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionDragonFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {e.printStackTrace();}
        }
        return sessionDragonFactory;
    }

    public static SessionFactory getUsersFactory() {
        if (sessionUsersFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Users.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionUsersFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {e.printStackTrace();}
        }
        return sessionUsersFactory;
    }
}
