package client.src.vue.panels;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.*;

import client.src.Controleur;
import client.src.metier.common.Objectif;
import client.src.metier.common.Route;
import client.src.metier.common.Wagon;


public class PanelInteraction extends JPanel implements ActionListener
{
    Controleur ctrl;

    private JButton btnValider;
    private ArrayList<JCheckBox> alCheckBox;
    private Objectif[] alObjectifs;

    private JPanel   panelDernieresCartes;
    private JLabel[] labelDernieresCartes;
    private JLabel   txtDernieresCartes1;
    private JLabel   txtDernieresCartes2;

    private ArrayList<Wagon> alDernieresCartes;

    private ArrayList<Color> alCouleurs;
    private ButtonGroup buttonGroup;
    private ArrayList<JRadioButton> alRadioButtons;
    private JButton btnInteractionCartes;

    private Route r;
    private int type;
    
    public PanelInteraction(Controleur ctrl)
    {
        this.ctrl = ctrl;

        this.r    = null;
        this.type = 0;

        this.setLayout(null);

        this.panelDernieresCartes = new JPanel();

        panelDernieresCartes.setLayout(null);

        panelDernieresCartes.setBounds(75, 300, 210, 100);

        JLabel carte1   = new JLabel();
        carte1.setBounds(0,50,100,50);

        JLabel carte2   = new JLabel();
        carte2.setBounds(110,50,100,50);

        this.txtDernieresCartes1 = new JLabel("");
        this.txtDernieresCartes1.setBounds(50,5,150,20);

        this.txtDernieresCartes2 = new JLabel("");
        this.txtDernieresCartes2.setBounds(25,25,160,20);

        this.labelDernieresCartes = new JLabel[]{carte1, carte2};

        panelDernieresCartes.add(carte1);
        panelDernieresCartes.add(carte2);
        panelDernieresCartes.add(this.txtDernieresCartes1);
        panelDernieresCartes.add(this.txtDernieresCartes2);

        this.add(panelDernieresCartes);

        this.alDernieresCartes = new ArrayList<Wagon>();
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
        this.alDernieresCartes.add(w);

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

    public void genererInteractionCartes( Route r , int type)        
    { 
        this.alRadioButtons = new ArrayList<JRadioButton>();

        this.r = r;
        this.type = type;

        this.removeAll();

        HashMap<Color, Integer> hmCount = this.ctrl.getJetonsCouleurJoueur();

        alCouleurs = new ArrayList<Color>();

        for ( Color c : hmCount.keySet() )
        {
            if ( hmCount.get(c) >= r.getCout() )
            {
                alCouleurs.add(c);
            }
        }

        this.setLayout(new GridLayout(alCouleurs.size() + 1 , 2));

       this.buttonGroup = new ButtonGroup();
        
        for (Color c : alCouleurs )
        {
            JLabel lbl = new JLabel();

            JRadioButton rb = new JRadioButton();
            this.buttonGroup.add(rb);
            
            this.alRadioButtons.add(rb);

            Wagon w = ctrl.getWagonCouleur(c);

            BufferedImage imgRectoWagon = null;
            try {
                imgRectoWagon = ImageIO.read(new File(w.getFileRecto()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Image dImgRectoWagon = imgRectoWagon.getScaledInstance(150, 85, Image.SCALE_SMOOTH);
            ImageIcon imgIcon = new ImageIcon(dImgRectoWagon);
            lbl.setIcon(imgIcon);
            lbl.setOpaque(true);
            this.add(lbl);
            this.add(rb);
        }

        this.btnInteractionCartes = new JButton("Valider");
        this.btnInteractionCartes.addActionListener(this);
        this.add(this.btnInteractionCartes);

        this.revalidate();
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

            Objectif[] obj = this.ctrl.getPiocheVisibleObj();
            
            if(obj[0] == null){ctrl.suppBtnPiocheObj();}

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

        if(e.getSource() == this.btnInteractionCartes)
        {
            for (int i = 0; i < this.alRadioButtons.size(); i++) {
                if (this.alRadioButtons.get(i).isSelected()) 
                {
                    this.ctrl.ajouterRoute( this.r, this.alCouleurs.get(i), this.type);
                    this.r = null;
                    this.type = 0;
                    this.ctrl.setActionEnCours(false);
                }
            }
        }
    }

    public void remplissageDerniersWagons()
    {
        this.add(panelDernieresCartes);

        panelDernieresCartes.setBorder(BorderFactory.createLineBorder(Color.black));

        panelDernieresCartes.validate();

        boolean vide = true;

        for (int i = 0; i < 2; i++) {
            if(this.alDernieresCartes.size()-1 < i) labelDernieresCartes[i].setIcon(null);
            else {
                vide = false;
                BufferedImage imgRectoWagon = null;
                try {
                    imgRectoWagon = ImageIO.read(new File(alDernieresCartes.get(i).getFileRecto()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Image dImgRectoWagon = imgRectoWagon.getScaledInstance(100, 50, Image.SCALE_SMOOTH);
                ImageIcon imgIcon = new ImageIcon(dImgRectoWagon);
                labelDernieresCartes[i].setIcon(imgIcon);
            }
        }

        if(vide){
            panelDernieresCartes.setBorder(null);
            this.txtDernieresCartes1.setText(null);
            this.txtDernieresCartes2.setText(null);
        }
        else 
        {
            panelDernieresCartes.setBorder(BorderFactory.createLineBorder(Color.black));
            this.txtDernieresCartes1.setText("cartes piochées");
            this.txtDernieresCartes2.setText("par le joueur précédent");
        }   
        this.alDernieresCartes.clear();
        
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