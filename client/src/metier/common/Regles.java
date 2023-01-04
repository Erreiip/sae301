package client.src.metier.common;

import java.util.ArrayList;

public class Regles 
{
    private Integer[]       tabReglesJoueurs;
    private Integer         nbWagonsFinParties;

    private                 ArrayList<Objectif> alObjectif; 
    private                 ArrayList<Wagon> alWagons;

    public Regles(int nbWagonsFinParties, Integer[] tabReglesJoueur)
    {
        this.nbWagonsFinParties = nbWagonsFinParties;
        this.tabReglesJoueurs   = tabReglesJoueur;

        ArrayList<Objectif> alObjectif = null;
        ArrayList<Wagon> alWagon       = null;
    }

    public void ajouterAlObjectifs(ArrayList<Objectif> alObjectif) { this.alObjectif = alObjectif; }
    public void ajouterAlWagons   (ArrayList<Wagon> alWagon)       { this.alWagons    = alWagon; }

    public ArrayList<Wagon> getAlWagons      () { return this.alWagons;  }
    public ArrayList<Objectif> getAlObjectifs() { return this.alObjectif;}


    public int getNbJoueursMini      () { return this.tabReglesJoueurs[0]; }
    public int getNbJoueursMaxi      () { return this.tabReglesJoueurs[1]; }
    public int getNbJoueursVoieDouble() { return this.tabReglesJoueurs[2]; }
    public int getNbWagonsFinParties () { return this.nbWagonsFinParties;  }
}
