package src;

import src.metier.Metier;
import src.vue.FramePrincipale;

public class Controleur 
{
    private FramePrincipale ihm;
    private Metier          metier;

    public Controleur()
    {
        this.metier = new Metier(this);
        this.ihm    = new FramePrincipale(this);
    }
}
