package application.order_management.data_access.repositories;

import java.util.Date;
import java.util.Map;

import org.hibernate.Session;

import util.SessionHelper;
import application.materials_management.data_access.entities.Part;
import application.order_management.data_access.entities.Offer;

public class OfferRepository {

    public Offer createOffer(Integer customerId, Map<Part, Integer> partlist, Date validUntil, double price) {
        Offer offer = new Offer(customerId, partlist, validUntil, price);

        this.getSession().save(offer);

        return offer;
    }

    private Session getSession() {
        return SessionHelper.getSession();
    }

}
