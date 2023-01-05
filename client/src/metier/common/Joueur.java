package client.src.metier.common;

import java.util.ArrayList;

public class Joueur
{
    private String              nom;
    private int                 nbMarqueurs;
    private int                 nbPv;
    private ArrayList<Wagon>    alWagons;
    private ArrayList<Objectif> alObjectifs;
    private ArrayList<Ville>    alVilles;

    public Joueur(){}

    public Joueur(String nom, int nbMarqueurs)
    {
        this.nom = nom;
        this.nbMarqueurs = nbMarqueurs;
        this.nbPv = 0;

        this.alObjectifs = new ArrayList<Objectif>();
        this.alWagons    = new ArrayList<Wagon>();
        this.alVilles    = new ArrayList<Ville>();
    }


    public ArrayList<Wagon>    getMain()      { return this.alWagons; }
    public ArrayList<Objectif> getObjectifs() { return this.alObjectifs; }

    public String getNom        () {return nom;}
    public int    getNbMarqueurs() {return nbMarqueurs;}

    public void setNbMarqueurs(int nbMarqueurs) { this.nbMarqueurs = nbMarqueurs; }

    public void ajouterWagon   (Wagon wagon)  { this.alWagons.add(wagon); }
    public void ajouterObjectif(Objectif obj) { this.alObjectifs.add(obj);}
    public void ajouterPV      (Integer pv )  { this.nbPv += pv;  } 
    
}