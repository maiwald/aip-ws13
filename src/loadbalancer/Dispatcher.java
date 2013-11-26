package loadbalancer;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import loadbalancer.monitor.Instance;
import loadbalancer.monitor.Monitor;
import application.materials_management.data_access.dtos.PartDTO;
import application.order_management.data_access.dtos.OfferDTO;
import application.order_management.data_access.dtos.OrderDTO;
import application.server.ServerInstance;

public class Dispatcher implements ServerInstance, Serializable {

    private int callCount = 0;

    @Override
    public OrderDTO createOrder(int offerId) throws RemoteException {
        return getNextInstance().createOrder(offerId);
    }

    @Override
    public OfferDTO createOffer(int customerId, Map<PartDTO, Integer> partlist, Date validUntil, double price) throws RemoteException {
        return getNextInstance().createOffer(customerId, partlist, validUntil, price);
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
