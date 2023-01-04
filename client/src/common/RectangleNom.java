package client.src.common;

import java.awt.geom.Rectangle2D;

import client.src.metier.Ville;

public class RectangleNom extends Rectangle2D.Double
{
    private Ville v;
    private int decalageX;
    private int decalageY;

    private boolean bAcrroche;

    private static final int MULT = 8; 

    public RectangleNom(Ville v, int decalageX, int decalageY)
    {
        super(v.getX() - decalageX, v.getCenterY() - decalageY, v.getNom().length()*RectangleNom.MULT, 20);

        this.v = v;
        this.decalageX = decalageX;
        this.decalageY = decalageY;
        this.bAcrroche = true;
    }

    public int getPosX   () 
    {   
        if ( this.bAcrroche )
            return (int) v.getX() - decalageX; 
        else
            return this.decalageX;
    }

    public int getPosY   () 
    { 
        if ( this.bAcrroche )
            return (int) v.getCenterY() - decalageY; 
        else
            return this.decalageY;
    }

    public String getNom () { return this.v.getNom(); }
    public Ville getVille() { return this.v; }

    public void setDecalageX(int decalageX) { this.bAcrroche = true; this.decalageX = decalageX; super.setFrame(v.getX() - decalageX, v.getCenterY() - decalageY, v.getNom().length()*RectangleNom.MULT, 20); }
    public void setDecalageY(int decalageY) { this.bAcrroche = true; this.decalageY = decalageY; super.setFrame(v.getX() - decalageX, v.getCenterY() - decalageY, v.getNom().length()*RectangleNom.MULT, 20); }

    public void setPosX(int x) { this.bAcrroche = false; this.decalageX = x; } 
    public void setPosY(int y) { this.bAcrroche = false; this.decalageY = y; }


    public void maj      ()
    { 
        if ( this.bAcrroche )
            super.setFrame(v.getX() - decalageX, v.getCenterY() - decalageY, v.getNom().length()*RectangleNom.MULT, 20); 
        else
            super.setFrame(decalageX, decalageY, v.getNom().length()*RectangleNom.MULT, 20); 
    }

}
