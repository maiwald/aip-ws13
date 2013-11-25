package application.customer_management.repository;

import org.hibernate.Session;

import util.SessionHelper;
import application.customer_management.data_access.entities.Customer;

public class CustomerRepository {

    public Customer createCustomer(String name, String street, String houseNr, int postcode) {
        Customer customer = new Customer(name, street, houseNr, postcode);
       
        this.getSession().save(customer);

        return customer;
    }

    private Session getSession() {
        return SessionHelper.getSession();
    }

    
}
