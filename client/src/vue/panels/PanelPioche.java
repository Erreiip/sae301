package client.src.vue.panels;

import client.src.Controleur;
import client.src.metier.common.Objectif;
import client.src.metier.common.Wagon;
import client.src.vue.mouseAdapter.*;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;

public class PanelPioche extends JPanel
{
    private Controleur ctrl;

    private JLabel   lblVersoWagon;
    private JLabel[] lblCartesWagon;
    private JLabel   lblVersoObjectif;

    public PanelPioche(Controleur ctrl)
    {
        this.ctrl = ctrl;

        this.setLayout(new GridLayout(7,1));
        this.setPreferredSize(new Dimension(180,600));
        
        Border blackline = BorderFactory.createLineBorder(Color.black);
        this.setBorder(blackline);

        // Image verso Wagon & verso Objectif
        BufferedImage imgVersoWagon = null;
        BufferedImage imgVersoObjectif = null;
        try {
            imgVersoWagon    = ImageIO.read(new File(Wagon.getFileVerso()));
            imgVersoObjectif = ImageIO.read(new File(Objectif.getFileVerso()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Image dImgVersoWagon    = imgVersoWagon.getScaledInstance(170, 85, Image.SCALE_SMOOTH);
        Image dImgVersoObjectif = imgVersoObjectif.getScaledInstance(170, 85, Image.SCALE_SMOOTH);

        this.lblVersoWagon = new JLabel(new ImageIcon(dImgVersoWagon));
        this.lblVersoObjectif = new JLabel(new ImageIcon(dImgVersoObjectif));
        

        // Images rectos Wagons
        Wagon[] piocheVisible = this.ctrl.getPiocheVisible();

        this.lblCartesWagon = new JLabel[piocheVisible.length];

        int index = 0;
        for (Wagon w : piocheVisible)
        {
            BufferedImage imgRectoWagon = null;
            try {
                imgRectoWagon = ImageIO.read(new File(w.getFileRecto()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            Image dImgRectoWagon = imgRectoWagon.getScaledInstance(170, 85, Image.SCALE_SMOOTH);

            this.lblCartesWagon[index] = new JLabel(new ImageIcon(dImgRectoWagon));
            this.lblCartesWagon[index].addMouseListener(new MouseAdapterWagonsListener(ctrl, lblCartesWagon));
            index++;
        }

        
        this.add(this.lblVersoWagon);

        for (JLabel lbl : this.lblCartesWagon)
            this.add(lbl);

        this.add(this.lblVersoObjectif);
        

        this.lblVersoObjectif.addMouseListener(new MouseAdapterVersoObjectif(this.ctrl));
        this.lblVersoWagon.addMouseListener(new MouseAdapterVersoWagon(ctrl));

    }

    public void majPioche()
    {
        Wagon[] piocheVisible = this.ctrl.getPiocheVisible();

        int index = 0;
        for (JLabel l : lblCartesWagon)
        {
            if(piocheVisible[index] == null)
            {
                if(l.getMouseListeners().length>0)
                {
                    l.setIcon(null);
                    System.out.println(index + "----" + piocheVisible[index]);
                    l.removeMouseListener(l.getMouseListeners()[0]);
                }
            }else
            {
                BufferedImage imgRectoWagon = null;
                try {
                    imgRectoWagon = ImageIO.read(new File(piocheVisible[index].getFileRecto()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
                Image dImgRectoWagon = imgRectoWagon.getScaledInstance(170, 85, Image.SCALE_SMOOTH);
    
                l.setIcon(new ImageIcon(dImgRectoWagon));
            }
            index++;
        }

        if(ctrl.getWagonVerso() == null && lblVersoWagon.getMouseListeners().length>0){ 
            lblVersoWagon.setIcon(null);
            lblVersoWagon.removeMouseListener(lblVersoWagon.getMouseListeners()[0]);
        }
    }

    public void suppBtnPiocheObj() 
    {
        lblVersoObjectif.setIcon(null);
        lblVersoObjectif.removeMouseListener(lblVersoObjectif.getMouseListeners()[0]);
    }
}