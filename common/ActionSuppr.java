package common;

import java.util.ArrayList;

import client.src.metier.common.Objectif;
import client.src.metier.common.Wagon;

public class ActionSuppr 
{
    private ArrayList<Objectif> alObjectifsSuppr;
    private ArrayList<Wagon> alWagonsSuppr;

    public ActionSuppr(){}

    public ActionSuppr(ArrayList<Wagon> alWagonsDef, ArrayList<Objectif> alObjectifsDef)
    {
        this.alWagonsSuppr   = alWagonsDef;
        this.alObjectifsSuppr = alObjectifsDef;
    }
    
    public ActionSuppr(String f)
    {
        this(new ArrayList<Wagon>(), new ArrayList<Objectif>());
    }

    public ArrayList<Wagon>      getAlWAgonsSuppr   () { return this.alWagonsSuppr;    }    
    public ArrayList<Objectif>   getAlObjectifsSuppr() { return this.alObjectifsSuppr; }
    
    public void ajouterWagon(Wagon w) { this.alWagonsSuppr.add(w); }
    public void ajouterObjec(Objectif w) { this.alObjectifsSuppr.add(w); }
    
}
