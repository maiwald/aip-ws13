package loadbalancer.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * User: felix_000
 * Date: 22.11.13
 * Time: 11:22
 */
public class TrafficLightPanel extends JPanel {
    int x = 0;
    int y = 0;
    int diameter = 100;

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Ellipse2D.Double circle = new Ellipse2D.Double(x, y, diameter, diameter);
        g2d.setBackground(Color.RED);
        g2d.fill(circle);
    }
}
