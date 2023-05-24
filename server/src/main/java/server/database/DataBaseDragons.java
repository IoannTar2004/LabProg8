package server.database;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.example.collections.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DataBaseDragons {
    private final Class<Dragon> dragonClass = Dragon.class;

    public void merge(Dragon dragon) {
        Session session = HibernateUtils.getSessionFactory(dragonClass).openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(dragon);
        transaction.commit();
        session.close();
    }

    public void remove(Dragon dragon) {
        Session session = HibernateUtils.getSessionFactory(dragonClass).openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(dragon);
        transaction.commit();
        session.close();
    }

    public List<Dragon> getAll() {
        Session session = HibernateUtils.getSessionFactory(dragonClass).openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Dragon> create = builder.createQuery(dragonClass);
        create.select(create.from(dragonClass));
        return session.createQuery(create).getResultList();
    }
}
