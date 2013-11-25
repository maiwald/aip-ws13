package application.customer_management.data_access.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import application.customer_management.data_access.dtos.CustomerDTO;


@Entity
@Table(name = "mpsCustomer")
public class Customer {

    private int customerid;
    private String name;
    private String street;
    private String houseNr;
    private int postcode;
    
    
    public Customer(String name, String street, String houseNr, int postcode){
        this.name = name;
        this.street = street;
        this.houseNr = houseNr;
        this.postcode = postcode;
        
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)    
    public int getCustomerid() {
        return customerid;
    }
    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }
    
    @Column
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    @Column
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    
    @Column
    public String getHouseNr() {
        return houseNr;
    }
    public void setHouseNr(String houseNr) {
        this.houseNr = houseNr;
    }
    
    @Column
    public int getPostcode() {
        return postcode;
    }
    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }
    
    public CustomerDTO createCustomerDTO(){
        return new CustomerDTO(getCustomerid(), getName(), getStreet(), getHouseNr(), getPostcode());
    }
    
}
