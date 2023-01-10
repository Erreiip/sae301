package client.src.vue.panels;

import client.src.Controleur;
import client.src.metier.common.Regles;
import client.src.vue.FrameNomsCouleursJoueurs;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;


public class PanelPret extends JPanel implements ActionListener
{
    private Controleur ctrl;

    private JSpinner spinner;
    private JButton btnValider;

    private JCheckBox cbModeDebug;
    private JCheckBox cbColorierObjectifs;

    private BufferedImage fond;

    public PanelPret(Controleur ctrl)
    {
        this.ctrl = ctrl;

        this.setLayout(new GridLayout(2,1));
        this.setSize(new Dimension(800, 500));

        BufferedImage input = this.ctrl.getFond();
        BufferedImage resized = new BufferedImage(800,500, input.getType());
        
        Graphics2D g = resized.createGraphics();
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        g.drawImage(input,0,0,800,500,null);
        g.dispose();

        this.fond = resized;

        //this.fond = new BufferedImage(800, 500, this.fond.getType());

        Regles regles = this.ctrl.getRegles();

        this.spinner = new JSpinner(new SpinnerNumberModel(regles.getNbJoueursMini(), regles.getNbJoueursMini(), regles.getNbJoueursMaxi(), 1));
        this.btnValider = new JButton("Valider");
        this.cbModeDebug = new JCheckBox("Activer le mode debug");
        this.cbColorierObjectifs = new JCheckBox("Colorier les cartes objectifs");

        JPanel pnlInfos = new JPanel(new GridLayout(5, 2));
        JPanel pnlSaisis = new JPanel(new BorderLayout());
            JPanel panelChoix = new JPanel(new BorderLayout());
                JPanel panelNbJoueur = new JPanel(new GridBagLayout());
                JPanel panelCb = new JPanel(new GridLayout(1,2));
                    JPanel panelCb1 = new JPanel();
                    JPanel panelCb2 = new JPanel();
            JPanel panelValider = new JPanel();

        pnlInfos.add(new JLabel("Nombre de joueurs Minimum : "               , JLabel.RIGHT));
        pnlInfos.add(new JLabel("" + regles.getNbJoueursMini()));

        pnlInfos.add(new JLabel("Nombre de joueurs Maximum : "               , JLabel.RIGHT));
        pnlInfos.add(new JLabel("" + regles.getNbJoueursMaxi()));

        pnlInfos.add(new JLabel("Nombre de joueurs Pour les voies Doubles : ", JLabel.RIGHT));
        pnlInfos.add(new JLabel("" + regles.getNbJoueursVoieDouble()));

        pnlInfos.add(new JLabel("Nombre de wagons pour la fin de partie: "   , JLabel.RIGHT));
        pnlInfos.add(new JLabel("" + regles.getNbWagonsFinParties()));

        pnlInfos.add(new JLabel("Nombre de wagons par joueurs : "            , JLabel.RIGHT));
        pnlInfos.add(new JLabel("" + regles.getNbWagonsParJoueurs()));

        panelNbJoueur.add(new JLabel("Nombre de joueurs : "));
        panelNbJoueur.add(this.spinner);

        panelCb1.add(this.cbModeDebug);
        panelCb2.add(this.cbColorierObjectifs);

        panelCb.add(panelCb1);
        panelCb.add(panelCb2);

        panelChoix.add(panelNbJoueur, BorderLayout.CENTER);
        panelChoix.add(panelCb, BorderLayout.SOUTH);

        panelValider.add(this.btnValider);
  
        pnlSaisis.add(panelChoix, BorderLayout.CENTER);
        pnlSaisis.add(panelValider, BorderLayout.SOUTH);

        pnlInfos.setOpaque(false);
        pnlSaisis.setOpaque(false);
        panelChoix.setOpaque(false);
        panelNbJoueur.setOpaque(false);
        panelCb.setOpaque(false);
        panelCb1.setOpaque(false);
        panelCb2.setOpaque(false);
        panelValider.setOpaque(false);

        this.add(pnlInfos);
        this.add(pnlSaisis);

        this.btnValider.addActionListener(this);

        this.repaint();
    }  
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g.drawImage(this.fond, 0, 0, this.fond.getWidth(), this.fond.getHeight(), null);
    }
    

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if ( e.getSource() == this.btnValider)
        {
            this.ctrl.setNbJoueurs((Integer) this.spinner.getValue());

            if (this.cbModeDebug.isSelected())
                this.ctrl.modeDebug();

            this.ctrl.colorier(this.cbColorierObjectifs.isSelected());

            this.ctrl.setIhm(new FrameNomsCouleursJoueurs(this.ctrl));
        }
    }
}

