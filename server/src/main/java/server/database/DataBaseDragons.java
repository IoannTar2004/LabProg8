package server.database;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.example.collections.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DataBaseDragons {
    private final Class<ProxyDragon> dragonClass = ProxyDragon.class;

    public void merge(ProxyDragon proxyDragon) {
        Session session = HibernateUtils.getDragonFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(proxyDragon);
        transaction.commit();
        session.close();

    }

    public void remove(ProxyDragon proxyDragon) {
        Session session = HibernateUtils.getDragonFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(proxyDragon);
        transaction.commit();
        session.close();
    }

    public List<ProxyDragon> getAll() {
        Session session = HibernateUtils.getDragonFactory().openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ProxyDragon> create = builder.createQuery(dragonClass);
        create.select(create.from(dragonClass));
        List<ProxyDragon> list = session.createQuery(create).getResultList();
        session.close();
        return list;
    }
}
