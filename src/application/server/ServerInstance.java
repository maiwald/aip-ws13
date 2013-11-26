package application.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Map;

import application.materials_management.data_access.dtos.PartDTO;
import application.order_management.data_access.dtos.OfferDTO;
import application.order_management.data_access.dtos.OrderDTO;

public interface ServerInstance extends Remote {
    public OrderDTO createOrder(int offerId) throws RemoteException;

    public OfferDTO createOffer(int customerId, Map<PartDTO, Integer> partlist, Date validUntil, double price) throws RemoteException;

    public void start() throws RemoteException;

    public void stop() throws RemoteException;
}
