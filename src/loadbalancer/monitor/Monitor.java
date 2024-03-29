package loadbalancer.monitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Monitor extends Thread {

    public static final int SERVER_PORT = 65001;

    static final int INSTANCE_CLEANUP_DELAY = 300;
    static final int INSTANCE_LIFETIME = 250;

    static Map<String, Instance> instances = new HashMap<String, Instance>();

    public static List<Instance> getInstances() {
        List<Instance> result = new ArrayList<Instance>();
        result.addAll(instances.values());
        return result;
    }

    public static List<Instance> getAliveInstances() {
        List<Instance> result = new ArrayList<Instance>();
        for (Instance instance : getInstances()) {
            if (instance.isAlive())
                result.add(instance);
        }
        return result;
    }

    static synchronized void addInstance(Instance instance) {
        instances.put(instance.getId(), instance);
    }

    public static void main(String[] args) {
        new Monitor();
    }

    public Monitor() {
        new MonitorThread().start();
        new DeadInstanceMarkerThread().start();
        new MonitorLogger().start();
    }
    
    class MonitorLogger extends Thread {
        public void run() {
            try {
                while (true) {
                    System.out.println(Monitor.getInstances());
                    Thread.sleep(2 * 1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
