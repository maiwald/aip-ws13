package loadbalancer.gui;

import loadbalancer.monitor.Instance;

/**
 * User: felix_000
 * Date: 15.11.13
 * Time: 15:50
 */
public class TrafficLight {
    private int id;
    private boolean status;

    public TrafficLight(int id) {
        this.id = id;
    }

    public void update(int status) {
        if(status == Instance.DEAD) {
            this.status = false;
        } else {
            this.status = true;
        }
        // TODO update gui element
    }
}
