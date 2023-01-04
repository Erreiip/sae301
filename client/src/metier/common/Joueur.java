package client.src.metier;

import java.util.ArrayList;

public class Joueur
{
    private String              nom;
    private int                 nbMarqueurs;
    private ArrayList<Wagon>    alWagons;
    private ArrayList<Objectif> alObjectifs;
    private ArrayList<Ville>    alVilles;

    public Joueur(String nom, int nbMarqueurs)
    {
        this.nom = nom;
        this.nbMarqueurs = nbMarqueurs;

        this.alObjectifs = new ArrayList<Objectif>();
        this.alWagons = new ArrayList<Wagon>();
        this.alVilles = new ArrayList<Ville>();
    }

    public String getNom        () {return nom;}
    public int    getNbMarqueurs() {return nbMarqueurs;}

    public void setNbMarqueurs(int nbMarqueurs) { this.nbMarqueurs = nbMarqueurs; }

    public void ajouterWagon   (Wagon wagon)  { this.alWagons.add(wagon); }
    public void ajouterObjectif(Objectif obj) { this.alObjectifs.add(obj);}
    public void ajouterVille   (Ville ville)  {this.alVilles.add(ville);  }
    
}