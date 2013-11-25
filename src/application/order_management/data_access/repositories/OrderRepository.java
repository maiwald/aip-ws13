package application.order_management.data_access.repositories;

import org.hibernate.Session;

import util.SessionHelper;
import application.order_management.data_access.entities.Order;

public class OrderRepository {
    
    public Order createOrder(int offerId) {
        Order order = new Order();
        order.setOfferId(offerId);

        this.getSession().save(order);

        return order;
    }

    private Session getSession() {
        return SessionHelper.getSession();
    }
}
