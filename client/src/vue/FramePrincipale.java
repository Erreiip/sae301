package client.src.vue;

import client.src.Controleur;
import client.src.metier.common.Route;
import client.src.metier.common.Ville;
import client.src.metier.common.Wagon;
import client.src.vue.panels.*;

import javax.swing.*;
import java.awt.*;

public class FramePrincipale extends JFrame
{
    private Controleur ctrl;

    private PanelJoueurs panelJoueurs;
    private PanelPioche  panelPioche;
    private PanelMap     panelMap;
    private PanelCartesInteraction  panelCartesInteraction;

    public FramePrincipale(Controleur ctrl)
    {
        this.ctrl = ctrl;

        this.setTitle("Les Aventuriers du Rail");
        this.setSize(1400, 800);
        this.setLayout(new BorderLayout());

        this.panelJoueurs           = new PanelJoueurs(this.ctrl);
        this.panelPioche            = new PanelPioche(this.ctrl);
        this.panelMap               = new PanelMap(this.ctrl);
        this.panelCartesInteraction = new PanelCartesInteraction(this.ctrl);

        JScrollPane jspJoueurs = new JScrollPane(this.panelJoueurs);
        jspJoueurs.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        jspJoueurs.getHorizontalScrollBar().setUnitIncrement(10);

        JScrollPane jspMap = new JScrollPane(this.panelMap);
        jspMap.getVerticalScrollBar().setUnitIncrement(10);
        jspMap.getHorizontalScrollBar().setUnitIncrement(10);

        this.add(jspJoueurs                  , BorderLayout.NORTH);
        this.add(this.panelPioche            , BorderLayout.WEST);
        this.add(jspMap                      , BorderLayout.CENTER);
        this.add(this.panelCartesInteraction , BorderLayout.EAST);

        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        this.maj();
    }

    public void majPioche() { this.panelPioche.majPioche(); }

    public void colorier( Ville v1, Ville v2) { this.panelMap.colorier(v1,v2); }
    public void colorier()                    { this.panelMap.colorier(); }

    public void genererInteractionObj() 
    {
        this.panelCartesInteraction.genererInteractionObj();
    }

    public void genererInteractionCartes( Route r, int type)        
    { 
        this.panelCartesInteraction.genererInteractionCartes(r, type);
    }


    public void genererInteractionWagon(Wagon wagonCorrespondant) 
    {
        this.panelCartesInteraction.genererInteractionWagon(wagonCorrespondant);
    }

    public void suppBtnPiocheObj() 
    {
        this.panelPioche.suppBtnPiocheObj();
    }

    public void maj()
    {
        this.panelMap.majMap();
        this.panelPioche.majPioche();
        this.panelJoueurs.majInfos();
        this.panelCartesInteraction.maj();
    }
}
