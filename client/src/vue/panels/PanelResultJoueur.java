package client.src.vue.panels;

import javax.swing.JPanel;

import client.src.Controleur;
import client.src.metier.common.Joueur;

public class PanelResultJoueur extends JPanel
{
    private Controleur ctrl;
    
    private Joueur joueur;

    public PanelResultJoueur(Controleur ctrl, Joueur j, int rank)
    {
        this.ctrl = ctrl;
        this.joueur = j;
    }
}
