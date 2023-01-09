package client.src.vue.panels;

import javax.swing.*;
import java.awt.*;

import client.src.Controleur;
import client.src.metier.common.Joueur;

public class PanelResultJoueur extends JPanel
{
    private Controleur ctrl;

    public PanelResultJoueur(Controleur ctrl, Joueur j, int rank)
    {
        this.ctrl = ctrl;

        this.setLayout(new GridLayout(1, 4));

        switch (rank)
        {
            case 1 : this.setBackground(new Color(255,215,0  )); break; // couleur or 
            case 2 : this.setBackground(new Color(192,192,192)); break; // couleur argent
            case 3 : this.setBackground(new Color(88 ,41 ,0  )); break; // couleur bronze
            default : break;
        }
    

        this.add(new JLabel("" + rank        , SwingConstants.CENTER));
        this.add(new JLabel("" + j.getId()   , SwingConstants.CENTER));
        this.add(new JLabel("" + j.getNbPv() , SwingConstants.CENTER));
        this.add(new JLabel("objectifs", SwingConstants.CENTER));

    }
}
