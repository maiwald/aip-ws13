package loadbalancer;

import loadbalancer.monitor.Monitor;

public class LoadBalancer {

    public static void main(String[] args) {
        Monitor monitor = new Monitor();
        Dispatcher dispatcher = new Dispatcher();
    }

}
