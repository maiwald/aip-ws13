package application.order_management.business_logic;

import application.order_management.data_access.entities.Order;
import application.order_management.data_access.repositories.OrderRepository;
import application.production.data_access.entities.ProductionOrder;
import application.production.data_access.repositories.ProductionOrderRepository;

public class OrderCreator {
	public Order createOrder(int offerId)
	{
		Order order = OrderRepository.createOrder(offerId);
		ProductionOrder productionOrder = ProductionOrderRepository.createProductionOrder(order); 
		return order;
		
	}
}
