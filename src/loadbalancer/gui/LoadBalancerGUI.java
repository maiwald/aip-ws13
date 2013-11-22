package loadbalancer.gui;

import loadbalancer.Instance;
import loadbalancer.monitor.Monitor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: felix_000
 * Date: 15.11.13
 * Time: 15:18
 */
public class LoadBalancerGUI {

    Map<Integer, TrafficLight> lights = new HashMap<Integer, TrafficLight>();
    Monitor monitor;

    public LoadBalancerGUI(Monitor monitor) {
        this.monitor = monitor;
    }

    private void update() {
        List<Instance> instances = monitor.getInstances();
        for(Instance instance: instances) {
            TrafficLight trafficLight = lights.get(instance.getId());
            if(trafficLight == null) {
                trafficLight = new TrafficLight(instance.getId());
                lights.put(instance.getId(), trafficLight);
            }
            trafficLight.update(instance.getStatus());
        }

    }
}
