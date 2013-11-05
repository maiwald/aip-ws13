package application.order_management.facades;

import application.order_management.data_access.dtos.OrderDTO;

/**
 * Created with IntelliJ IDEA.
 * User: felix_000
 * Date: 05.11.13
 * Time: 09:07
 * To change this template use File | Settings | File Templates.
 */
public interface OrderManagement {
    OrderDTO createOrder(int offerId);
}
