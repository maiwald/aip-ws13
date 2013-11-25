package application.order_management.facades;

import application.order_management.data_access.dtos.OfferDTO;
import application.order_management.data_access.dtos.OrderDTO;

/**
 * User: felix_000
 * Date: 05.11.13
 * Time: 09:07
 */
public interface OrderManagement {
    OrderDTO createOrder(int offerId);
    OfferDTO createOffer();
    //CustomerDTO createCustomer(String name, String street, String houseNr, int postcode);
}
