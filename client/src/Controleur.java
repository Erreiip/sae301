package client.src;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JFrame;

import client.src.metier.*;
import client.src.metier.common.Objectif;
import client.src.metier.common.Route;
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

    public void creerClient()
    {
        this.metier.creerClient();
    }

    public void supprimerClient()
    {
        this.metier.supprimerClient();
    }

    public Wagon[] getPiocheVisible()
    {
        return this.metier.getPiocheVisible();
    }

    public ArrayList<Wagon> getMainJoueur()
    {
        return this.metier.getMainJoueur();
    }

    public ArrayList<Objectif> getObjectifsJoueur()
    {
        return this.metier.getObjectifsJoueur();
    }

    public void supprimerObj(ArrayList<Objectif> alObj) 
    {
        this.metier.supprimerObj(alObj);
    }

    public void supprimerWagons(ArrayList<Wagon> alWagons) 
    {
        this.metier.supprimerWagons(alWagons);
    }

    public void routePrise(ArrayList<Route> alRoute) 
    {
        this.metier.routePrise(alRoute);
    }

}
