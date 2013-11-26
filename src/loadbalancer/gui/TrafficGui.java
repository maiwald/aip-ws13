package loadbalancer.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import loadbalancer.monitor.Instance;

public class TrafficGui extends javax.swing.JFrame {

    private List<ServerPanel> serverPanels;

    public TrafficGui(List<Instance> instances) {
        initComponents(instances);
    }

    private void initComponents(List<Instance> instances) {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        JPanel orderList = new JPanel();
        orderList.setLayout(new BoxLayout(orderList, BoxLayout.Y_AXIS));

        serverPanels = new ArrayList<ServerPanel>();

        for (Instance i : instances) {
            serverPanels.add(new ServerPanel(this, i));
        }

        for (JPanel p : serverPanels) {
            orderList.add(p);
        }

        getContentPane().add(orderList);

        pack();
    }

    public void update() {
        for (ServerPanel panel : serverPanels) {
            panel.update();
        }
    }

}
