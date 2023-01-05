package client.src.vue.panels;

import client.src.Controleur;
import client.src.metier.common.Objectif;
import client.src.metier.common.Wagon;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PanelCartesInteraction extends JPanel
{
    private Controleur ctrl;

    private JTabbedPane tpCartes;
        private JPanel panelWagons;
            private ArrayList<Wagon> alWagons;
        private JPanel panelObjectifs;
            private ArrayList<Objectif> alObjectifs;

    public PanelCartesInteraction(Controleur ctrl)
    {
        this.ctrl = ctrl;

        this.setPreferredSize(new Dimension(300,600));
        this.setLayout(new GridLayout(2, 1));

        this.tpCartes = new JTabbedPane();
        this.panelWagons = new JPanel();
        this.panelObjectifs = new JPanel();

        this.alWagons = this.ctrl.getMainJoueur();

        this.alWagons.add(new Wagon(0, "wagon1"));
        this.alWagons.add(new Wagon(1, "wagon2"));
        this.alWagons.add(new Wagon(2, "wagon3"));

        String[] columsName = {"Carte","Nombre"};
        String[][] data = new String[this.alWagons.size()][2];
        for (int i = 0; i < this.alWagons.size(); i++)
        {
            data[i][0] = this.alWagons.get(i).getFileRecto();
            
        }




        this.tpCartes.addTab("Wagons", this.panelWagons);
        this.tpCartes.addTab("Objectifs", this.panelObjectifs);

        this.add(this.tpCartes);
    }   
}