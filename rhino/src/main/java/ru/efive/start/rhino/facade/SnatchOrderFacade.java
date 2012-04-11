package ru.efive.start.rhino.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.efive.start.rhino.domain.SnatchOrder;
import ru.efive.start.rhino.domain.User;
import ru.efive.start.rhino.persistence.SnatchOrderRepository;

import java.util.Date;
import java.util.List;

@Service
public class SnatchOrderFacade {
    @Autowired
    private SnatchOrderRepository snatchOrderRepository;

    @Autowired
    UserFacade userFacade;

    @Transactional
    public void addOrder(String des, String fields, String site, String email) {
        SnatchOrder snatchOrder = new SnatchOrder();
        snatchOrder.setCreated(new Date());
        snatchOrder.setDescription(des);
        snatchOrder.setFields(fields);
        snatchOrder.setSite(site);
        snatchOrder.setUser(userFacade.getByEmail(email));
        snatchOrder.setProcessed(0);
        snatchOrderRepository.addSnatchOrder(snatchOrder);
    }

    @Transactional(readOnly = true)
    public List<SnatchOrder> getUsersOrder(String email) {
        return snatchOrderRepository.getSnatchOrderList(userFacade.getByEmail(email));
    }

    @Transactional(readOnly = true)
    public List<SnatchOrder> getAllOrders() {
        return snatchOrderRepository.getSnatchOrderList();
    }

    @Transactional(readOnly = true)
    public SnatchOrder getOrder(long id) {
        return snatchOrderRepository.getSnatchOrder(id);
    }

    @Transactional
    public void updateOrder(long id, long processed) {
        SnatchOrder snatchOrder = snatchOrderRepository.getSnatchOrder(id);
        snatchOrder.setProcessed(processed);
        snatchOrderRepository.updateSnatchOrder(snatchOrder);
    }

    @Transactional
    public void deleteOrder(long id) {
        snatchOrderRepository.deleteSnatchOrder(id);
    }

    @Transactional
    public void deleteOrders(User user) {
        snatchOrderRepository.deleteSnatchOrderList(user);
    }

}
