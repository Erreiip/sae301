package client.src.vue;

import javax.swing.*;

import client.src.Controleur;
import client.src.metier.common.Joueur;
import client.src.vue.panels.PanelResultJoueur;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class FrameFin extends JFrame
{
    private Controleur ctrl;

    private JPanel panelHaut;
    private PanelResultJoueur panelResultJoueur;

    public FrameFin(Controleur ctrl)
    {
        this.ctrl = ctrl;

        this.setTitle("Fin de la partie");
        this.setSize(800,700);
        this.setLayout(new GridLayout((this.ctrl.getNbJoueur() + 1), 1));

        this.panelHaut = new JPanel(new GridLayout(1, 4));
        this.panelHaut.add(new JLabel("Classement"       , SwingConstants.CENTER));
        this.panelHaut.add(new JLabel("Nom"              , SwingConstants.CENTER));
        this.panelHaut.add(new JLabel("Point de victoire", SwingConstants.CENTER));
        this.panelHaut.add(new JLabel("Objectifs"        , SwingConstants.CENTER));

        this.add(this.panelHaut);

        ArrayList<Joueur> alJoueurs = this.ctrl.getAlJoueurs();
        Collections.sort(alJoueurs);
        
        for (Joueur j : alJoueurs)
        {
            this.panelResultJoueur = new PanelResultJoueur(ctrl, j, alJoueurs.indexOf(j) + 1);

            this.add(this.panelResultJoueur);
        }

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
