package application.server;

import java.math.BigInteger;
import java.rmi.RemoteException;
import java.security.SecureRandom;

import loadbalancer.monitor.Monitor;
import application.materials_management.facades.MaterialsManagement;
import application.materials_management.facades.MaterialsManagementFacade;
import application.order_management.data_access.dtos.OrderDTO;
import application.order_management.facades.OrderManagement;
import application.order_management.facades.OrderManagementFacade;
import application.production.facades.Production;
import application.production.facades.ProductionFacade;

public class ServerFacade implements ServerInstance {

    private static SecureRandom random = new SecureRandom();

    private final String serverId;
    private final Thread ekg;

    private MaterialsManagement materialsManagement = new MaterialsManagementFacade();
    private Production production = new ProductionFacade(this.materialsManagement);
    private OrderManagement orderManagement = new OrderManagementFacade(this.production);

    public ServerFacade(String monitorHostname, int monitorPort) {
        this.serverId = generateId();
        this.ekg = new ServerEKG(this.serverId, monitorHostname, monitorPort);
        this.ekg.start();
    }

    private String generateId() {
        return new BigInteger(130, ServerFacade.random).toString(32).substring(0, 6);
    }

    public static void main(String[] args) {
        new ServerFacade("localhost", Monitor.SERVER_PORT);
    }

    @Override
    public OrderDTO createOrder(int offerId) throws RemoteException {
        return orderManagement.createOrder(offerId);
    }
    
    @Override
    public void close() throws RemoteException {
        this.ekg.interrupt();
    }

}
