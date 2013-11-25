package application.customer_management.facade;

import application.customer_management.data_access.dtos.CustomerDTO;

public interface CustomerManagement {
    CustomerDTO createCustomer(String name, String street, String houseNr, int postcode);
}
