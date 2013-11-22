package loadbalancer.gui;

import loadbalancer.monitor.Instance;
import loadbalancer.monitor.Monitor;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

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

        for(Instance i: instances) {
            serverPanels.add(new ServerPanel(this, i.getId()));
        }

        for(JPanel p: serverPanels) {
            orderList.add(p);
        }

        getContentPane().add(orderList);

        pack();
    }

    protected int getInstanceStatus(String instanceID) {
        List<Instance> instances = Monitor.getInstances();

        for(Instance i: instances) {
            if(i.getId() == instanceID) {
                return i.getStatus();
            }
        }
        return -1;
    }

    public void update() {
        for(ServerPanel panel: serverPanels) {
            panel.update();
        }
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            System.out.println("kannste knicken do");
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                List<Instance> instances = Monitor.getInstances();
                new TrafficGui(instances).setVisible(true);
            }
        });
    }

}
