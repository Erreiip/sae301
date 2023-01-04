package client.src.vue.panels;

import client.src.Controleur;

import javax.swing.*;
import java.awt.*;

public class PanelJoueurs extends JPanel
{
    private Controleur ctrl;

    public PanelJoueurs(Controleur ctrl)
    {
        this.ctrl = ctrl;
        this.setBackground(Color.RED);
    }
}
