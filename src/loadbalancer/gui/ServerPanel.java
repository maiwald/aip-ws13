package loadbalancer.gui;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import loadbalancer.monitor.Instance;

public class ServerPanel extends JPanel {

    private TrafficGui gui;
    JPanel trafficLight = new JPanel();
    JButton button = new JButton();
    private String instanceID;

    public ServerPanel(TrafficGui gui, String instanceID) {
        super();
        this.gui = gui;
        this.instanceID = instanceID;

        setLayout(new java.awt.GridLayout(2, 1));
        trafficLight.setBackground(new java.awt.Color(255, 0, 0));
        GroupLayout trafficLightLayout = new GroupLayout(trafficLight);
        trafficLight.setLayout(trafficLightLayout);
        trafficLightLayout.setHorizontalGroup(trafficLightLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 162, Short.MAX_VALUE));
        trafficLightLayout.setVerticalGroup(trafficLightLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 104, Short.MAX_VALUE));

        this.add(trafficLight);

        button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonActionPerformed(evt);
            }
        });
        this.add(button);
    }

    private void buttonActionPerformed(java.awt.event.ActionEvent evt) {
        if (instanceStatus() == Instance.ALIVE) {
            gui.stop(instanceID);
        } else {
            gui.start(instanceID);
        }
    }

    public void update() {
        updateUi(this.instanceStatus());
    }

    private int instanceStatus() {
        return this.gui.getInstanceStatus(this.instanceID);
    }

    private void updateUi(int newStatus) {
        if (newStatus == Instance.ALIVE) {
            trafficLight.setBackground(Color.green);
            button.setText("Switch off " + this.instanceID);
        } else {
            trafficLight.setBackground(Color.red);
            button.setText("Switch on " + this.instanceID);
        }
    }
}
