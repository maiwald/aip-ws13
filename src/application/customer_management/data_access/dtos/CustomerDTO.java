package application.customer_management.data_access.dtos;

public class CustomerDTO {

    private final int customerID;
    private final String name;
    private final String street;
    private final String houseNr;
    private final int postcode;
    
    public CustomerDTO(int customerID, String name, String street, String houseNr, int postcode){
        this.customerID = customerID;
        this.name = name;
        this.street = street;
        this.houseNr = houseNr;
        this.postcode = postcode;
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getName() {
        return name;
    }

    public String getStreet() {
        return street;
    }

    public String getHouseNr() {
        return houseNr;
    }

    public int getPostcode() {
        return postcode;
    }
    
    
        
    
}
