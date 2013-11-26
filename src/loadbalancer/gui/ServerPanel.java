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
    private Instance instance;

    public ServerPanel(TrafficGui gui, Instance instance) {
        super();
        this.gui = gui;
        this.instance = instance;

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
        if (this.instance.isAlive()) {
            this.instance.stop();
        } else {
            this.instance.start();
        }
    }

    public void update() {
        if (this.instance.isAlive()) {
            trafficLight.setBackground(Color.green);
            button.setText("Switch off:\n" + this.instance.getId());
        } else {
            trafficLight.setBackground(Color.red);
            button.setText("Switch on:\n" + this.instance.getId());
        }
    }
}
