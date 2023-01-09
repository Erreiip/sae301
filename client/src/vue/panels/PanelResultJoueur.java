package client.src.vue.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import client.src.Controleur;
import client.src.metier.common.Joueur;

public class PanelResultJoueur extends JPanel implements ActionListener
{
    private Controleur ctrl;

    private Joueur joueur;
    private JLabel lblImage;

    private int indexObjectif;

    public PanelResultJoueur(Controleur ctrl, Joueur j, int rank)
    {
        this.ctrl = ctrl;

        this.joueur = j;

        this.indexObjectif = 0;

        this.setLayout(new GridLayout(1, 4));

        switch (rank)
        {
            case 1 : this.setBackground(new Color(255,215,0  )); break; // couleur or 
            case 2 : this.setBackground(new Color(192,192,192)); break; // couleur argent
            case 3 : this.setBackground(new Color(88 ,41 ,0  )); break; // couleur bronze
            default : break;
        }
    

        this.add(new JLabel("" + rank        , SwingConstants.CENTER));                  // Classement
        this.add(new JLabel("Joueur " + this.joueur.getId()   , SwingConstants.CENTER)); // Joueur X
        this.add(new JLabel("" + this.joueur.getNbPv() , SwingConstants.CENTER));        // Point de victoire

        JPanel panelObjectifs = new JPanel(new BorderLayout());
            JButton btnGauche = new JButton("<");
            this.lblImage = new JLabel(this.joueur.getObjectifs().get(this.indexObjectif).getV1().getNom() + " - " + 
                                       this.joueur.getObjectifs().get(this.indexObjectif).getV2().getNom() + " / " + this.joueur.getObjectifs().size());
            JButton btnDroite = new JButton(">");

        panelObjectifs.add(btnGauche, BorderLayout.WEST);
        panelObjectifs.add(lblImage, BorderLayout.CENTER);
        panelObjectifs.add(btnDroite, BorderLayout.EAST);


        this.add(panelObjectifs);        // Liste des objectifs du joueurs (background vert = réussi, rouge = échoué)

    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() instanceof JButton)
        {
            JButton btn = (JButton) e.getSource();

            if (btn.getText().equals("<"))
            {
                if ( this.indexObjectif == 0) { this.indexObjectif = this.joueur.getObjectifs().size(); }
                else                          { this.indexObjectif--;                                   }
            }
            else if (btn.getText().equals(">"))
            {
                if ( this.indexObjectif == this.joueur.getObjectifs().size()) { this.indexObjectif = 0; }
                else                                                          { this.indexObjectif++;   }
            }

            this.lblImage.setText(this.joueur.getObjectifs().get(this.indexObjectif).getV1().getNom() + " - " + 
                                  this.joueur.getObjectifs().get(this.indexObjectif).getV2().getNom() + " / " + this.joueur.getObjectifs().size());
        }
    }
}
