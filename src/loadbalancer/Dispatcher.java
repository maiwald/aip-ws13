package loadbalancer;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import loadbalancer.monitor.Instance;
import loadbalancer.monitor.Monitor;
import application.order_management.data_access.dtos.OfferDTO;
import application.order_management.data_access.dtos.OrderDTO;
import application.server.ServerInstance;

public class Dispatcher implements ServerInstance, Serializable {

    private int callCount = 0;

    @Override
    public OrderDTO createOrder(int offerId) throws RemoteException {
        return this.getNextInstance().createOrder(offerId);
    }

    @Override
    public OfferDTO createOffer(int customerId, Integer partId, Date validUntil, double price) throws RemoteException {
        return this.getNextInstance().createOffer(customerId, partId, validUntil, price);
    }

    @Override
    public void start() throws RemoteException {
    }

    @Override
    public void stop() throws RemoteException {
    }

    private ServerInstance getNextInstance() {
        List<Instance> instances = Monitor.getAliveInstances();
        if (instances.size() == 0)
            return null;

        this.callCount++;
        Instance instance = instances.get(this.callCount % instances.size());
        return instance.getStub();
    }

}
