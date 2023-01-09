package client.src.vue.panels;

import client.src.Controleur;
import client.src.metier.common.Joueur;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;

public class PanelJoueurs extends JPanel
{
    private Controleur ctrl;

    private ArrayList<PanelInfosJoueurs> alPnlInfosJoueurs;
   
    public PanelJoueurs(Controleur ctrl)
    {
        this.ctrl = ctrl;

        alPnlInfosJoueurs = new ArrayList<PanelInfosJoueurs>();

        this.setLayout(new FlowLayout());

        for (Joueur j : this.ctrl.getAlJoueurs())
        {
            PanelInfosJoueurs pnlInfos = new PanelInfosJoueurs(j, ctrl);

            this.alPnlInfosJoueurs.add(pnlInfos);

            this.add(pnlInfos);
        }
    }

    public void majInfos()
    {
        this.majInfosJoueur();
        this.majPanel();
    }

    public void majPanel()
    {
        // 
    }

    public void majInfosJoueur()
    {
        for (PanelInfosJoueurs pnl : this.alPnlInfosJoueurs)
        {
            pnl.maj();
        }
    }
}
