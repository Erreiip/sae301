package client.src.vue.panels;

import javax.swing.*;
import javax.swing.text.StyledEditorKit.FontSizeAction;

import java.awt.*;
import java.awt.event.*;

import client.src.Controleur;
import client.src.metier.common.Joueur;

public class PanelResultJoueur extends JPanel implements ActionListener
{
    private Controleur ctrl;

    private Joueur joueur;

    private JPanel panelObjectifs;
    private JLabel lblImage;

    private int indexObjectif;

    public PanelResultJoueur(Controleur ctrl, Joueur j, int rank)
    {
        this.ctrl = ctrl;

        this.joueur = j;

        this.panelObjectifs = new JPanel(new BorderLayout());
        this.lblImage = null;

        this.indexObjectif = 0;

        this.setLayout(new GridLayout(1, 5));

        switch (rank)
        {
            case 1 : this.setBackground(new Color(255,215,0  )); break; // couleur or 
            case 2 : this.setBackground(new Color(192,192,192)); break; // couleur argent
            case 3 : this.setBackground(new Color(222,184,135)); break; // couleur bronze
            default : break;
        }
    
        Font font = new Font("Arial", Font.BOLD, 20);

        JLabel lblRank = new JLabel("" + rank, SwingConstants.CENTER);                      // Classement
        lblRank.setFont(new Font("Arial", Font.BOLD, (30-(rank*2))));
        this.add(lblRank);

        JLabel lblNom = new JLabel("Joueur " + this.joueur.getId(), SwingConstants.CENTER); // Joueur X
        lblNom.setFont(font);
        lblNom.setForeground(new Color(this.joueur.getCouleur()));
        this.add(lblNom);

        JLabel lblRlpl = new JLabel("", SwingConstants.CENTER);                             // Route la plus longue
        if (this.ctrl.getJoueursCheminPlusLong().contains(this.joueur))
        {
            ImageIcon imageIcon = new ImageIcon("./client/src/data/rlpl.png");
            Image image = imageIcon.getImage();
            Image newimg = image.getScaledInstance(75, 75,  java.awt.Image.SCALE_SMOOTH);
            lblRlpl.setIcon(new ImageIcon(newimg));
        }


        this.add(lblRlpl, BorderLayout.CENTER);

        JLabel lblPv = new JLabel("" + this.joueur.getNbPv() , SwingConstants.CENTER);      // Point de victoire
        lblPv.setFont(font);
        this.add(lblPv);        

        //JButton btnGauche = new JButton(new ImageIcon("./client/src/data/gauche.png"));
        JButton btnGauche = new JButton("<");

        ImageIcon icon = new ImageIcon(this.joueur.getObjectifs().get(this.indexObjectif).getFileRecto());
        Image image = icon.getImage();
        Image newimg = image.getScaledInstance(150, 80,  java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);
        this.lblImage = new JLabel(icon);

        if (this.joueur.getObjectifs().get(this.indexObjectif).isPrit())
            this.panelObjectifs.setBackground(new Color(0,190,0));
        else
            this.panelObjectifs.setBackground(new Color(190,0,0));

        //JButton btnDroite = new JButton(new ImageIcon("./client/src/data/rlpl.png"));
        JButton btnDroite = new JButton(">");

        panelObjectifs.add(btnGauche, BorderLayout.WEST  );
        panelObjectifs.add(lblImage , BorderLayout.CENTER);
        panelObjectifs.add(btnDroite, BorderLayout.EAST  );

        btnGauche.addActionListener(this);
        btnDroite.addActionListener(this);


        this.add(panelObjectifs);        // Liste des objectifs du joueurs (background vert = réussi, rouge = échoué)

    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() instanceof JButton)
        {
            JButton btn = (JButton) e.getSource();

            if (btn.getText().equals("<"))
            {
                if ( this.indexObjectif == 0) { this.indexObjectif = (this.joueur.getObjectifs().size()-1); }
                else                          { this.indexObjectif--;                                   }
            }
            else if (btn.getText().equals(">"))
            {
                if ( this.indexObjectif == (this.joueur.getObjectifs().size()-1)) { this.indexObjectif = 0; }
                else                                                          { this.indexObjectif++;   }
            }

            ImageIcon icon = new ImageIcon(this.joueur.getObjectifs().get(this.indexObjectif).getFileRecto());
            Image image = icon.getImage();
            Image newimg = image.getScaledInstance(150, 80,  java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newimg);

            if (this.joueur.getObjectifs().get(this.indexObjectif).isPrit())
                this.panelObjectifs.setBackground(new Color(0,215,0));
            else
                this.panelObjectifs.setBackground(new Color(215,0,0));

            this.lblImage.setIcon(icon);
        }
    }
}
