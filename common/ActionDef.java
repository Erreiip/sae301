package common;

import java.util.ArrayList;

import client.src.metier.common.Objectif;
import client.src.metier.common.Wagon;

public class ActionDef 
{
    private ArrayList<Objectif> alObjectifsDef;
    private ArrayList<Wagon> alWagonsDef;
    
    public ActionDef(){}

    public ActionDef(ArrayList<Wagon> alWagonsDef, ArrayList<Objectif> alObjectifsDef)
    {
        this.alWagonsDef   = alWagonsDef;
        this.alObjectifsDef = alObjectifsDef;
        this.alWagonsDef.add(new Wagon(111, "shesh"));
    }
    
    public ActionDef(String f)
    {
        this(new ArrayList<Wagon>(), new ArrayList<Objectif>());
    }

    public ArrayList<Wagon>      getAlWAgonsDef     () { return this.alWagonsDef;      }
    public ArrayList<Objectif>   getAlObjectifsDef  () { return this.alObjectifsDef; }
    
    public void ajouterWagon(Wagon w) { this.alWagonsDef.add(w); }
    public void ajouterObjec(Objectif w) { this.alObjectifsDef.add(w); }
    
}
