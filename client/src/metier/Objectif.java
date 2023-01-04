package client.src.metier;

import java.io.File;

public class Objectif
{
    private Ville v1;
    private Ville v2;
    private int nbPoints;

    private static File versoObjectif = null;
    private static File rectoObjectif = null;


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


    public static void setFileVerso ( File f)
    {
        Objectif.versoObjectif = f;
    }

    public static void setFileRecto ( File f)
    {
        Objectif.versoObjectif = f;
    }

    public static File getFile ()
    {
        return Objectif.versoObjectif;
    }
}