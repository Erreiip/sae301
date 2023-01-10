package client.src.vue;

import javax.swing.*;

import client.src.Controleur;
import client.src.metier.common.Joueur;
import client.src.vue.panels.PanelResultJoueur;

import java.awt.*;
import java.util.List;
import java.util.Collections;

public class FrameFin extends JFrame
{
    private Controleur ctrl;

    private JPanel panelHaut;
    private JPanel panelClassement;
        private PanelResultJoueur panelResultJoueur;

    public FrameFin(Controleur ctrl)
    {
        this.ctrl = ctrl;

        this.setTitle("Fin de la partie");
        this.setSize(1200, (50+(this.ctrl.getNbJoueur() * 100)));
        this.setLayout(new BorderLayout());

        this.panelHaut = new JPanel(new GridLayout(1, 5));
        this.panelHaut.add(new JLabel("Classement"          , SwingConstants.CENTER));
        this.panelHaut.add(new JLabel("Nom"                 , SwingConstants.CENTER));
        this.panelHaut.add(new JLabel("Route la plus longue", SwingConstants.CENTER));
        this.panelHaut.add(new JLabel("Point de victoire"   , SwingConstants.CENTER));
        this.panelHaut.add(new JLabel("Objectifs"           , SwingConstants.CENTER));

        this.add(this.panelHaut, BorderLayout.NORTH);


        this.panelClassement = new JPanel(new GridLayout(this.ctrl.getNbJoueur(), 1));

        List<Joueur> alJoueurs = this.ctrl.getJoueursFin();
        Collections.sort(alJoueurs);
        
        int rank = 1;
        for (int i=0; i<alJoueurs.size(); i++)
        {
            this.panelResultJoueur = new PanelResultJoueur(ctrl, alJoueurs.get(i), rank);

            this.panelClassement.add(this.panelResultJoueur);

            if (i != 0 && !(alJoueurs.get(i).compareTo(alJoueurs.get(i-1)) == 0))
                rank++;
        }

        this.add(this.panelClassement, BorderLayout.CENTER);



        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
