package application.production.data_access.repositories;

import application.order_management.data_access.dtos.OrderDTO;
import application.production.data_access.entities.ProductionOrder;

public class ProductionOrderRepository {
	
	public ProductionOrder createProductionOrder(OrderDTO orderDTO) {
		return new ProductionOrder(orderDTO);
	}

}
