package client.src.vue.panels;

import client.src.Controleur;

import javax.swing.*;
import java.awt.*;

public class PanelJoueurs extends JPanel
{
    private Controleur ctrl;

    private JPanel panelVous;
        private JPanel panelLblVous;
            private JLabel lblVous;
        private JPanel panelCouleurVous;
            private Color couleurVous;
            private JLabel lblCouleurVous;
        private JPanel panelInfosVous;
            private JLabel lblNomVous;
            private JLabel lblNbWagonsVous;
            private JLabel lblNbPvVous;

    private JPanel panelJActuel;
        private JPanel panelLblJActuel;
            private JLabel lblJActuel;
        private JPanel panelCouleurJActuel;
            private Color couleurJActuel;
            private JLabel lblCouleurJActuel;
        private JPanel panelInfosJActuel;
            private JLabel lblNomJActuel;
            private JLabel lblNbWagonsJActuel;
            private JLabel lblNbPvJActuel;
   
    public PanelJoueurs(Controleur ctrl)
    {
        this.ctrl = ctrl;

        this.setLayout(new GridLayout(1,2));
        
        this.panelVous = new JPanel(new GridBagLayout());
            this.panelLblVous = new JPanel();   
                this.lblVous = new JLabel("Vous :", SwingConstants.CENTER);
            this.panelCouleurVous = new JPanel();
                this.couleurVous = Color.RED; // Couleur du joueur
                this.lblCouleurVous = new JLabel("Couleur");
                this.lblCouleurVous.setForeground(Color.RED);
            this.panelInfosVous = new JPanel(new GridLayout(3,1));
                this.lblNomVous = new JLabel("Nom du joueur");
                this.lblNbWagonsVous = new JLabel("Nombre de wagons : X");
                this.lblNbPvVous = new JLabel("Nombre de points de victoire : X");

        this.panelJActuel = new JPanel(new GridBagLayout());
            this.panelLblJActuel = new JPanel();
                this.lblJActuel = new JLabel("Joueur actuel :");
            this.panelCouleurJActuel = new JPanel();
                this.couleurJActuel = Color.GREEN; // Couleur du joueur actuel
                this.lblCouleurJActuel = new JLabel("Couleur");
                this.lblCouleurJActuel.setForeground(Color.RED);
            this.panelInfosJActuel = new JPanel(new GridLayout(3,1));
                this.lblNomJActuel = new JLabel("Nom du joueur");
                this.lblNbWagonsJActuel = new JLabel("Nombre de wagons : X");
                this.lblNbPvJActuel = new JLabel("Nombre de points de victoire : X");

        // --

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(1, 1, 1, 1);

        this.panelLblVous.add(this.lblVous);
        this.panelCouleurVous.add(this.lblCouleurVous);
        this.panelInfosVous.add(this.lblNomVous);
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

        this.panelVous.setBackground(Color.BLUE);
        this.add(this.panelVous);

        // --

        this.panelLblJActuel.add(this.lblJActuel);
        this.panelCouleurJActuel.add(this.lblCouleurJActuel);
        this.panelInfosJActuel.add(this.lblNomJActuel);
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

        this.panelJActuel.setBackground(Color.RED);
        this.add(this.panelJActuel);

        // -- 
    }
}
