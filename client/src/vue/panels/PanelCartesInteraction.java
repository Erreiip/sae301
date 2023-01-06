package client.src.vue.panels;

import client.src.Controleur;
import client.src.metier.common.Wagon;
import client.src.vue.common.ImageRenderer;
import client.src.vue.common.ModelTableObjectifs;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PanelCartesInteraction extends JPanel
{
    private Controleur ctrl;

    private PanelCarte panelCarte;
        

    public PanelCartesInteraction(Controleur ctrl)
    {
        this.setLayout(new GridLayout(2, 1));
        this.setPreferredSize(new Dimension(350,600));



        this.ctrl = ctrl;

        this.panelCarte = new PanelCarte(this.ctrl);

        this.add(this.panelCarte);
        this.add(new JLabel("shesh"));        
    }   

   
}