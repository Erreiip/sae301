package client.src;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.image.BufferedImage;
import java.awt.Color;



import client.src.metier.*;
import client.src.metier.common.Joueur;
import client.src.metier.common.Objectif;
import client.src.metier.common.Regles;
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
        this.ihm.dispose();
        this.ihm = frame;
    }

    public void lireXml(File f, boolean creeServeurClient)
    {
        this.metier.lectureXML(f, creeServeurClient);
    }

    public BufferedImage getFond    () { return this.metier.getFond(); }
    //public File getImgJoker() { return this.metier.getImgJoker(); }

    public Wagon[] getPiocheVisible                       () { return this.metier.getPiocheVisible();  }

    public Objectif[] getPiocheVisibleObj                 () { return this.metier.getPiocheVisibleObj();   }
    public void supprimerObjToDef(ArrayList<Objectif> alObj) { this.metier.supprimerObjToDef(alObj);       }
    public void supprimerObj     (ArrayList<Objectif> alObj) { this.metier.supprimerObj(alObj);            }

    public ArrayList<Ville> getAlVilles() { return this.metier.getAlVilles(); }
    public ArrayList<Route> getAlRoutes() { return this.metier.getAlRoutes(); }

    public Joueur getJoueurActif() { return this.metier.getJoueurActif(); }

    public Joueur getJoueur() { return this.metier.getJoueur(); }

    public void setJoueurActif(Joueur j) { this.metier.setJoueurActif(j); }
    public void setJoueur    (Joueur j) { this.metier.setJoueur(j); }

    public  HashMap <Color, Integer> getJetonsCouleurJoueur() { return this.metier.getJetonsCouleurJoueur(); }
    

    public boolean ajouterRoute    (Route r, int nb)         { return this.metier.ajouterRoute(r, nb);    }
    public boolean ajouterRoute    (Route r,Color c,int nb)  { return this.metier.ajouterRoute(r, c, nb); }

    public boolean peutPrendreRoute(Route r, int nb)  { return this.metier.peutPrendreRoute(r, nb);}
    public boolean peutDessinerDouble()  { return this.metier.peutDessinerDouble();}


    public boolean actionPossible() { return this.metier.actionPossible(); }


    public void colorier( Ville v1, Ville v2) { ((FramePrincipale)this.ihm).colorier(v1,v2); }
    public void colorier()                    { ((FramePrincipale)this.ihm).colorier(); }

    public Wagon getWagonVerso        ()        { return this.metier.getWagonVerso(); }
    public boolean ajouterWagonAJoueur(Wagon w) { return this.metier.piocherWagon(w); }
    public void    ajouterWagon       (Wagon w) { this.metier.ajouterWagon(w);        }
    public boolean secondWagon        ()        { return this.metier.secondWagon  (); }

    public void genererInteractionObj   ()        { ((FramePrincipale)this.ihm).genererInteractionObj();    }
    public void genererInteractionCartes(Route r, int type) { ((FramePrincipale)this.ihm).genererInteractionCartes(r, type); }
    public void genererInteractionWagon(Wagon w)  { ((FramePrincipale)this.ihm).genererInteractionWagon(w); }
    public void majPioche               ()        { ((FramePrincipale)this.ihm).majPioche();                }

    public void    setActionEnCours(boolean action) {  this.metier.setActionEnCours(action);  }
    public boolean getActionEnCours()               { return this.metier.getActionEnCours(); }

    public int     getTour() { return this.metier.getTour(); }

    public void modeDebut() { this.metier.modeDebug(); }

    public boolean verifierObjectif(Objectif o, Joueur j)
    {
        return this.metier.verifierObjectif(o, j);
    }
    
    public void piocheObjectif( ArrayList<Objectif> ajoutJoueur, ArrayList<Objectif> ajoutDefausse)
    {
        this.metier.piocheObjectif(ajoutJoueur, ajoutDefausse);
    }

    public void setNbJoueurs(Integer i)
    {
        this.metier.setNbJoueurs(i);
    }

    public int getNbJoueur()
    {
        return this.metier.getNbJoueur();
    }

    public ArrayList<Joueur> getAlJoueurs()
    {
        return this.metier.getAlJoueurs();
    }

    public List<Joueur> getJoueursFin()
    {
        return this.metier.getJoueursFin();
    }

    public ArrayList<Joueur> getJoueursCheminPlusLong() { return this.metier.getJoueursCheminPlusLong(); }


    public Regles getRegles()
    {
        return this.metier.getRegles();
    }

    public void suppBtnPiocheObj() 
    {
        ((FramePrincipale)this.ihm).suppBtnPiocheObj();
    }

    public Wagon getWagonCouleur ( Color c) { return this.metier.getWagonCouleur(c); }


    public void afficher(String f)
    {
        JOptionPane.showMessageDialog(this.ihm, 
         f,
         " Information ",
         JOptionPane.WARNING_MESSAGE);
    }

    public void tourTermine() 
    { 
        ((FramePrincipale)this.ihm).maj();
    }

}
