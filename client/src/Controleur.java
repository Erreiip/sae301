package client.src;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JFrame;

import client.src.metier.*;
import client.src.metier.common.Joueur;
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

    public File getFond    () { return this.metier.getFond(); }
    //public File getImgJoker() { return this.metier.getImgJoker(); }

    public Wagon[] getPiocheVisible                       () { return this.metier.getPiocheVisible();  }
    public void supprimerWagonsToDef(ArrayList<Wagon> alObj) { this.metier.supprimerWagonsToDef(alObj);}
    public void supprimerWagons     (ArrayList<Wagon> alObj) { this.metier.supprimerWagons     (alObj);}

    public Objectif[] getPiocheVisibleObj                 () { return this.metier.getPiocheVisibleObj();   }
    public void supprimerObjToDef(ArrayList<Objectif> alObj) { this.metier.supprimerObjToDef(alObj);       }
    public void supprimerObj     (ArrayList<Objectif> alObj) { this.metier.supprimerObj(alObj);            }


    public Joueur getJoueurActif()
    {
        return this.metier.getJoueurActif();
    }

    public Joueur getJoueur()
    {
        return this.metier.getJoueur();
    }

    public void setJoueurActif(Joueur j)
    {
        this.metier.setJoueurActif(j);
    }

    public void routePrise(ArrayList<Route> alRoute) 
    {
        this.metier.routePrise(alRoute);
    }

}
