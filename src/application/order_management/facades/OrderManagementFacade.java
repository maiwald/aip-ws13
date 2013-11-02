package application.order_management.facades;

import org.hibernate.Session;

import util.SessionHelper;
import application.order_management.business_logic.OrderCreator;
import application.order_management.data_access.dtos.OrderDTO;
import application.order_management.data_access.entities.Order;

public class OrderManagementFacade {

    OrderCreator orderCreator = new OrderCreator();

    public OrderManagementFacade(OrderCreator orderCreator) {
        this.orderCreator = orderCreator;
    }

    public OrderManagementFacade() {}

    public OrderDTO createOrder(int offerId) {
        Session session = SessionHelper.getSession();
        session.beginTransaction();

        validateOfferId(offerId);
        Order order = orderCreator.createOrder(offerId);
        if(order == null) return null;

        session.getTransaction().commit();
        return order.createDTO();
    }

    private void validateOfferId(int offerId) {
        try {
            if(offerId < 1)
                throw new InvalidOfferIdError();
        } catch (InvalidOfferIdError invalidOfferIdError) {
            invalidOfferIdError.printStackTrace();
        }
    }

    public class InvalidOfferIdError extends Exception {
    }
}