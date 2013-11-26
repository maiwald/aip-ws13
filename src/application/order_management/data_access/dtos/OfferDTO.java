package application.order_management.data_access.dtos;

import java.io.Serializable;
import java.util.Date;

public class OfferDTO implements Serializable {

    private Integer customerId;
    private Integer partId;
    private Date offerDate;
    private Date validUntil;
    private double price;

    public OfferDTO(Integer customerId, Integer partId, Date offerDate, Date validUntil, double price) {
        this.customerId = customerId;
        this.partId = partId;
        this.offerDate = offerDate;
        this.validUntil = validUntil;
        this.price = price;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public Integer getPartId() {
        return partId;
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
