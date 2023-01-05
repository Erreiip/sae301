package client.src.metier.common;

import java.util.ArrayList;

public class Joueur
{
    private Integer             couleur;

    private int                 nbMarqueurs;
    private int                 nbPv;
    private ArrayList<Wagon>    alWagons;
    private ArrayList<Objectif> alObjectifs;

    public Joueur(){}

    public Joueur(Integer couleur, int nbMarqueurs)
    {
        this.couleur = couleur;
        this.nbMarqueurs = nbMarqueurs;
        this.nbPv = 0;

        this.alObjectifs = new ArrayList<Objectif>();
        this.alWagons    = new ArrayList<Wagon>();
    }


    public ArrayList<Wagon>    getMain()      { return this.alWagons; }
    public ArrayList<Objectif> getObjectifs() { return this.alObjectifs; }

    public Integer getCouleur        () {return couleur;}
    public int     getNbMarqueurs    () {return nbMarqueurs;}

    public void setNbMarqueurs(int nbMarqueurs) { this.nbMarqueurs = nbMarqueurs; }
    public void setCouleur    (Integer couleur) { this.couleur = couleur;         }


    public void ajouterWagon   (Wagon wagon)  { this.alWagons.add(wagon); }
    public void ajouterObjectif(Objectif obj) { this.alObjectifs.add(obj);}
    public void ajouterPV      (Integer pv )  { this.nbPv += pv;  } 
    
}