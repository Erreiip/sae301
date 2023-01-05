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
    private PanelCartesInteraction  panelCartesInteraction;

    public FramePrincipale(Controleur ctrl)
    {
        this.ctrl = ctrl;

        this.setTitle("Les Aventuriers du Rail");
        this.setSize(1400, 800);
        this.setLayout(new BorderLayout());

        this.panelJoueurs     = new PanelJoueurs(ctrl);
        this.panelPioche      = new PanelPioche(ctrl);
        this.panelMap         = new PanelMap(ctrl);
        this.panelCartesInteraction      = new PanelCartesInteraction(ctrl);

        JScrollPane jspMap = new JScrollPane(this.panelMap);

        this.add(this.panelJoueurs, BorderLayout.NORTH);
        this.add(this.panelPioche , BorderLayout.WEST);
        this.add(jspMap    , BorderLayout.CENTER);
        this.add(this.panelCartesInteraction , BorderLayout.EAST);

        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
