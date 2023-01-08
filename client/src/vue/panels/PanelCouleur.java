package client.src.vue.panels;

import java.awt.*;
import javax.swing.*;

import client.src.Controleur;

public class PanelCouleur extends JPanel
{
    private Controleur ctrl;
    
    private Integer couleur;

    public PanelCouleur(Controleur ctrl, Integer couleur)
    {
        this.ctrl = ctrl;

        this.couleur = couleur;

        this.setLayout(null);
        this.setPreferredSize(new Dimension(50, 50));
    }

    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);

        Color c;

        c = new Color(this.couleur);
        
        Graphics2D g2d = (Graphics2D) g;

        g2d.setStroke(new BasicStroke(1));

        g.setColor(c);
        g.fillOval(0, 0, 49, 49);
        g.setColor(Color.BLACK);
        g.drawOval(0, 0, 49, 49);
    }
}
