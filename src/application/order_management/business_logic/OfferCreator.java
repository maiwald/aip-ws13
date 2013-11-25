package application.order_management.business_logic;

import java.util.Date;
import java.util.Map;

import application.materials_management.data_access.entities.Part;
import application.order_management.data_access.entities.Offer;
import application.order_management.data_access.repositories.OfferRepository;


public class OfferCreator {

    private static OfferRepository offerRepository;
    
    public OfferCreator(OfferRepository offerRepository){
        this.offerRepository = offerRepository;
    }
    
    public Offer createOffer(Integer customerId, Map<Part, Integer> partlist, Date validUntil, double price ){
        return offerRepository.createOffer(customerId, partlist, validUntil, price);
    }
}

