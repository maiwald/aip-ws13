package application.production.data_access.repositories;

import application.order_management.data_access.entities.Order;
import application.production.data_access.entities.ProductionOrder;

public class ProductionOrderRepository {
	
	static public ProductionOrder createProductionOrder(Order order) {
		return new ProductionOrder();
	}

}
