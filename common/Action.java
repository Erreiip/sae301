package common;

import java.util.ArrayList;

import client.src.metier.common.*;

public class Action 
{
    private ArrayList<Wagon>    alWagons;
    private ArrayList<Objectif> alObjectifs;
    private ArrayList<Route>    alRoutes;

    public Action(ArrayList<Wagon> alWagons, ArrayList<Objectif> alObjectifs, ArrayList<Route> alRoutes)
    {
        this.alWagons    = alWagons;
        this.alObjectifs = alObjectifs;
        this.alRoutes    = alRoutes;
    }

    public ArrayList<Wagon>     getAlWAgons    () { return this.alWagons;    }
    public ArrayList<Objectif>   getAlObjectifs() { return this.alObjectifs; }
    public ArrayList<Route>     getAlRoutes    () { return this.alRoutes;    }
}
