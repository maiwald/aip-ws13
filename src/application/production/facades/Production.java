package application.production.facades;

import application.materials_management.data_access.entities.PartDTO;
import application.order_management.data_access.dtos.OrderDTO;

/**
 * User: felix_000
 * Date: 05.11.13
 * Time: 09:09
 */
public interface Production {
    void produceOrder(OrderDTO orderDTO);

    void printWorkSchedule(PartDTO partDTO);
}
