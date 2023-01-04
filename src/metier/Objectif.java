package src.metier;

public class Objectif
{
    private Ville v1;
    private Ville v2;
    private int nbPoints;

    public Objectif(Ville v1, Ville v2, int nbPoints)
    {
        this.v1 = v1;
        this.v2 = v2;
        this.nbPoints = nbPoints;
    }

    public boolean contains( Ville v)
    {
        return v.equals(this.v1) || v.equals(this.v2);
    }

    public Ville getV1()        {return v1;      }
    public Ville getV2()        {return v2;      }
    public int getNbPoints()    {return nbPoints;}
}