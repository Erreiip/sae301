package client.src.vue.panels;

import client.src.Controleur;

import javax.swing.*;
import java.awt.*;

public class PanelMap extends JPanel
{
    private Controleur ctrl;

    public PanelMap(Controleur ctrl)
    {
        this.ctrl = ctrl;
        this.setBackground(Color.RED);
    }    
}
