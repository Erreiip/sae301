package client.src.vue.panels;

import client.src.Controleur;

import javax.swing.*;
import java.awt.*;

public class PanelCartes extends JPanel
{
    private Controleur ctrl;

    public PanelCartes(Controleur ctrl)
    {
        this.ctrl = ctrl;
        this.setBackground(Color.RED);
    }
}
