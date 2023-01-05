package client.src.vue;

import client.src.Controleur;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.esotericsoftware.kryonet.Client;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class FrameAcceuil extends JFrame implements ActionListener
{
    private Controleur ctrl;
    
    private JButton btnCreerServeur;
    private JButton btnRejoindre;


    public FrameAcceuil(Controleur ctrl)
    {
        this.ctrl = ctrl;

        this.setLayout(new GridLayout(2,1));
        this.setTitle("Accueil");
        this.setLocationRelativeTo(null);
        this.setSize(500, 500);

        this.btnCreerServeur = new JButton("Créer");
        this.btnRejoindre = new JButton("Rejoindre");


        this.add(this.btnCreerServeur);
        this.add(this.btnRejoindre);

        this.btnCreerServeur.addActionListener(this);
        this.btnRejoindre   .addActionListener(this);
    
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
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
                this.ctrl.lireXml(f);

                this.dispose();
                this.ctrl.setIhm(new FramePrincipale(this.ctrl));
            }
            
        }

        if (e.getSource() == this.btnCreerServeur)
        {
            this.ctrl.creerClient();
        }
    }
}