package client.src.vue.panels;

import client.src.Controleur;
import client.src.metier.common.Objectif;
import client.src.metier.common.Wagon;

import javax.swing.*;
import java.awt.*;

import java.util.ArrayList;

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
        this.alWagons.add(new Wagon(1, "wagon1"));
        this.alWagons.add(new Wagon(2, "wagon2"));
        this.alWagons.add(new Wagon(3, "wagon3"));
        String[] strWagons = new String[this.alWagons.size()];
        for (Wagon w : this.alWagons)
        {
            strWagons[this.alWagons.indexOf(w)] = w.toString();
        }
        for (int i = 0; i < strWagons.length; i++)
            System.out.println(strWagons[i] + "oui");



        this.tpCartes.addTab("Wagons", this.panelWagons);
        this.tpCartes.addTab("Objectifs", this.panelObjectifs);

        this.add(this.tpCartes);
    }
}
