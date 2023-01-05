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

    private JLabel   lblVersoWagon;
    private JLabel[] lblCartesWagon;
    private JLabel   lblVersoObjectif;

    public PanelCartesInteraction(Controleur ctrl)
    {
        this.ctrl = ctrl;

        this.setLayout(new GridLayout(2, 1));
    }
}
