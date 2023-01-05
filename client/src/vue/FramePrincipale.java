package client.src.vue;

import client.src.Controleur;
import client.src.vue.panels.*;

import javax.swing.*;
import java.awt.*;

public class FramePrincipale extends JFrame
{
    private Controleur ctrl;

    private PanelJoueurs panelJoueurs;
    private PanelPioche  panelPioche;
    private PanelMap     panelMap;
    private PanelCartes  panelCartes;

    public FramePrincipale(Controleur ctrl)
    {
        this.ctrl = ctrl;

        this.setTitle("Les Aventuriers du Rail");
        this.setSize(1400, 800);
        this.setLayout(new BorderLayout());

        this.panelJoueurs     = new PanelJoueurs(ctrl);
        this.panelPioche      = new PanelPioche(ctrl);
        this.panelMap         = new PanelMap(ctrl);
        this.panelCartes      = new PanelCartes(ctrl);

        this.add(this.panelJoueurs, BorderLayout.NORTH);
        this.add(this.panelPioche , BorderLayout.WEST);
        this.add(this.panelMap    , BorderLayout.CENTER);
        this.add(this.panelCartes , BorderLayout.EAST);

        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
