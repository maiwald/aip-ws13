package loadbalancer;

import java.util.List;

import loadbalancer.monitor.Instance;
import loadbalancer.monitor.Monitor;
import application.order_management.data_access.dtos.OrderDTO;
import application.server.ServerInstance;

public class Dispatcher {

    private int callCount = 0;

    public OrderDTO createOrder(int offerId) {
        OrderDTO result = null;
        try {
            result = getNextInstance().createOrder(offerId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void mett() {
        try {
            getNextInstance().mett();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ServerInstance getNextInstance() {
        List<Instance> instances = Monitor.getAliveInstances();
        Instance instance = instances.get(this.callCount % instances.size());
        this.callCount++;
        return instance.getStub();
    }
}
