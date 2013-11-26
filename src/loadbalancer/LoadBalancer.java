package loadbalancer;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import loadbalancer.gui.LoadBalancerGUI;
import loadbalancer.monitor.Monitor;
import application.server.ServerFacade;

public class LoadBalancer {

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            new Monitor();

            Dispatcher dispatcher = new Dispatcher();

            Thread.sleep(10000);

            dispatcher.mett();

            new LoadBalancerGUI().start();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
