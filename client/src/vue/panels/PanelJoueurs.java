package client.src.vue.panels;

import client.src.Controleur;

import javax.swing.*;
import java.awt.*;

public class PanelJoueurs extends JPanel
{
    private Controleur ctrl;

    
    private JPanel panelHaut;
        private JLabel lblHaut;

    private JPanel panelCouleur;
        private Color  couleurJoueur;
    
    private JPanel panelInfos;
        private JLabel lblNom;
        private JLabel lblNbWagons;
        private JLabel lblNbPV;

    public PanelJoueurs(Controleur ctrl)
    {
        this.ctrl = ctrl;

        this.setLayout(new GridLayout(1,2));
        
        this.panelHaut = new JPanel();
            this.lblHaut = new JLabel("Vous : ");

        this.panelCouleur = new JPanel();
            this.couleurJoueur = Color.RED; // Couleur du joueur
            this.panelCouleur.setBackground(this.couleurJoueur);

        this.panelInfos = new JPanel(new GridLayout(3,1));
            this.lblNom = new JLabel("Nom du joueur");
            this.lblNbWagons = new JLabel("Nombre de wagons");
            this.lblNbPV = new JLabel("Nombre de points de vie");

            this.panelInfos.add(this.lblNom);
            this.panelInfos.add(this.lblNbWagons);
            this.panelInfos.add(this.lblNbPV);


    }
}
