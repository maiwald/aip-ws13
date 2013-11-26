package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;
import java.util.HashMap;

import application.materials_management.data_access.dtos.PartDTO;
import application.server.ServerInstance;

public class Client {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(Registry.REGISTRY_PORT);
            ServerInstance server = (ServerInstance) registry.lookup("MPS");
            server.createOffer(423, 7, new Date(), 4.2);
            server.createOrder(123);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
