package client.src;

import java.io.File;

import client.src.metier.Metier;
import client.src.vue.FramePrincipale;

public class Controleur 
{
    private FramePrincipale ihm;
    private Metier          metier;

    public Controleur()
    {
        this.metier = new Metier(this);
        this.ihm    = new FramePrincipale(this);
    }

    public void lireXml(File f)
    {
        this.metier.lectureXML(f);
    }
}
