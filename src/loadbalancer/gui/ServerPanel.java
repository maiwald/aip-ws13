package loadbalancer.gui;

import loadbalancer.monitor.Instance;

import javax.swing.*;
import java.awt.*;

public class ServerPanel extends JPanel {

    private TrafficGui gui;
    JPanel trafficLight = new JPanel();
    JButton button = new JButton();
    private int guiStatus = Instance.DEAD;
    private String instanceID;

    public ServerPanel(TrafficGui gui, String instanceID) {
        super();
        this.gui = gui;
        this.instanceID = instanceID;
        this.guiStatus = gui.getInstanceStatus(instanceID);

        setLayout(new java.awt.GridLayout(2, 1));
        trafficLight.setBackground(new java.awt.Color(255, 0, 0));
        GroupLayout trafficLightLayout = new GroupLayout(trafficLight);
        trafficLight.setLayout(trafficLightLayout);
        trafficLightLayout.setHorizontalGroup(
                trafficLightLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 162, Short.MAX_VALUE)
        );
        trafficLightLayout.setVerticalGroup(
                trafficLightLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 104, Short.MAX_VALUE)
        );

        this.add(trafficLight);

        button.setText("Switch On");
        button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonActionPerformed(evt);
            }
        });
        this.add(button);
        toggleTrafficLightStatus();
    }

    private void buttonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO switch instance on/off
        if(instanceStatus() == Instance.ALIVE) {
            gui.stop(instanceID);
        } else {
            gui.start(instanceID);
        }
        toggleTrafficLightStatus();
    }

    public void update() {
        if (needToUpdate()) {
            toggleTrafficLightStatus();
        }
    }

    private boolean needToUpdate() {
        return guiStatus != this.instanceStatus();
    }

    private int instanceStatus() {
        return this.gui.getInstanceStatus(this.instanceID);
    }

    private void toggleTrafficLightStatus() {
        if (guiStatus == Instance.ALIVE) {
            trafficLight.setBackground(Color.green);
            button.setText("Switch off");
            guiStatus = Instance.DEAD;
        } else {
            trafficLight.setBackground(Color.red);
            button.setText("Switch on");
            guiStatus = Instance.ALIVE;
        }
    }
}
