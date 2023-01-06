package client.src;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JFrame;
import java.awt.image.BufferedImage;


import client.src.metier.*;
import client.src.metier.common.Joueur;
import client.src.metier.common.Objectif;
import client.src.metier.common.Route;
import client.src.metier.common.Ville;
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

    public BufferedImage getFond    () { return this.metier.getFond(); }
    //public File getImgJoker() { return this.metier.getImgJoker(); }

    public Wagon[] getPiocheVisible                       () { return this.metier.getPiocheVisible();  }
    public void supprimerWagonsToDef(ArrayList<Wagon> alObj) { this.metier.supprimerWagonsToDef(alObj);}
    public void supprimerWagons     (ArrayList<Wagon> alObj) { this.metier.supprimerWagons     (alObj);}

    public Objectif[] getPiocheVisibleObj                 () { return this.metier.getPiocheVisibleObj();   }
    public void supprimerObjToDef(ArrayList<Objectif> alObj) { this.metier.supprimerObjToDef(alObj);       }
    public void supprimerObj     (ArrayList<Objectif> alObj) { this.metier.supprimerObj(alObj);            }

    public ArrayList<Ville> getAlVilles() { return this.metier.getAlVilles(); }
    public ArrayList<Route> getAlRoutes() { return this.metier.getAlRoutes(); }

    public Joueur getJoueurActif() { return this.metier.getJoueurActif(); }

    public Joueur getJoueur() { return this.metier.getJoueur(); }

    public void setJoueurActif(Joueur j) { this.metier.setJoueurActif(j); }

    public void routePrise(ArrayList<Route> alRoute) { this.metier.routePrise(alRoute);    }
    public boolean ajouterRoute(Route r)             { return this.metier.ajouterRoute(r); }

    public boolean actionPossible() { return this.metier.actionPossible(); }


    public void colorier( Ville v1, Ville v2) { ((FramePrincipale)this.ihm).colorier(v1,v2); }
    public void colorier()                    { ((FramePrincipale)this.ihm).colorier(); }

    public Wagon getWagonVerso        ()        { return this.metier.getWagonVerso(); }
    public boolean ajouterWagonAJoueur(Wagon w) { return this.metier.piocherWagon(w); }
    public boolean secondWagon        ()        { return this.metier.secondWagon  (); }

    public void genererInteractionObj   ()        { ((FramePrincipale)this.ihm).genererInteractionObj();    }
    public void genererInteractionWagon(Wagon w)  { ((FramePrincipale)this.ihm).genererInteractionWagon(w); }
    public void majPioche               ()        { ((FramePrincipale)this.ihm).majPioche();                }

    public void    setActionEnCours(boolean action) { this.metier.setActionEnCours(action);; }
    public boolean getActionEnCours()               { return this.metier.getActionEnCours(); }

    public boolean verifierObjectif(Objectif o)
    {
        if ( this.metier.verifierObjectif(o) ) 
        {
            //((FramePrincipale) this.ihm).majIHM();
            return true;
        }

        return false;
    }

    private void tourTermine() { ((FramePrincipale)this.ihm).maj(); }

}
