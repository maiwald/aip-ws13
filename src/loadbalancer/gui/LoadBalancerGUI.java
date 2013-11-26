package loadbalancer.gui;

import loadbalancer.monitor.Monitor;

public class LoadBalancerGUI extends Thread {

    private final TrafficGui gui;

    public LoadBalancerGUI() {
        this.gui = new TrafficGui(Monitor.getInstances());
        gui.setVisible(true);
    }

    public void run() {
        while (true) {
            try {
                gui.update();
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
