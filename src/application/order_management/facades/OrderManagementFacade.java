package application.order_management.facades;

import org.hibernate.Session;

import util.SessionHelper;
import application.order_management.business_logic.OrderCreator;
import application.order_management.data_access.dtos.OrderDTO;
import application.order_management.data_access.entities.Order;
import application.order_management.data_access.repositories.OrderRepository;
import application.production.facades.Production;

public class OrderManagementFacade implements OrderManagement {

    private final OrderCreator orderCreator;

    public OrderManagementFacade(Production production) {
        OrderRepository orderRepository = new OrderRepository();
        this.orderCreator = new OrderCreator(orderRepository, production);
    }

    @Override
    public OrderDTO createOrder(int offerId) {
        Session session = this.getSession();
        session.beginTransaction();

        validateOfferId(offerId);
        Order order = orderCreator.createOrder(offerId);
        if (order == null)
            return null;

        session.getTransaction().commit();
        return order.createDTO();
    }

    private void validateOfferId(int offerId) {
        try {
            if (offerId < 1)
                throw new InvalidOfferIdError();
        } catch (InvalidOfferIdError invalidOfferIdError) {
            invalidOfferIdError.printStackTrace();
        }
    }

    public class InvalidOfferIdError extends Exception {
        private static final long serialVersionUID = 1L;
    }

    private Session getSession() {
        return SessionHelper.getSession();
    }
}