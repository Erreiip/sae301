package client.src.vue.panels;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

import client.src.Controleur;
import client.src.metier.common.Objectif;
import client.src.metier.common.Wagon;


public class PanelInteraction extends JPanel implements ActionListener
{
    Controleur ctrl;

    private JPanel panelInteraction;
    private JButton btnValider;
    private ArrayList<JCheckBox> alCheckBox;
    private Objectif[] alObjectifs;
    private JLabel lblWagon;
    
    public PanelInteraction(Controleur ctrl)
    {
        this.ctrl = ctrl;
        this.setLayout(null);
    }

    public void afficherWagon(Icon icon)
    {
        JLabel labelImageWagon = new JLabel(icon);
        this.add(labelImageWagon);
    }

    public void retirerImageWagon()
    {
        this.removeAll();
    }

    public void genererInteractionObj()
    {
        // Variable contenant les 3 premieres cartes objectif
        this.alObjectifs = ctrl.getPiocheVisibleObj();

        this.alCheckBox = new ArrayList<JCheckBox>();

        for(int cpt = 0; cpt < alObjectifs.length; cpt++)
        {
            // Ajout case a cocher
            JCheckBox choix = new JCheckBox();
            choix.addActionListener(this);
            choix.setBounds(2,cpt * 42 + 172, 20, 20);
            this.alCheckBox.add(choix);


            BufferedImage bImgRectoObjectif = null;
            try
            {
                bImgRectoObjectif = ImageIO.read(new File (alObjectifs[cpt].getFileRecto()));
            }
            catch (Exception e) { e.printStackTrace(); }

            // Déclare l'image dans un label et le redimentionne
            Image img = bImgRectoObjectif.getScaledInstance(60, 40, Image.SCALE_SMOOTH);
            JLabel imgLabel = new JLabel(new ImageIcon(img));
            imgLabel.setBounds(25, cpt*42 + 160 , 60, 40);

            // Ajout texte de l'objectif
            JLabel texteObjectif = new JLabel(alObjectifs[cpt].getV1().getNom() + " "
                                            + alObjectifs[cpt].getV2().getNom() + " "
                                            + alObjectifs[cpt].getNbPoints());
            texteObjectif.setBounds(90,cpt*42 + 160,250,40);

            // Les ajoute au panel
            this.add(choix);
            this.add(imgLabel);
            this.add(texteObjectif);
        }

        // Declare et place le bouton valider
        btnValider = new JButton("Valider");
        btnValider.setEnabled(false);
        btnValider.setBounds(120, 300, 100, 40);

        // Ajoute l'action listener au bouton
        btnValider.addActionListener(this);

        // Ajoute le bouton au panel
        this.add(btnValider);

        this.repaint();
    }

    public void genererInteractionWagon(Wagon w)
    {
        BufferedImage imgRectoWagon = null;
        try {
            imgRectoWagon = ImageIO.read(new File(w.getFileRecto()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dImgRectoWagon = imgRectoWagon.getScaledInstance(170, 85, Image.SCALE_SMOOTH);
        ImageIcon imgIcon = new ImageIcon(dImgRectoWagon);
        if(this.lblWagon == null)
        {
            lblWagon = new JLabel(imgIcon);
            lblWagon.setBounds(150, 150, 175, 110);
            this.add(lblWagon);
        }else{
            lblWagon.setIcon(imgIcon);
        }
        this.repaint();
    }

    public void finGenerer()
    {
        this.removeAll();
        this.repaint();
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == this.btnValider)
        {
            ArrayList<Objectif> alObjectifsGardes   = new ArrayList<Objectif>();
            ArrayList<Objectif> alObjectifsDefausse = new ArrayList<Objectif>();

            for(int index = 0; index < this.alCheckBox.size(); index++) 
            {
                // Si la case est cochee, on ajoute l'objectif au joueur
                if(this.alCheckBox.get(index).isSelected())
                {
                    ctrl.getJoueur().ajouterObjectif(alObjectifs[index]);
                    alObjectifsGardes.add(alObjectifs[index]);
                }
                // Sinon on l'ajoute a la defausse
                else
                {
                    alObjectifsDefausse.add(alObjectifs[index]);
                }
            }
            // On vide le panel et on le cache
            this.removeAll();
            this.validate();
            this.repaint();
            ctrl.supprimerObj(alObjectifsGardes);
            ctrl.supprimerObjToDef(alObjectifsDefausse);
        }

        if(e.getSource() instanceof JCheckBox)
        {
            // On verifie si au moins une case est cochee
            btnValider.setEnabled(false);
            for (JCheckBox checkBox : this.alCheckBox)
            {
                if( checkBox.isSelected() )
                {
                    btnValider.setEnabled(true);
                    break;
                }
            }
        }
    }
}