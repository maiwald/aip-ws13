package application.order_management.facades;

import org.hibernate.Session;

import util.SessionHelper;
import application.order_management.business_logic.OrderCreator;
import application.order_management.data_access.dtos.OrderDTO;
import application.order_management.data_access.entities.Order;

public class OrderManagementFacade {

    OrderCreator orderCreator = new OrderCreator();

    public OrderDTO createOrder(int offerId) {
        Session session = SessionHelper.getSession();
        session.beginTransaction();

        Order order = orderCreator.createOrder(offerId);

        session.getTransaction().commit();
        return order.createDTO();
    }
}
