package client.src.metier.common;

import java.util.ArrayList;

public class Joueur
{
    private Integer             couleur;
    
    private int                 id;

    private int                 nbMarqueurs;
    private int                 nbPv;
    private ArrayList<Wagon>    alWagons;
    private ArrayList<Objectif> alObjectifs;

    public Joueur(){}

    public Joueur(Integer couleur, int nbMarqueurs, int id)
    {
        this.couleur = couleur;
        this.nbMarqueurs = nbMarqueurs;
        this.nbPv = 0;

        this.id = id;

        this.alObjectifs = new ArrayList<Objectif>();
        this.alWagons    = new ArrayList<Wagon>();
    }

    public boolean enleverMarqueurs(int nbMarqueurs) 
    { 
        if ( (this.nbMarqueurs - nbMarqueurs) < 0) return false;
        
        this.nbMarqueurs -= nbMarqueurs;  
        return true;
    }



    public ArrayList<Wagon>    getMain()      { return this.alWagons; }
    public ArrayList<Objectif> getObjectifs() { return this.alObjectifs; }

    public Integer getCouleur        () {return couleur;}
    public int     getNbMarqueurs    () {return nbMarqueurs;}
    public int     getNbPv           () {return nbPv;}

    public void setNbMarqueurs(int nbMarqueurs) { this.nbMarqueurs = nbMarqueurs; }
    public void setCouleur    (Integer couleur) { this.couleur = couleur;         }


    public void ajouterWagon   (Wagon wagon)  { this.alWagons.add(wagon); }
    public void ajouterObjectif(Objectif obj) { this.alObjectifs.add(obj);}
    public void ajouterPV      (Integer pv )  { this.nbPv += pv;  } 
    
    public int getId() { return this.id; }
    
}