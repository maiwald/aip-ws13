package application.order_management.data_access.repositories;

import java.util.Date;

import org.hibernate.Session;

import util.SessionHelper;
import application.order_management.data_access.entities.Offer;

public class OfferRepository {

    public Offer createOffer(Integer customerId, Integer partId, Date validUntil, double price) {
        Offer offer = new Offer(customerId, partId, validUntil, price);

        this.getSession().save(offer);

        return offer;
    }

    private Session getSession() {
        return SessionHelper.getSession();
    }

}
