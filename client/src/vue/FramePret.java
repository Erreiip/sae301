package client.src.vue;

import java.awt.*;
import java.util.concurrent.Flow;

import javax.swing.*;

import client.src.Controleur;
import client.src.vue.panels.PanelPret;

public class FramePret extends JFrame
{
    private Controleur ctrl;

    private JPanel panel;

    public FramePret(Controleur ctrl)
    {
        this.ctrl = ctrl;
        this.setLayout(new BorderLayout());
        this.setSize(new Dimension(800, 500));

        this.panel = new PanelPret(this.ctrl);

        this.add(this.panel, BorderLayout.CENTER);

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
