package client.src.vue.panels;

import client.src.Controleur;
import client.src.metier.common.Joueur;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;

public class PanelJoueurs extends JPanel
{
    private Controleur ctrl;

    private JPanel panelGridBag;
        private JPanel panelLbl;
            private JLabel lbl;
        private PanelCouleur panelCouleur;
        private JPanel panelInfos;
            private JLabel lblNbWagons;
            private JLabel lblNbPv;
   
    public PanelJoueurs(Controleur ctrl)
    {
        this.ctrl = ctrl;

        this.setLayout(new GridLayout(1 , this.ctrl.getNbJoueur()));
        

        Border blackline = BorderFactory.createLineBorder(Color.black);

        for (Joueur j : this.ctrl.getAlJoueurs())
        {
            this.panelGridBag = new JPanel(new GridBagLayout());      // GridBagLayout pour 1 joueur
            this.panelLbl = new JPanel();                             // Panel pour nom/numéro du joueur

            if (this.ctrl.getJoueurActif() == j)
                this.lbl = new JLabel(j.getCouleur() + " (actif)", SwingConstants.CENTER); // nom/numéro du joueur
            else
                this.lbl = new JLabel(j.getCouleur() + "", SwingConstants.CENTER); // nom/numéro du joueur
        
            this.panelCouleur = new PanelCouleur(this.ctrl, j.getCouleur()); // Panel pour la couleur du joueur
            this.panelInfos = new JPanel(new GridLayout(2,1));        // Panel pour les infos du joueur
            this.lblNbWagons = new JLabel("Nb wagons : " + j.getNbMarqueurs()); // Nombre de wagons du joueur
            this.lblNbPv = new JLabel("Nb PV : " + j.getNbPv());      // Nombre de points de victoire du joueur

            this.panelLbl.add(this.lbl);

            this.panelInfos.add(this.lblNbWagons);
            this.panelInfos.add(this.lblNbPv);

            GridBagConstraints c = new GridBagConstraints();
            c.insets = new Insets(1, 1, 1, 10);

            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridwidth = 3;
            c.gridx = 0;
            c.gridy = 0;
            this.panelGridBag.add(this.panelLbl, c);

            c.fill = GridBagConstraints.VERTICAL;
            c.gridwidth = 1;
            c.gridx = 0;
            c.gridy = 1;
            this.panelGridBag.add(this.panelCouleur, c);
            
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridwidth = 2;
            c.gridx = 1;
            c.gridy = 1;
            this.panelGridBag.add(this.panelInfos, c);

            this.panelLbl.setBorder(blackline);
            this.panelCouleur.setBorder(blackline);
            this.panelInfos.setBorder(blackline);
   
            this.panelGridBag.setBorder(blackline);

            this.add(this.panelGridBag);
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
        this.lblNbWagons.setText("Nb wagons : " + this.ctrl.getJoueur().getNbMarqueurs());
        this.lblNbPv.setText("Nb PV : " + this.ctrl.getJoueur().getNbPv());
    }
}
