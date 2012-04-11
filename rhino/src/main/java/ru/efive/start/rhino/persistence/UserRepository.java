package ru.efive.start.rhino.persistence;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.efive.start.rhino.domain.User;

import java.util.Date;
import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public void addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
        sessionFactory.getCurrentSession().flush();
    }

    public List<User> getUserList() {
        return sessionFactory.getCurrentSession().createQuery("from User").list();
    }

    public User getUserByEmail(String email) {
        Query query = sessionFactory.getCurrentSession().createQuery("from User where email = :email");
        query.setParameter("email", email);
        return (User)query.uniqueResult();
    }

    public void updateUser(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    public void deleteUser(String email) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "delete from User where email = :email");
        query.setParameter("email", email);
        query.executeUpdate();
    }
}
