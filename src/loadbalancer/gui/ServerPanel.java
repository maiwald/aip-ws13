package loadbalancer.gui;

import java.awt.Color;

import javax.swing.*;

import loadbalancer.monitor.Instance;

public class ServerPanel extends JPanel {

    JPanel trafficLight = new JPanel();
    JButton button = new JButton();
    private Instance instance;
    private final JLabel durationLabel = new JLabel();;

    public ServerPanel(Instance instance) {
        super();
        this.instance = instance;

        setLayout(new java.awt.GridLayout(3, 1));
        trafficLight.setBackground(new java.awt.Color(255, 0, 0));
        GroupLayout trafficLightLayout = new GroupLayout(trafficLight);
        trafficLight.setLayout(trafficLightLayout);
        trafficLightLayout.setHorizontalGroup(trafficLightLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 162, Short.MAX_VALUE));
        trafficLightLayout.setVerticalGroup(trafficLightLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 104, Short.MAX_VALUE));

        durationLabel.setText("0 Sekunden");
        durationLabel.setVisible(true);
        this.add(durationLabel);

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
            durationLabel.setText(this.instance.getSecondsOnline() + " Sekunden");
        } else if (this.instance.isDead()) {
            trafficLight.setBackground(Color.red);
            button.setText("Switch on:\n" + this.instance.getId());
            durationLabel.setText(this.instance.getSecondsOffline() + " Sekunden");
        }
    }
}
