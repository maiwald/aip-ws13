package loadbalancer;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import application.server.ServerFacade;
import loadbalancer.gui.TrafficGui;
import loadbalancer.monitor.Monitor;

public class LoadBalancer {

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            new Monitor();

            Dispatcher dispatcher = new Dispatcher();
            System.out.println("haaaaaaaaaaaallloooooooo!");

            new ServerFacade("localhost", Monitor.SERVER_PORT).start();

            Thread.sleep(10000);

            System.out.println("sending mett!");
            dispatcher.mett();
            TrafficGui gui = new TrafficGui(Monitor.getInstances());
            gui.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
