package client.src;

import client.src.metier.Metier;
import client.src.vue.FrameAcceuil;

public class Controleur 
{
    private FrameAcceuil ihm;
    private Metier          metier;

    public Controleur()
    {
        this.metier = new Metier(this);
        this.ihm    = new FrameAcceuil(this);
    }
}
