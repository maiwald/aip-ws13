package application.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

import application.order_management.data_access.dtos.OfferDTO;
import application.order_management.data_access.dtos.OrderDTO;

public interface ServerInstance extends Remote {
    public OrderDTO createOrder(int offerId) throws RemoteException;

    public OfferDTO createOffer(int customerId, Integer partId, Date validUntil, double price) throws RemoteException;

    public void start() throws RemoteException;

    public void stop() throws RemoteException;
}
