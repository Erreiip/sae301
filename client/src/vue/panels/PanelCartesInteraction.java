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

    private final Map<String, ImageIcon> imageMap;

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

        String[] strWagons = new String[this.alWagons.size()];
        for (Wagon w : this.alWagons)
        {
            strWagons[this.alWagons.indexOf(w)] = w.toString();
        }
        
        imageMap = createImageMap(this.alWagons);
        JList<String> listWagons = new JList<String>(strWagons);
        listWagons.setCellRenderer(new WagonListRenderer());

        JScrollPane scroll = new JScrollPane(listWagons);
        scroll.setPreferredSize(new Dimension(280, 550));

        this.panelWagons.add(scroll);

        this.tpCartes.addTab("Wagons", this.panelWagons);
        this.tpCartes.addTab("Objectifs", this.panelObjectifs);

        this.add(this.tpCartes);
    }   

    public class WagonListRenderer extends DefaultListCellRenderer
    {
        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
        {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            imageMap.get((index + "")).getImage().flush();
            label.setIcon(imageMap.get((index + "")));
            label.setHorizontalTextPosition(JLabel.LEFT);
            return label;
        }
    }

    private Map<String, ImageIcon> createImageMap(ArrayList<Wagon> list)
    {
        Map<String, ImageIcon> map = new HashMap<>();

        for (Wagon w : list)
        {
            map.put(String.valueOf(w.getCouleur()), new ImageIcon(w.getFileRecto()));
        }
        return map;
    }
}