package loadbalancer;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import loadbalancer.monitor.Monitor;

public class LoadBalancer {

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            new Monitor();

            Dispatcher dispatcher = new Dispatcher();
            System.out.println("haaaaaaaaaaaallloooooooo!");

            Thread.sleep(10000);

            System.out.println("sending mett!");
            dispatcher.mett();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
