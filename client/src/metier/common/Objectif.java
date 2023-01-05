package client.src.metier.common;

import java.io.File;

public class Objectif
{
    private Ville v1;
    private Ville v2;
    private int nbPoints;

    private static String versoObjectif = null;
    private static String rectoObjectif = null;

    public Objectif(){}

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


    public static void setFileVerso ( String f)
    {
        Objectif.versoObjectif = f;
    }

    public static void setFileRecto ( String f)
    {
        Objectif.rectoObjectif = f;
    }

    public String getFileRecto ()
    {
        return Objectif.rectoObjectif;
    }

    public static String getFileVerso()
    {
        return Objectif.versoObjectif;
    }
}