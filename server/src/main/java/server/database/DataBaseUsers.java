package server.database;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.example.collections.Dragon;
import org.hibernate.Session;

import java.util.List;

public class DataBaseUsers {

    private final Class<Users> usersClass = Users.class;

    public List<Users> getAll() {
        Session session = HibernateUtils.getSessionFactory(usersClass).openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Users> create = builder.createQuery(usersClass);
        create.select(create.from(usersClass));
        return session.createQuery(create).getResultList();
    }
}
