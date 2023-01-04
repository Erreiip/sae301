package client.src.vue.panels;

import client.src.Controleur;
import client.src.metier.common.Objectif;
import client.src.metier.common.Wagon;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
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
            imgVersoWagon = ImageIO.read(Wagon.getFileVerso());
            imgVersoObjectif = ImageIO.read(Objectif.getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Image dImgVersoWagon = imgVersoWagon.getScaledInstance(170, 85, Image.SCALE_SMOOTH);
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
                imgRectoWagon = ImageIO.read(w.getFileRecto());
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            Image dImgRectoWagon = imgRectoWagon.getScaledInstance(170, 85, Image.SCALE_SMOOTH);

            this.lblCartesWagon[index] = new JLabel(new ImageIcon(dImgRectoWagon));
            index++;
        }
        
        this.add(this.lblVersoWagon);

        for (JLabel lbl : this.lblCartesWagon)
            this.add(lbl);

        this.add(this.lblVersoObjectif);

    }
}
