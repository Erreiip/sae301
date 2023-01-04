package client.src;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JFrame;

import client.src.metier.*;
import client.src.metier.common.Objectif;
import client.src.metier.common.Wagon;
import client.src.vue.*;

public class Controleur 
{
    private JFrame          ihm;
    private Metier          metier;

    public Controleur()
    {
        this.metier = new Metier(this);
        this.ihm    = new FrameAcceuil(this);
    }

    public void setIhm(JFrame frame)
    {
        this.ihm = frame;
    }

    public void lireXml(File f)
    {
        this.metier.lectureXML(f);
    }

    public Wagon[] getPiocheVisible()
    {
        return this.metier.getPiocheVisible();
    }

    public ArrayList<Wagon> getMainJoueur()
    {
        return this.metier.getMainJoueur();
    }

    public void setAlObjectif(ArrayList<Objectif> alObj) 
    {
        this.metier.setAlObjectif(alObj);
    }

    public void setAlWagons(ArrayList<Wagon> alWagons) 
    {
        this.metier.setAlWagons(alWagons);
    }

}
