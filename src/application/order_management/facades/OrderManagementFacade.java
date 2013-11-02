package application.order_management.facades;

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
        validateOfferId(offerId);
        Order order = orderCreator.createOrder(offerId);
        if(order == null) return null;
        return order.createDTO();
    }

    private void validateOfferId(int offerId) {
        if(offerId < 1)
            throw new InvalidOfferIdException();
    }

    public class InvalidOfferIdException extends RuntimeException {
    }
}