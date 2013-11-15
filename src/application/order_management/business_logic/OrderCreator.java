package application.order_management.business_logic;

import application.order_management.data_access.dtos.OrderDTO;
import application.order_management.data_access.entities.Order;
import application.order_management.data_access.repositories.OrderRepository;
import application.production.facades.Production;

public class OrderCreator {

    private final Production production;
    private final OrderRepository orderRepository;

    public OrderCreator(OrderRepository orderRepository, Production production) {
        this.orderRepository = orderRepository;
        this.production = production;
    }

    public Order createOrder(int offerId) {
		Order order = orderRepository.createOrder(offerId);
        if(order != null) {
            OrderDTO orderDTO = order.createDTO();
            production.produceOrder(orderDTO);
        }

		return order;
	}
}