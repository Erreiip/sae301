package client.src.vue.panels;

import client.src.Controleur;
import client.src.metier.common.Route;
import client.src.metier.common.Wagon;
import client.src.vue.common.ImageRenderer;
import client.src.vue.common.ModelTableObjectifs;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PanelCartesInteraction extends JPanel
{
    private Controleur ctrl;

    private PanelCarte       panelCarte;
    private PanelInteraction panelInteraction;
        

    public PanelCartesInteraction(Controleur ctrl)
    {
        this.setLayout(new GridLayout(2, 1));
        this.setPreferredSize(new Dimension(350,600));
        
        this.ctrl = ctrl;

        this.panelCarte       = new PanelCarte(this.ctrl);
        this.panelInteraction = new PanelInteraction(this.ctrl);

        this.add(this.panelCarte);
        this.add(this.panelInteraction);        
    }

    public void genererInteractionObj() 
    {
        this.panelInteraction.genererInteractionObj();
    }

    public void genererInteractionWagon(Wagon wagonCorrespondant) 
    {
        this.panelInteraction.genererInteractionWagon(wagonCorrespondant);
    }

    public void genererInteractionCartes(Route r, int type) 
    {
        this.panelInteraction.genererInteractionCartes(r, type);
    }

    public void maj()
    {
        this.panelCarte.maj();
        this.panelInteraction.maj();
    } 
}