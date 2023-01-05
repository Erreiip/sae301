package client.src.vue.panels;

import client.src.Controleur;
import client.src.metier.common.Objectif;
import client.src.metier.common.Wagon;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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

        // PANEL WAGONS ---
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



        // PANEL OBJECTIFS ---
        this.alObjectifs = this.ctrl.getObjectifsJoueur();

        String[] columnsName = {"Image", "Ville 1", "Ville 2", "Points de Victoire"};

        String[][] data = {};

        DefaultTableModel model = new DefaultTableModel(data, columnsName);

        JTable table = new JTable(model)
        {
            public Class getColumnClass(int column)
            {
                switch (column)
                {
                    case 0:  return ImageIcon.class;
                    case 1:  return String.class;
                    case 2:  return String.class;
                    case 3:  return Integer.class;
                    default: return String.class;
                }
            }
        };

        table.setRowHeight(50);

        JScrollPane scrollPane = new JScrollPane(table);
        this.panelObjectifs.add(scrollPane);

        this.tpCartes.addTab("Objectifs", this.panelObjectifs);

        // --
        this.add(this.tpCartes);
    }   

    public class WagonListRenderer extends DefaultListCellRenderer
    {
        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
        {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            // imageMap.get((index + "")).getImage().flush();
            // label.setIcon(imageMap.get((index + "")));
            ImageIcon icon = new ImageIcon("./assets/i2.png");
            Image image = icon.getImage();
            Image newImg = image.getScaledInstance(80, 50,  java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImg);
            
            label.setIcon(icon);
            label.setPreferredSize(new Dimension(200, 60));
            label.setHorizontalTextPosition(JLabel.RIGHT);
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