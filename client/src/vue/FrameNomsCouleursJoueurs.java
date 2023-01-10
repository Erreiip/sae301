package client.src.vue;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import client.src.Controleur;
import client.src.metier.common.Joueur;

public class FrameNomsCouleursJoueurs extends JFrame implements ActionListener
{
    private Controleur ctrl;

    private ArrayList<Joueur> tabJoueur;

    private JTextField[] tabTxt;
    private JButton[] tabBtn;

    private JButton btnValider;

    public FrameNomsCouleursJoueurs(Controleur ctrl)
    {   
        this.ctrl = ctrl;

        this.tabJoueur = this.ctrl.getAlJoueurs();

        this.setTitle("Choix du nom et de la couleur des joueurs");
        this.setSize(600, (this.tabJoueur.size()+1)*60);
        this.setLayout(new BorderLayout());

        JPanel panelHaut = new JPanel(new GridLayout(this.tabJoueur.size(), 3));
            this.tabTxt = new JTextField[this.tabJoueur.size()];
            this.tabBtn = new JButton[this.tabJoueur.size()];

        JPanel panelValider = new JPanel();
            this.btnValider = new JButton("Valider");

        for (Joueur j : this.tabJoueur)
        {
            int index = this.tabJoueur.indexOf(j);
            panelHaut.add(new JLabel("Joueur " + (index+1) + " : ", SwingConstants.RIGHT));

            this.tabTxt[index] = new JTextField("Joueur " + (index+1));
            panelHaut.add(this.tabTxt[index]);
        
            this.tabBtn[index] = new JButton("Changer de couleur");
            this.tabBtn[index].setBackground(new Color(j.getCouleur()));
            this.tabBtn[index].addActionListener(this);
            panelHaut.add(this.tabBtn[index]);
        }

        panelValider.add(this.btnValider);
        this.btnValider.addActionListener(this);

        this.add(panelHaut, BorderLayout.CENTER);
        this.add(panelValider, BorderLayout.SOUTH);

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        for (JButton b : this.tabBtn)
        {
            if (e.getSource() == b)
            {
                int re = (int)(Math.random() * 255);
                int gr = (int)(Math.random() * 255);
                int bl = (int)(Math.random() * 255);

                b.setBackground(new Color(re,gr,bl));
            }
        }

        if (e.getSource() == this.btnValider)
        {
            for (Joueur j : this.tabJoueur)
            {
                int index = this.tabJoueur.indexOf(j);
                j.setNom(this.tabTxt[index].getText());
                j.setCouleur(this.tabBtn[index].getBackground().getRGB());
            }

            this.dispose();
            this.ctrl.setIhm(new FramePrincipale(this.ctrl));
        }
    }
}