package application.server;

import java.math.BigInteger;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Map;

import loadbalancer.monitor.Monitor;
import application.materials_management.data_access.entities.Part;
import application.materials_management.facades.MaterialsManagement;
import application.materials_management.facades.MaterialsManagementFacade;
import application.order_management.data_access.dtos.OfferDTO;
import application.order_management.data_access.dtos.OrderDTO;
import application.order_management.facades.OrderManagement;
import application.order_management.facades.OrderManagementFacade;
import application.production.facades.Production;
import application.production.facades.ProductionFacade;

public class ServerFacade implements ServerInstance {

    private static SecureRandom random = new SecureRandom();

    boolean running = true;

    final String serverId;
    final String monitorHost;
    final int monitorPort;

    private Thread ekg;

    private MaterialsManagement materialsManagement = new MaterialsManagementFacade();
    private Production production = new ProductionFacade(this.materialsManagement);
    private OrderManagement orderManagement = new OrderManagementFacade(this.production);

    public ServerFacade(String monitorHost, int monitorPort) {
        this.serverId = generateId();
        this.monitorHost = monitorHost;
        this.monitorPort = monitorPort;

        try {
            ServerInstance stub = (ServerInstance) UnicastRemoteObject.exportObject(this, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.bind(this.getId(), stub);
            this.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String generateId() {
        return new BigInteger(130, ServerFacade.random).toString(32).substring(0, 6);
    }

    public String getId() {
        return this.serverId;
    }

    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        new ServerFacade("localhost", Monitor.SERVER_PORT);
    }

    @Override
    public OrderDTO createOrder(int offerId) throws RemoteException {
        return orderManagement.createOrder(offerId);
    }

    @Override
    public OfferDTO createOffer(int customerId, Map<PartDTO, Integer> partlist, Date validUntil, double price) throws RemoteException {
        return orderManagement.createOffer(customerId, partlist, validUntil, price);
    }

    @Override
    public void start() throws RemoteException {
        this.ekg = new ServerEKG(this);
        this.running = true;
        this.ekg.start();
    }

    @Override
    public void stop() throws RemoteException {
        this.running = false;
    }

}
