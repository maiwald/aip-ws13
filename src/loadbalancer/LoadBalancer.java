package loadbalancer;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import loadbalancer.gui.LoadBalancerGUI;
import loadbalancer.monitor.Monitor;

public class LoadBalancer {

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            new Monitor();

            Dispatcher dispatcher = new Dispatcher();
            registry.bind("MPS", dispatcher);

            Thread.sleep(10000);

            new LoadBalancerGUI().start();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
