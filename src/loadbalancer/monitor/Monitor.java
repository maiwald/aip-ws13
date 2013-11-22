package loadbalancer.monitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    static synchronized void addInstance(Instance instance) {
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
                //System.out.println(Monitor.getInstances());
                for(Instance elem: Monitor.getInstances()){
                    if(elem.getStatus()!=Instance.DEAD){
                        elem.getStub().mett();
                    }
                }
                Thread.sleep(2 * 1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
