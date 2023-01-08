package client.src.vue;

import java.awt.*;
import java.awt.event.*;


import javax.swing.*;

import client.src.Controleur;
import client.src.metier.common.Regles;

public class FramePret extends JFrame implements ActionListener
{
    private Controleur ctrl;

    private JSpinner spinner;
    private JButton btnValider;

    public FramePret(Controleur ctrl)
    {
        this.ctrl = ctrl;
        this.setLayout(new GridLayout(2,1));

        Regles regles = this.ctrl.getRegles();

        this.spinner = new JSpinner();
        this.btnValider = new JButton("Valider");
        
        JPanel pnlInfos = new JPanel(new GridLayout(5, 1));
        JPanel pnlSaisis = new JPanel();

        pnlInfos.add(new JLabel("Nombre de joueurs Minimum : " + regles.getNbJoueursMini(), JLabel.CENTER));
        pnlInfos.add(new JLabel("Nombre de joueurs Maximum : " + regles.getNbJoueursMaxi(), JLabel.CENTER));
        pnlInfos.add(new JLabel("Nombre de joueurs Pour les voies Doubles : " + regles.getNbJoueursVoieDouble(), JLabel.CENTER));
        pnlInfos.add(new JLabel("Nombre de wagons pour la fin de partie: " + regles.getNbWagonsFinParties(), JLabel.CENTER));
        pnlInfos.add(new JLabel("Nombre de wagons par joueurs : " + regles.getNbWagonsParJoueurs(), JLabel.CENTER));



        pnlSaisis.add(new JLabel("Nombre de Joueurs : "));
        pnlSaisis.add(this.spinner);
        pnlSaisis.add(this.btnValider);

        this.add(pnlInfos);
        this.add(pnlSaisis);

        this.btnValider.addActionListener(this);

        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if ( e.getSource() == this.btnValider)
        {
            this.ctrl.setNbJoueurs((Integer) this.spinner.getValue());
            this.ctrl.setIhm(new FramePrincipale(this.ctrl));
            this.dispose();
        }
        
    }
}
