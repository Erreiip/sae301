package common;

import java.util.ArrayList;

import client.src.metier.common.Route;

public class ActionRoute 
{
    private Route rt;

    public ActionRoute(){}

    public ActionRoute(Route rt)
    {
        this.rt = rt;
    }
    
    public ActionRoute(int f)
    {
        this(null);
    }

    public Route getRoute()
    {
        return this.rt;
    }

    public void setRoute(Route r)
    {
        this.rt = r;
    }
    
    
}
