package client.src;

import java.io.File;

import client.src.metier.Metier;
import client.src.metier.Wagon;
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

    public void lireXml(File f)
    {
        this.metier.lectureXML(f);
    }

    public Wagon[] getPiocheVisible()
    {
        return this.metier.getPiocheVisible();
    }
}
