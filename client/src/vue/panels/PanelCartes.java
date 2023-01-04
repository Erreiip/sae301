package client.src.vue.panels;

import client.src.Controleur;
import client.src.metier.commom.Wagon;
import client.src.metier.commom.Objectif;


import javax.swing.*;
import java.awt.*;

public class PanelCartes extends JPanel
{
    private Controleur ctrl;

    private JLabel   lblVersoWagon;
    private JLabel[] lblCartesWagon;
    private JLabel   lblVersoObjectif;

    public PanelCartes(Controleur ctrl)
    {
        this.ctrl = ctrl;

        ImageIcon imgVersoWagon = new ImageIcon(Wagon.getFileVerso().getAbsolutePath());
        this.lblVersoWagon = new JLabel(imgVersoWagon);
        this.add(lblVersoWagon);

        Wagon[] piocheVisible = this.ctrl.getPiocheVisible();
        this.lblCartesWagon = new JLabel[piocheVisible.length];
        int index = 0;
        for (Wagon w : piocheVisible)
        {
            ImageIcon imgRectoWagon = new ImageIcon(w.getFileRecto().getAbsolutePath());
            this.lblCartesWagon[index] = new JLabel(imgRectoWagon);
            this.add(this.lblCartesWagon[index]);
            index++;
        }

        ImageIcon imgVersoObjectif = new ImageIcon(Objectif.getFile().getAbsolutePath());
        this.lblVersoObjectif = new JLabel(imgVersoObjectif);
        this.add(lblVersoObjectif);

        this.setLayout(new GridLayout(7,1));
    }
}
