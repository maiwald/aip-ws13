package application.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import application.order_management.data_access.dtos.OrderDTO;

public interface ServerInstance extends Remote {
    public OrderDTO createOrder(int offerId) throws RemoteException;
    public void close() throws RemoteException;
}
