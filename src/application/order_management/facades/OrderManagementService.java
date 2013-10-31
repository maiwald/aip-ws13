package application.order_management.facades;

import application.order_management.business_logic.OrderCreator;
import application.order_management.data_access.entities.Order;

public class OrderManagementService {

	public Order createOrder(int offerId) {
		OrderCreator o = new OrderCreator();
		return o.createOrder(offerId);
	}
}
