package src.vue.panels;

import src.Controleur;

import javax.swing.*;
import java.awt.*;

public class PanelPioche extends JPanel 
{
    private Controleur ctrl;

    public PanelPioche(Controleur ctrl)
    {
        this.ctrl = ctrl;
        this.setBackground(Color.RED);
    }    
}
