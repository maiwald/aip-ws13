package application.production.data_access.repositories;

import org.hibernate.Session;

import util.SessionHelper;
import application.order_management.data_access.dtos.OrderDTO;
import application.production.data_access.entities.ProductionOrder;

public class ProductionOrderRepository {

    public ProductionOrder createProductionOrder(OrderDTO orderDTO) {
        ProductionOrder productionOrder = ProductionOrder.fromOrderDTO(orderDTO);

        Session session = SessionHelper.getSession();
        session.save(productionOrder);

        return productionOrder;
    }
}
