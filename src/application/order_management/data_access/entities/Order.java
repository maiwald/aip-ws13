package application.order_management.data_access.entities;

import application.order_management.data_access.dtos.OrderDTO;

public class Order {
	
	int orderId;
	final int offerId;

	public Order(int offerId) {
		this.orderId = 2873; 
		this.offerId = offerId;
	}
	
	public OrderDTO createDTO() {
		return new OrderDTO(this.offerId);
	}
}
