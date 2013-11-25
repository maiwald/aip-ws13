package application.order_management.data_access.dtos;

import java.util.Date;
import java.util.Map;

import application.materials_management.data_access.entities.Part;

public class OfferDTO {

    private Integer customerId;
    private Map<Part, Integer> partlist;
    private Date offerDate;
    private Date validUntil;
    private double price;

    public OfferDTO(Integer customerId, Map<Part, Integer> partlist, Date offerDate, Date validUntil, double price) {
        this.customerId = customerId;
        this.partlist = partlist;
        this.offerDate = offerDate;
        this.validUntil = validUntil;
        this.price = price;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public Map<Part, Integer> getPartlist() {
        return partlist;
    }

    public Date getOfferDate() {
        return offerDate;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public double getPrice() {
        return price;
    }

}
