package application.server;

import java.math.BigInteger;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
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
    private final String monitorHost;
    private final int monitorPort;

    private Thread ekg;

    private MaterialsManagement materialsManagement = new MaterialsManagementFacade();
    private Production production = new ProductionFacade(this.materialsManagement);
    private OrderManagement orderManagement = new OrderManagementFacade(this.production);

    public ServerFacade(String monitorHost, int monitorPort) {
        this.serverId = generateId();
        this.monitorHost = monitorHost;
        this.monitorPort = monitorPort;
    }

    private String generateId() {
        return new BigInteger(130, ServerFacade.random).toString(32).substring(0, 6);
    }

    public String getId() {
        return this.serverId;
    }

    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        ServerFacade serverFacade = new ServerFacade("localhost", Monitor.SERVER_PORT);
        ServerInstance stub = (ServerInstance) UnicastRemoteObject.exportObject(serverFacade, 0);
        Registry registry = LocateRegistry.getRegistry();
        registry.bind(serverFacade.getId(), stub);
        serverFacade.start();
    }

    @Override
    public OrderDTO createOrder(int offerId) throws RemoteException {
        return orderManagement.createOrder(offerId);
    }

    @Override
    public void start() throws RemoteException {
        this.ekg = new ServerEKG(this.serverId, this.monitorHost, this.monitorPort);
        this.ekg.start();
    }

    @Override
    public void stop() throws RemoteException {
        this.ekg.interrupt();
    }

    @Override
    public void mett() throws RemoteException {
        System.out.println("MEEEEEEEEEEEEEEEEEEEEEETT");

    }

}
