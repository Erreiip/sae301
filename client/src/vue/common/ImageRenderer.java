package client.src.vue.common;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;

import client.src.metier.common.Objectif;

public class ImageRenderer extends JLabel implements TableCellRenderer
{
    private ModelTableObjectifs model;

    public ImageRenderer(ModelTableObjectifs model)
    {
        this.model = model;
        setOpaque(true);
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
        boolean isSelected, boolean hasFocus, int row, int column)
    {
        Objectif o = model.getObjectif(row);
        ImageIcon icon = new ImageIcon(o.getFileRecto());
        Image image = icon.getImage();
        Image newImg = image.getScaledInstance(75, 45, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImg);

        setIcon(icon);

        if ( o.isPrit() ) this.setBackground(Color.GREEN);
        
        return this;
    }
}
