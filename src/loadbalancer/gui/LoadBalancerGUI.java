package loadbalancer.gui;


import loadbalancer.monitor.Monitor;

public class LoadBalancerGUI extends Thread {

    private final TrafficGui gui;

    public static void main(String[] args) {
        LoadBalancerGUI loadBalancerGUI = new LoadBalancerGUI();

        while (true) {
            loadBalancerGUI.update();
        }
    }

    public LoadBalancerGUI() {
        this.gui = new TrafficGui(Monitor.getInstances());
        gui.setVisible(true);

    }

    private void update() {
        gui.update();
    }
}
