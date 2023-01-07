package client.src.vue.panels;

import client.src.Controleur;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;

public class PanelJoueurs extends JPanel
{
    private Controleur ctrl;

    private JPanel panelVous;
    private JPanel panelLblVous;
    private JLabel lblVous;
    private PanelCouleur panelCouleurVous;
    private JPanel panelInfosVous;
    private JLabel lblNbWagonsVous;
    private JLabel lblNbPvVous;

    private JPanel panelJActuel;
    private JPanel panelLblJActuel;
    private JLabel lblJActuel;
    private PanelCouleur panelCouleurJActuel;
    private JPanel panelInfosJActuel;
    private JLabel lblNbWagonsJActuel;
    private JLabel lblNbPvJActuel;
   
    public PanelJoueurs(Controleur ctrl)
    {
        this.ctrl = ctrl;

        this.setLayout(new GridLayout(1,2));
        
        this.panelVous = new JPanel(new GridBagLayout());
        this.panelLblVous = new JPanel();   
        this.lblVous = new JLabel("Vous :", SwingConstants.CENTER);
        this.panelCouleurVous = new PanelCouleur(0, this.ctrl);
        this.panelInfosVous = new JPanel(new GridLayout(3,1));
        this.lblNbWagonsVous = new JLabel("Nombre de wagons : " + this.ctrl.getJoueur().getNbMarqueurs());
        this.lblNbPvVous = new JLabel("Nombre de points de victoire : " + this.ctrl.getJoueur().getNbPv());

        this.panelJActuel = new JPanel(new GridBagLayout());
        this.panelLblJActuel = new JPanel();
        this.lblJActuel = new JLabel("Joueur actuel :");
        this.panelCouleurJActuel = new PanelCouleur(1, this.ctrl);
        this.panelInfosJActuel = new JPanel(new GridLayout(3,1));
        this.lblNbWagonsJActuel = new JLabel("Nombre de wagons : " + this.ctrl.getJoueurActif().getNbMarqueurs()); //joueur actif
        this.lblNbPvJActuel = new JLabel("Nombre de points de victoire : " + this.ctrl.getJoueurActif().getNbPv()); // joueur actif

        // --

        Border blackline = BorderFactory.createLineBorder(Color.black);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(1, 1, 1, 10);

        this.panelLblVous.add(this.lblVous);
        this.panelInfosVous.add(this.lblNbWagonsVous);
        this.panelInfosVous.add(this.lblNbPvVous);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 0;
        this.panelVous.add(this.panelLblVous, c);

        c.fill = GridBagConstraints.VERTICAL;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        this.panelVous.add(this.panelCouleurVous, c);
         
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 2;
        c.gridx = 1;
        c.gridy = 1;
        this.panelVous.add(this.panelInfosVous, c);

        this.panelVous.setBorder(blackline);
        this.add(this.panelVous);

        // --

        this.panelLblJActuel.add(this.lblJActuel);
        this.panelInfosJActuel.add(this.lblNbWagonsJActuel);
        this.panelInfosJActuel.add(this.lblNbPvJActuel);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 0;
        this.panelJActuel.add(this.panelLblJActuel, c);

        c.fill = GridBagConstraints.VERTICAL;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        this.panelJActuel.add(this.panelCouleurJActuel, c);
         
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 2;
        c.gridx = 1;
        c.gridy = 1;
        this.panelJActuel.add(this.panelInfosJActuel, c);

        this.panelJActuel.setBorder(blackline);
        this.add(this.panelJActuel);

        // -- 
    }

    public void majInfos()
    {
        this.majInfosJoueur();
        this.majInfosJoueurActif();
        this.majPanel();
    }

    public void majPanel()
    {
        this.panelCouleurJActuel.repaint();
    }

    public void majInfosJoueur()
    {
        this.lblNbWagonsVous.setText("Nombre de wagons : " + this.ctrl.getJoueur().getNbMarqueurs());
        this.lblNbPvVous.setText("Nombre de points de victoire : " + this.ctrl.getJoueur().getNbPv());
    }

    public void majInfosJoueurActif()
    {
        this.lblNbWagonsJActuel.setText("Nombre de wagons : " + this.ctrl.getJoueurActif().getNbMarqueurs());
        this.lblNbPvJActuel.setText("Nombre de points de victoire : " + this.ctrl.getJoueurActif().getNbPv());
    }
}
