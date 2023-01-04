package src.vue;

import src.Controleur;
import src.vue.panels.*;

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
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        this.panelJoueurs     = new PanelJoueurs(ctrl);
        this.panelPioche      = new PanelPioche(ctrl);
        this.panelMap         = new PanelMap(ctrl);
        this.panelCartes      = new PanelCartes(ctrl);

        this.panelJoueurs.setBackground(Color.RED);
        this.panelPioche.setBackground(Color.BLUE);
        this.panelMap.setBackground(Color.GREEN);
        this.panelCartes.setBackground(Color.YELLOW);

        this.add(this.panelJoueurs, BorderLayout.NORTH);
        this.add(this.panelPioche, BorderLayout.WEST);
        this.add(this.panelMap, BorderLayout.CENTER);
        this.add(this.panelCartes, BorderLayout.EAST);

        this.setVisible(true);
    }
}
