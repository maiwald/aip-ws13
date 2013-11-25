package application.customer_management.business_logic;

import application.customer_management.data_access.entities.Customer;
import application.customer_management.repository.CustomerRepository;

public class CustomerCreator {
    private final CustomerRepository customerRepository; 
    
    CustomerCreator(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }
    
    public Customer createCustomer(String name, String street, String houseNr, int postcode){
       return customerRepository.createCustomer(name,street,houseNr,postcode);
        
    }
}
