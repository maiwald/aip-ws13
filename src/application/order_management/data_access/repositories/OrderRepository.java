package application.order_management.data_access.repositories;

import application.order_management.data_access.entities.Order;

public class OrderRepository {
    public Order createOrder(int offerId) {
        return new Order();
    }
}
