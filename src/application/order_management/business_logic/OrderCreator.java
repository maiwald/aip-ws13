package application.order_management.business_logic;

import application.order_management.data_access.dtos.OrderDTO;
import application.order_management.data_access.entities.Order;
import application.order_management.data_access.repositories.OrderRepository;
import application.production.facades.ProductionFacade;

public class OrderCreator {

    private ProductionFacade productionFacade = new ProductionFacade();
    private OrderRepository orderRepository = new OrderRepository();

    public OrderCreator() {}

    public OrderCreator(OrderRepository orderRepository, ProductionFacade productionFacade) {
        this.orderRepository = orderRepository;
        this.productionFacade = productionFacade;
    }

    public Order createOrder(int offerId) {
		Order order = orderRepository.createOrder(offerId);
        if(order != null) {
            OrderDTO orderDTO = order.createDTO();
            productionFacade.produceOrder(orderDTO);
        }

		return order;
	}
}