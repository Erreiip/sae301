package client.src.vue.panels;

import java.awt.*;
import javax.swing.*;

public class PanelCouleur extends JPanel
{ 
    private Color c;

    public PanelCouleur(Color c)
    {
        this.c = c;
        this.setLayout(null);
        this.setPreferredSize(new Dimension(50, 50));
    }

    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;

        g2d.setStroke(new BasicStroke(2));

        g.setColor(this.c);
        g.fillOval(0, 0, 50, 50);
    }
}
