package server.database;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;

import java.util.List;

/**
 * Class to get all registered users from database
 */

public class DataBaseUsers {

    public List<Users> getAll() {
        Session session = HibernateUtils.getUsersFactory().openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Users> create = builder.createQuery(Users.class);
        create.select(create.from(Users.class));

        List<Users> users = session.createQuery(create).getResultList();
        session.close();
        return users;
    }
}
