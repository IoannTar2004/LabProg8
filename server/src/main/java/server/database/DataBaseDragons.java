package server.database;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.example.collections.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import server.manager.ObjectsManager;
import java.util.List;

public class DataBaseDragons {
    private final Class<Dragon> dragonClass = Dragon.class;

    public void merge(Dragon dragon) {
        Session session = HibernateUtils.getDragonFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(dragon);
        transaction.commit();
        session.close();

        new ObjectsManager().add(dragon);

    }

    public void remove(Dragon dragon) {
        Session session = HibernateUtils.getDragonFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(dragon);
        transaction.commit();
        session.close();
    }

    public List<Dragon> getAll() {
        Session session = HibernateUtils.getDragonFactory().openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Dragon> create = builder.createQuery(dragonClass);
        create.select(create.from(dragonClass));
        List<Dragon> list = session.createQuery(create).getResultList();
        session.close();
        return list;
    }
}
