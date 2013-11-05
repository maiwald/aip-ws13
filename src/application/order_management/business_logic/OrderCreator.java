package application.order_management.business_logic;

import application.order_management.data_access.dtos.OrderDTO;
import application.order_management.data_access.entities.Order;
import application.order_management.data_access.repositories.OrderRepository;
import application.production.facades.Production;
import application.production.facades.ProductionFacade;

public class OrderCreator {

    private Production production = new ProductionFacade();
    private OrderRepository orderRepository = new OrderRepository();

    public OrderCreator() {}

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