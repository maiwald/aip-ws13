package application.order_management.business_logic;

import application.order_management.data_access.dtos.OrderDTO;
import application.order_management.data_access.entities.Order;
import application.order_management.data_access.repositories.OrderRepository;
import application.production.data_access.repositories.ProductionOrderRepository;

public class OrderCreator {
	public static Order createOrder(int offerId)
	{
		Order order = OrderRepository.createOrder(offerId);
		OrderDTO orderDTO = order.createDTO();
		ProductionOrderRepository.createProductionOrder(orderDTO); 
		return order;
	}
}
