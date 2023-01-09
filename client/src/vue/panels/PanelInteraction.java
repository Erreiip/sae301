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

    private JButton btnValider;
    private ArrayList<JCheckBox> alCheckBox;
    private Objectif[] alObjectifs;

    JPanel panelDernieresCartes;
    JLabel[] labelDernieresCartes;
    
    private ArrayList<Wagon> dernieresCartes;
    
    public PanelInteraction(Controleur ctrl)
    {
        this.ctrl = ctrl;

        this.setLayout(null);

        this.panelDernieresCartes = new JPanel();

        panelDernieresCartes.setLayout(new GridLayout(1,2, 10,0));

        panelDernieresCartes.setBounds(75, 375, 210, 50);

        JLabel carte1 = new JLabel();
        JLabel carte2 = new JLabel();

        this.labelDernieresCartes = new JLabel[]{carte1, carte2};

        panelDernieresCartes.add(carte1);
        panelDernieresCartes.add(carte2);

        this.add(panelDernieresCartes);

        this.dernieresCartes = new ArrayList<Wagon>();
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
        this.removeAll();
        // Variable contenant les 3 premieres cartes objectif
        this.alObjectifs = ctrl.getPiocheVisibleObj();

        this.alCheckBox = new ArrayList<JCheckBox>();

        for(int cpt = 0; cpt < alObjectifs.length; cpt++)
        {
            if ( alObjectifs[cpt] == null) continue;

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

            // DÃ©clare l'image dans un label et le redimentionne
            Image img = bImgRectoObjectif.getScaledInstance(60, 40, Image.SCALE_SMOOTH);
            JLabel imgLabel = new JLabel(new ImageIcon(img));
            imgLabel.setBounds(25, cpt*42 + 160 , 60, 40);

            // Ajout texte de l'objectif
            JLabel texteVille1 = new JLabel(alObjectifs[cpt].getV1().getNom());
            JLabel texteVille2 = new JLabel(alObjectifs[cpt].getV2().getNom());
            JLabel textePoints = new JLabel("("+alObjectifs[cpt].getNbPoints()+")");

            texteVille1.setBounds(90,cpt*42 + 160,120,40);
            texteVille2.setBounds(215,cpt*42 + 160,120,40);
            textePoints.setBounds(320,cpt*42 + 160,30,40);

            // Les ajoute au panel
            this.add(choix);
            this.add(imgLabel);
            this.add(texteVille1);
            this.add(texteVille2);
            this.add(textePoints);
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
        this.dernieresCartes.add(w);

        if(this.ctrl.secondWagon())
        {
            this.removeAll();
        }
        JLabel lblWagon = new JLabel();
        BufferedImage imgRectoWagon = null;
        try {
            imgRectoWagon = ImageIO.read(new File(w.getFileRecto()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dImgRectoWagon = imgRectoWagon.getScaledInstance(170, 85, Image.SCALE_SMOOTH);
        ImageIcon imgIcon = new ImageIcon(dImgRectoWagon);
        lblWagon = new JLabel(imgIcon);
        lblWagon.setBounds(100, 150, 175, 110);
        this.add(lblWagon);
        this.revalidate();

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

            this.ctrl.piocheObjectif(alObjectifsGardes, alObjectifsDefausse);
            this.ctrl.setActionEnCours(false);
        }

        if(e.getSource() instanceof JCheckBox)
        {
            // On verifie si au moins une case est cochee
            btnValider.setEnabled(false);
            int nbChecked = 0;
            for (JCheckBox checkBox : this.alCheckBox)
            {
                if( checkBox.isSelected() )
                {
                    if ( this.ctrl.getTour() == 1) nbChecked++;
                    else                           btnValider.setEnabled(true);
                } 
            }

            if ( nbChecked > 1 ) this.btnValider.setEnabled(true);
        }
    }

    public void remplissageDerniersWagons()
    {
        this.add(panelDernieresCartes);

        panelDernieresCartes.validate();

        for (int i = 0; i < 2; i++) {
            if(this.dernieresCartes.size()-1 < i) labelDernieresCartes[i].setIcon(null);
            else { 
                BufferedImage imgRectoWagon = null;
                try {
                    imgRectoWagon = ImageIO.read(new File(dernieresCartes.get(i).getFileRecto()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Image dImgRectoWagon = imgRectoWagon.getScaledInstance(100, 50, Image.SCALE_SMOOTH);
                ImageIcon imgIcon = new ImageIcon(dImgRectoWagon);
                labelDernieresCartes[i].setIcon(imgIcon);
            }
        }
        this.dernieresCartes.clear();
        
    }

    public void maj()
    {
        this.removeAll();
        this.validate();
        this.repaint();

        if ( this.ctrl.getTour() == 1)
        {
            this.genererInteractionObj();
        }else
        {
            remplissageDerniersWagons();
        }
    }
}