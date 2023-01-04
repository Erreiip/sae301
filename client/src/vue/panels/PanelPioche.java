package client.src.vue.panels;

import client.src.Controleur;

import javax.swing.*;
import java.awt.*;

public class PanelPioche extends JPanel 
{
    private Controleur ctrl;

    public PanelPioche(Controleur ctrl)
    {
        this.ctrl = ctrl;

        this.setLayout(new GridLayout(7,1));
        this.setBackground(Color.RED);

        JLabel lbl = new JLabel("JLabel");
        this.add(lbl);
    }    
}
