package client.src.metier.common;

public class Objectif
{
    private Ville v1;
    private Ville v2;
    private int nbPoints;

    private boolean estPrit;

    private static String versoObjectif = null;
    private static String rectoObjectifS = null;

    
    private String rectoObjectif;

    public Objectif(){}

    public Objectif(Ville v1, Ville v2, int nbPoints)
    {
        this.v1 = v1;
        this.v2 = v2;
        this.nbPoints = nbPoints;

        this.estPrit = false;

        this.rectoObjectif = null;        
    }

    public boolean contains( Ville v)
    {
        return v.equals(this.v1) || v.equals(this.v2);
    }

    public Ville getV1    ()    {return v1;      }
    public Ville getV2    ()    {return v2;      }
    public int getNbPoints()    {return nbPoints;}

    public boolean isPrit ()               { return estPrit;      }
    public void    setPrit(boolean prit)   { this.estPrit = prit; }

    public void   setFileRecto ( String f) { this.rectoObjectif = f;    }
    public String getFileRecto ()          { return this.rectoObjectif; }

    public static void   setFileRectoS  ( String f ) { Objectif.rectoObjectifS = f; }
    public static String getFileRectoS  ()           { return Objectif.rectoObjectifS; }


    public static void   setFileVerso ( String f ) { Objectif.versoObjectif = f; }
    public static String getFileVerso ()          { return Objectif.versoObjectif; }
}