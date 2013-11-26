package application.order_management.business_logic;

import java.util.Date;

import application.order_management.data_access.entities.Offer;
import application.order_management.data_access.repositories.OfferRepository;


public class OfferCreator {

    private final OfferRepository offerRepository;
    
    public OfferCreator(OfferRepository offerRepository){
        this.offerRepository = offerRepository;
    }
    
    public Offer createOffer(Integer customerId, Integer partId, Date validUntil, double price ){
        return offerRepository.createOffer(customerId, partId, validUntil, price);
    }
}

