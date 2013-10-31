package application.production.data_access.entities;

import application.order_management.data_access.dtos.OrderDTO;

public class ProductionOrder {
	
	private OrderDTO orderDTO;
	private int partId;
	
	public ProductionOrder(OrderDTO orderDTO) {
		this.orderDTO = orderDTO;
		this.partId = 2349085;
	}
	
	public int getPartId() {
		return this.partId;
	}
	
}
