package client.src.vue.panels;

import client.src.Controleur;
import client.src.metier.common.Regles;
import client.src.vue.FramePrincipale;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;


public class PanelPret extends JPanel implements ActionListener
{
    private Controleur ctrl;

    private BufferedImage fond;

    private JSpinner spinner;
    private JButton btnValider;

    public PanelPret(Controleur ctrl)
    {
        this.ctrl = ctrl;

        this.setLayout(null);
        this.setSize(new Dimension(800, 500));

        BufferedImage input = this.ctrl.getFond();
        BufferedImage resized = new BufferedImage(800,500, input.getType());
        
        Graphics2D g = resized.createGraphics();
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        g.drawImage(input,0,0,800,500,null);
        g.dispose();

        this.fond = resized;

        //this.fond = new BufferedImage(800, 500, this.fond.getType());

        Regles regles = this.ctrl.getRegles();

        this.spinner = new JSpinner(new SpinnerNumberModel(regles.getNbJoueursMini(), regles.getNbJoueursMini(), regles.getNbJoueursMaxi(), 1));
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

        pnlInfos.setOpaque(false);
        pnlSaisis.setOpaque(false);

        pnlInfos.setBounds(200,0, 400, 200);
        pnlSaisis.setBounds(200,200,400,200);
        this.add(pnlInfos);
        this.add(pnlSaisis);

        this.btnValider.addActionListener(this);

        this.repaint();
    }  
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;

        g.drawImage(this.fond, 0, 0, this.fond.getWidth(), this.fond.getHeight(), null);
    }
    

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if ( e.getSource() == this.btnValider)
        {
            this.ctrl.setNbJoueurs((Integer) this.spinner.getValue());
            this.ctrl.setIhm(new FramePrincipale(this.ctrl));
        }
    }
}

