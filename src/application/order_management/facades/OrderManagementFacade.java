package application.order_management.facades;

import java.util.Date;
import java.util.Map;

import org.hibernate.Session;

import util.SessionHelper;
import application.customer_management.facade.CustomerManagement;
import application.materials_management.data_access.entities.Part;
import application.order_management.business_logic.OfferCreator;
import application.order_management.business_logic.OrderCreator;
import application.order_management.data_access.dtos.OfferDTO;
import application.order_management.data_access.dtos.OrderDTO;
import application.order_management.data_access.entities.Offer;
import application.order_management.data_access.entities.Order;
import application.order_management.data_access.repositories.OfferRepository;
import application.order_management.data_access.repositories.OrderRepository;
import application.production.facades.Production;

public class OrderManagementFacade implements OrderManagement {

    private final OrderCreator orderCreator;
    private final OfferCreator offerCreator;
    private final CustomerManagement customerManagement; 
    
    public OrderManagementFacade(Production production, CustomerManagement customerManagement) {
        OrderRepository orderRepository = new OrderRepository();
        OfferRepository offerRepository = new OfferRepository();
        this.customerManagement = customerManagement;
        
        this.offerCreator = new OfferCreator(offerRepository);
        this.orderCreator = new OrderCreator(orderRepository, production);
    }

    @Override
    public OfferDTO createOffer(Integer customerId, Map<Part, Integer> partlist, Date validUntil, double price){
        Session session = this.getSession();
        session.beginTransaction();
        Offer offer = offerCreator.createOffer(customerId, partlist, validUntil, price);
        if (offer == null)
            return null;

        session.getTransaction().commit();
        return offer.createDTO();
    }
    
    @Override
    public OrderDTO createOrder(int offerId) {
        Session session = this.getSession();
        session.beginTransaction();

        validateOfferId(offerId);
        Order order = orderCreator.createOrder(offerId);
        if (order == null)
            return null;

        session.getTransaction().commit();
        return order.createDTO();
    }

    private void validateOfferId(int offerId) {
        try {
            if (offerId < 1)
                throw new InvalidOfferIdError();
        } catch (InvalidOfferIdError invalidOfferIdError) {
            invalidOfferIdError.printStackTrace();
        }
    }

    public class InvalidOfferIdError extends Exception {
        private static final long serialVersionUID = 1L;
    }

    private Session getSession() {
        return SessionHelper.getSession();
    }
}