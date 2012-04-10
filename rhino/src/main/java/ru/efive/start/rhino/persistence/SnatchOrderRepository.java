package ru.efive.start.rhino.persistence;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.efive.start.rhino.domain.SnatchOrder;
import ru.efive.start.rhino.domain.User;

import java.util.List;

@Repository
public class SnatchOrderRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public void addSnatchOrder(SnatchOrder snatchOrder) {
        sessionFactory.getCurrentSession().save(snatchOrder);
    }

    public void updateSnatchOrder(SnatchOrder snatchOrder) {
        sessionFactory.getCurrentSession().update(snatchOrder);
    }

    public SnatchOrder getSnatchOrder(long orderId) {
        Query query = sessionFactory.getCurrentSession().createQuery("from SnatchOrder where id = :id");
        query.setParameter("id", orderId);
        return (SnatchOrder) query.uniqueResult();
    }

    public List<SnatchOrder> getSnatchOrderList(User user) {
        String sqlQuery = "from SnatchOrder where user = :user order by created desc";
        Query query = sessionFactory.getCurrentSession().createQuery(sqlQuery);
        query.setParameter("user", user);
        return query.list();
    }

    public List<SnatchOrder> getSnatchOrderList() {
        String sqlQuery = "from SnatchOrder order by created desc";
        Query query = sessionFactory.getCurrentSession().createQuery(sqlQuery);
        return query.list();
    }
}
