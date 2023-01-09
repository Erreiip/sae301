package client.src.vue.panels;

import javax.swing.*;

import client.src.Controleur;
import client.src.metier.common.Joueur;

import java.awt.*;


public class PanelInfosJoueurs extends JPanel
{
    private Controleur ctrl;

    private Joueur joueur;

    private JPanel panelGridBag;
    private JPanel panelLbl;
    private JLabel lbl;
    private PanelCouleur panelCouleur;
    private JPanel panelInfos;
    private JLabel lblNbWagons;
    private JLabel lblNbPv;

    public PanelInfosJoueurs(Joueur j, Controleur ctrl)
    {
        this.ctrl = ctrl;
        this.joueur = j;

        this.panelGridBag = new JPanel(new GridBagLayout());      // GridBagLayout pour 1 joueur
        this.panelLbl     = new JPanel();                             // Panel pour nom/numéro du joueur

        if (this.ctrl.getJoueurActif() == j)
            this.lbl = new JLabel("Joueur " + j.getId() + " (actif)", SwingConstants.CENTER); // nom/numéro du joueur
        else
            this.lbl = new JLabel("Joueur " + j.getId() + "", SwingConstants.CENTER); // nom/numéro du joueur
    
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

        this.add(this.panelGridBag);
    }

    public void maj()
    {
        this.lblNbWagons.setText("Nb wagons : " + this.joueur.getNbMarqueurs() );
        this.lblNbPv.setText("Nb PV : " + this.joueur.getNbPv());

        if (this.ctrl.getJoueurActif() == this.joueur)
            this.lbl.setText("Joueur " + this.joueur.getId() + " (actif)"); // nom/numéro du joueur
        else
            this.lbl.setText("Joueur " + this.joueur.getId() + ""); 
    }
}
