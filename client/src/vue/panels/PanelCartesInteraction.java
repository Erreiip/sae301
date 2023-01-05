package client.src.vue.panels;

import client.src.Controleur;
import client.src.metier.common.Objectif;
import client.src.metier.common.Wagon;
import client.src.vue.common.ImageRenderer;
import client.src.vue.common.ModelTableObjectifs;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

import java.util.ArrayList;
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
            private JScrollPane spTabObjectifs;
            private JTable tableObjectifs;
            private ModelTableObjectifs md;

    public PanelCartesInteraction(Controleur ctrl)
    {
        this.ctrl = ctrl;

        this.setPreferredSize(new Dimension(350,600));
        this.setLayout(new GridLayout(2, 1));

        this.tpCartes = new JTabbedPane();
        this.panelWagons = new JPanel();
        this.panelObjectifs = new JPanel();

        // PANEL WAGONS ---
        this.alWagons = this.ctrl.getJoueur().getMain();

        String[] strWagons = new String[this.alWagons.size()];
        for (Wagon w : this.alWagons)
        {
            strWagons[this.alWagons.indexOf(w)] = w.toString();
        }
        
        imageMap = createImageMap(this.alWagons);
        JList<String> listWagons = new JList<String>(strWagons);
        listWagons.setCellRenderer(new WagonListRenderer());

        JScrollPane scroll = new JScrollPane(listWagons);
        scroll.setPreferredSize(new Dimension(330, 550));

        this.panelWagons.add(scroll);

        this.tpCartes.addTab("Wagons", this.panelWagons);



        // PANEL OBJECTIFS ---
        this.spTabObjectifs = new JScrollPane();
        this.tableObjectifs = new JTable();
        this.md = new ModelTableObjectifs(this.ctrl);
        this.tableObjectifs.setModel(this.md);
        this.tableObjectifs.setShowVerticalLines(false);
        this.tableObjectifs.getColumn("Image").setCellRenderer(new ImageRenderer(this.md));

        this.tableObjectifs.setRowHeight(50);

        this.spTabObjectifs.setViewportView(this.tableObjectifs);
        this.spTabObjectifs.setPreferredSize(new Dimension(330, 550));

        this.panelObjectifs.add(this.spTabObjectifs);

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