package client.src.vue.panels;

import java.awt.*;
import javax.swing.*;

import client.src.Controleur;

public class PanelCouleur extends JPanel
{
    private int type;
    private Controleur ctrl;


    public PanelCouleur(int type, Controleur ctrl)
    {
        this.ctrl = ctrl;
        this.type = type;

        this.setLayout(null);
        this.setPreferredSize(new Dimension(50, 50));
    }

    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);

        Color c;

        if (type == 0) c = new Color(this.ctrl.getJoueur().getCouleur());
        else           c = new Color(this.ctrl.getJoueurActif().getCouleur());
        
        Graphics2D g2d = (Graphics2D) g;

        g2d.setStroke(new BasicStroke(2));

        g.setColor(c);
        g.fillOval(0, 0, 50, 50);
    }
}
