package common;

import java.util.ArrayList;

import client.src.metier.common.*;

public class Action 
{
    private ArrayList<Wagon>    alWagonsSuppr;
    private ArrayList<Objectif> alObjectifsSuppr;
    private ArrayList<Objectif> alObjectifsDef;

    private ArrayList<Route>    alRoutes;

    public Action(ArrayList<Wagon> alWagons, ArrayList<Objectif> alObjectifsSuppr, ArrayList<Objectif> alObjectifsDef, ArrayList<Route> alRoutes)
    {
        this.alWagonsSuppr   = alWagons;
        this.alObjectifsSuppr = alObjectifsSuppr;
        this.alObjectifsDef = alObjectifsDef;

        this.alRoutes    = alRoutes;
    }

    public ArrayList<Wagon>      getAlWAgonsSuppr   () { return this.alWagonsSuppr;    }
    public ArrayList<Objectif>   getAlObjectifsSuppr() { return this.alObjectifsSuppr; }
    public ArrayList<Objectif>   getAlObjectifsDef  () { return this.alObjectifsSuppr; }

    public ArrayList<Route>     getAlRoutes    () { return this.alRoutes;    }
}
