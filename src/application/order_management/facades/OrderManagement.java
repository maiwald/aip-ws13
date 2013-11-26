package application.order_management.facades;

import java.util.Date;

import application.order_management.data_access.dtos.OfferDTO;
import application.order_management.data_access.dtos.OrderDTO;

/**
 * User: felix_000
 * Date: 05.11.13
 * Time: 09:07
 */
public interface OrderManagement {
    OrderDTO createOrder(int offerId);
    OfferDTO createOffer(Integer customerId, Integer partId, Date validUntil, double price);
}
