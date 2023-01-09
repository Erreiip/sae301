package client.src.vue;

import client.src.Controleur;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class FrameAcceuil extends JFrame implements ActionListener
{
    private Controleur ctrl;
    
    private JButton btnCreerServeur;

    public FrameAcceuil(Controleur ctrl)
    {
        this.ctrl = ctrl;

        this.setTitle("Choix du plateau");
        this.setLocationRelativeTo(null);
        this.setSize(800, 500);

        this.setLayout(new BorderLayout());

        JPanel panelHaut = new JPanel(new FlowLayout());
        JPanel panelBtn = new JPanel(new GridBagLayout());

        this.btnCreerServeur = new JButton("Choix du plateau (Fichier XML)");
        this.btnCreerServeur.setPreferredSize(new Dimension(400, 100));
        
        panelHaut.add(new JLabel(new ImageIcon("./client/src/data/jeu_de_plateau.png")));
        panelBtn.add(this.btnCreerServeur, new GridBagConstraints());

        this.add(panelHaut, BorderLayout.NORTH);
        this.add(panelBtn, BorderLayout.CENTER);

        this.btnCreerServeur.addActionListener(this);
  
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == this.btnCreerServeur)
        {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("XML Files", "xml");
            chooser.setFileFilter(filter);

            int returnVal = chooser.showOpenDialog(this);
            if(returnVal == JFileChooser.APPROVE_OPTION) 
            {
                File f = chooser.getSelectedFile();
                this.ctrl.lireXml(f, true);

                this.ctrl.setIhm(new FramePret(this.ctrl));
                this.dispose();
            }
            
        }

        /*if (e.getSource() == this.btnRejoindre)
        {
            this.ctrl.creerClient();
        }*/
    }
}