package client.src.metier;

public class Regles 
{
    private Integer[]            tabReglesJoueurs;
    private Integer              nbWagonsFinParties;

    public Regles(int nbWagonsFinParties, Integer[] tabReglesJoueur)
    {
        this.nbWagonsFinParties = nbWagonsFinParties;
        this.tabReglesJoueurs   = tabReglesJoueur;
    }

    public int getNbJoueursMini      () { return this.tabReglesJoueurs[0]; }
    public int getNbJoueursMaxi      () { return this.tabReglesJoueurs[1]; }
    public int getNbJoueursVoieDouble() { return this.tabReglesJoueurs[2]; }
    public int getNbWagonsFinParties () { return this.nbWagonsFinParties;  }
}
