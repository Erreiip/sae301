package client.src.vue.panels;

import client.src.Controleur;
import client.src.metier.common.Objectif;
import client.src.metier.common.Wagon;
import client.src.vue.common.ImageRenderer;
import client.src.vue.common.ModelTableObjectifs;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class PanelCarte extends JPanel implements ListSelectionListener
{
    private Controleur ctrl;

    private ArrayList<Wagon>  alWagons;
    private Map<Color, ArrayList<Wagon>> hsmWagonCouleur;

    private JList<Wagon> lstWagon;

    private JTabbedPane tpCartes;
    private JPanel panelWagons;
    private JPanel panelObjectifs;
    
    private JTable tableObjectifs;
    private ModelTableObjectifs md;

    public PanelCarte(Controleur ctrl)
    {
        this.ctrl            = ctrl;
        this.hsmWagonCouleur = new HashMap<Color, ArrayList<Wagon>>();
        this.alWagons        = this.ctrl.getJoueur().getMain();

        this.tpCartes        = new JTabbedPane();
        this.panelWagons     = new JPanel();
        this.panelObjectifs  = new JPanel();

        Border blackline = BorderFactory.createLineBorder(Color.BLACK);
        this.tpCartes.setBorder(blackline);

        majListWagon();


        JScrollPane spTabObjectifs = new JScrollPane();
        JScrollPane spTabWagons    = new JScrollPane();

        this.tableObjectifs = new JTable();
        this.md = new ModelTableObjectifs(this.ctrl);
        this.tableObjectifs.setModel(this.md);
        this.tableObjectifs.setShowVerticalLines(false);
        this.tableObjectifs.getColumn("Image").setCellRenderer(new ImageRenderer(this.md));

        this.tableObjectifs.setRowHeight(50);

        spTabObjectifs.add(this.tableObjectifs);
        spTabObjectifs.setViewportView(this.tableObjectifs);
        spTabObjectifs.setPreferredSize(new Dimension(330, 400));

        spTabWagons.add(this.lstWagon);
        spTabWagons.setViewportView(this.lstWagon);
        spTabWagons.setPreferredSize(new Dimension(330, 400));

        this.tpCartes.addTab("Wagons", this.panelWagons);
        this.tpCartes.addTab("Objectifs", this.panelObjectifs);

        this.panelObjectifs.add(spTabObjectifs);   
        this.panelWagons.add(spTabWagons);

        
        this.add(this.tpCartes);

        ListSelectionModel selModel = this.tableObjectifs.getSelectionModel();
        selModel.addListSelectionListener(this);        
    }

    public void majListWagon()
    {
        for ( Wagon w : this.alWagons)
        {
            Color c = new Color(w.getCouleur());

            if ( !this.hsmWagonCouleur.containsKey(c) )
            {
                this.hsmWagonCouleur.put(c, new ArrayList<Wagon>());
            }

            this.hsmWagonCouleur.get(c).add(w);
        }

        Wagon[] strWagons = new Wagon[this.hsmWagonCouleur.keySet().size()];
        int cpt = 0;
        for (Color c : this.hsmWagonCouleur.keySet())
        {
            strWagons[cpt++] = this.hsmWagonCouleur.get(c).get(0); 
        }

        this.lstWagon = new JList<Wagon>(strWagons);
        this.lstWagon.setCellRenderer(new WagonListRenderer());
    }

    @Override
    public void valueChanged(ListSelectionEvent e) 
    {
        if ( this.tableObjectifs.getSelectedRow() != -1)
        {
            ModelTableObjectifs md = (ModelTableObjectifs) this.tableObjectifs.getModel();
            Objectif o = md.getObjectif(this.tableObjectifs.getSelectedRow());
            this.ctrl.colorier(o.getV1(), o.getV2());
        } else
        {
            this.ctrl.colorier();
        }
    }

    public class WagonListRenderer extends DefaultListCellRenderer
    {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus)
        {  
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            Wagon w = (Wagon) value;
            Color c = new Color(w.getCouleur());
            int nbWagon = PanelCarte.this.hsmWagonCouleur.get(c).size();

            ImageIcon icon   = new ImageIcon(w.getFileRecto());
            Image     image  = icon.getImage();
            Image     newImg = image.getScaledInstance(80, 50,  java.awt.Image.SCALE_SMOOTH);
            icon             = new ImageIcon(newImg);
            
            label.setIcon(icon);
            label.setPreferredSize(new Dimension(200, 60));
            label.setText(nbWagon + "");
            label.setHorizontalTextPosition(JLabel.RIGHT);
            return label;
        }
    }

    

    
}
