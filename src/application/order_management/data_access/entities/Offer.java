package application.order_management.data_access.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import application.materials_management.data_access.dtos.PartDTO;
import application.materials_management.data_access.entities.Part;
import application.order_management.data_access.dtos.OfferDTO;

@Entity
@Table(name = "mpsOffer")
public class Offer {

    private Integer offerId;
    private Integer customerId;
    private Map<PartDTO, Integer> partlist;
    private Date validUntil;
    private Date offerDate;
    private double price;

    public Offer(Integer customerId, Map<PartDTO, Integer> partlist, Date validUntil, double price) {
        this.customerId = customerId;
        this.partlist = partlist;
        this.validUntil = validUntil;

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            offerDate = formatter.parse(formatter.format(new Date()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.price = price;

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getOfferId() {
        return offerId;
    }

    public void setOfferId(Integer offerId) {
        this.offerId = offerId;
    }

    @Column(nullable = false)
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @Column
    public Map<PartDTO, Integer> getPartlist() {
        return partlist;
    }

    public void setPartlist(Map<PartDTO, Integer> partlist) {
        this.partlist = partlist;
    }

    @Column
    public Date getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }

    @Column
    public Date getOfferDate() {
        return offerDate;
    }

    public void setOfferDate(Date offerDate) {
        this.offerDate = offerDate;
    }

    @Column
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public OfferDTO createDTO() {
        return new OfferDTO(this.customerId, this.partlist, this.offerDate, this.validUntil, this.price);
    }
}
