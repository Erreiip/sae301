package client.src.vue.panels;

import client.src.Controleur;

import javax.swing.*;
import java.awt.*;

public class PanelCartes extends JPanel
{
    private Controleur ctrl;

    public PanelCartes(Controleur ctrl)
    {
        this.ctrl = ctrl;

        this.setLayout(new GridLayout(2,1));
        this.setBackground(Color.RED);

        JLabel lbl = new JLabel("JLabel");
        this.add(lbl);
    }
}
