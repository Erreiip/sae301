package client.src.vue.common;

import client.src.Controleur;
import client.src.metier.common.*;

import javax.swing.table.AbstractTableModel;

import java.awt.Color;
import java.util.ArrayList;

public class ModelTableObjectifs extends AbstractTableModel
{
    Controleur ctrl;

    private String[] columnNames = {"Image", "Ville 1", "Ville 2", "PV"};
    private ArrayList<Objectif> alObjectifs;

    public ModelTableObjectifs(Controleur ctrl)
    {
        this.ctrl = ctrl;
        this.alObjectifs = this.ctrl.getJoueur().getObjectifs();    

        //test
        /*for (int i=0; i<5; i++)
            this.alObjectifs.add(new Objectif(new Ville(new Color(154,255,100),"Ville"+(i+1)+"",400,400,30), 
                                              new Ville(new Color(200,100, 50),"Ville2"+(i+2)+"",400,700,30), 
                                              i*2));*/
    }

    public int getColumnCount()
    {
        return columnNames.length;
    }

    public String getColumnName(int col)
    {
        return columnNames[col];
    }

    public int getRowCount()
    {
        return alObjectifs.size();
    }

    public Class<?> getColumClass(int c)
    {
        return getValueAt(0, c).getClass();
    }

    public boolean isCellEditable(int row, int col)
    {
        return false;
    }

    public Object getValueAt(int row, int col)
    {
        Objectif obj = this.alObjectifs.get(row);

        switch (col)
        {
            case 0 : return null;
            case 1 : return obj.getV1().getNom();
            case 2 : return obj.getV2().getNom();
            case 3 : return obj.getNbPoints();
            default: return null;
        }
    }

    public void addObjectif(Objectif obj)
    {
        insertObjectif(getRowCount(), obj);
    }

    public void insertObjectif(int row, Objectif obj)
    {
        this.alObjectifs.add(row, obj);
        fireTableRowsInserted(row, row);
    }

    public Objectif removeObjectif(int index)
    {
        Objectif obj = this.alObjectifs.get(index);
        this.alObjectifs.remove(index);
        fireTableRowsDeleted(index, index);
        return obj;
    }

    public Objectif getObjectif(int row)
    {
        return this.alObjectifs.get(row);
    }
}

