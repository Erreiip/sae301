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
    
    private JButton btnJouer;

    public FrameAcceuil(Controleur ctrl)
    {
        this.ctrl = ctrl;

        this.setTitle("Accueil");
        this.setLocationRelativeTo(null);
        this.setSize(500, 500);

        this.btnJouer = new JButton("Jouer");

        this.add(this.btnJouer);

        this.btnJouer.addActionListener(this);
    
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == this.btnJouer)
        {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("XML Files", "xml");
            chooser.setFileFilter(filter);

            int returnVal = chooser.showOpenDialog(this);
            if(returnVal == JFileChooser.APPROVE_OPTION) 
            {
                File f = chooser.getSelectedFile();
                this.ctrl.lireXml(f);

                // lancer frame principale
            }
            
        }
    }
}