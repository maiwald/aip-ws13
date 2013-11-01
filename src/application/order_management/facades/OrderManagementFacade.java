package application.order_management.facades;

import application.order_management.business_logic.OrderCreator;
import application.order_management.data_access.dtos.OrderDTO;
import application.order_management.data_access.entities.Order;

public class OrderManagementFacade {

    OrderCreator orderCreator = new OrderCreator();

	public OrderDTO createOrder(int offerId) {
		Order order = orderCreator.createOrder(offerId);
		return order.createDTO();
	}
}
