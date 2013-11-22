package loadbalancer.monitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import loadbalancer.Instance;

public class Monitor extends Thread {
    public static final int SERVER_PORT = 65001;
    static final int INSTANCE_CLEANUP_DELAY = 3;
    static final int INSTANCE_LIFETIME = 10;

    private static Map<String, Instance> instances = new HashMap<String, Instance>();

    public static synchronized List<Instance> getInstances() {
        List<Instance> result = new ArrayList<Instance>();
        result.addAll(instances.values());
        return result;
    }

    public static synchronized List<Instance> getAliveInstances() {
        List<Instance> result = new ArrayList<Instance>();
        for (Instance elem : getInstances()) {
            if (elem.getStatus() != Instance.DEAD) {
                result.add(elem);
            }
        }
        return result;
    }

    public static synchronized void addInstance(Instance instance) {
        instances.put(instance.getId(), instance);
    }

    public static void main(String[] args) {
        new Monitor();
    }

    public Monitor() {
        new MonitorThread().start();
        new DeadInstanceMarkerThread().start();

        try {
            while (true) {
                System.out.println(Monitor.getInstances());
                Thread.sleep(2 * 1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
