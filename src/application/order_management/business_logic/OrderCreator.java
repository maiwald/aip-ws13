package application.order_management.business_logic;

import application.order_management.data_access.dtos.OrderDTO;
import application.order_management.data_access.entities.Order;
import application.order_management.data_access.repositories.OrderRepository;
import application.production.data_access.repositories.ProductionOrderRepository;

public class OrderCreator {

    private ProductionOrderRepository productionOrderRepository = new ProductionOrderRepository();
    private OrderRepository orderRepository = new OrderRepository();

    public OrderCreator() {}

    public OrderCreator(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(int offerId) {
		Order order = orderRepository.createOrder(offerId);
        if(order != null) {
            OrderDTO orderDTO = order.createDTO();
            productionOrderRepository.createProductionOrder(orderDTO);
        }

		return order;
	}
}