package client.src.metier.common;

import java.io.File;
import java.awt.Color;


public class Wagon
{
    public static String verso = null;
    public String recto;

    public int couleur;

    public Wagon(int couleur, String f)
    {
        this.couleur = couleur;
        this.recto   = f;
    }

    public int getCouleur() { return this.couleur; }

    public String getFileRecto() { return this.recto; }

    public String toString()
    {
        return "Couleur : " + this.couleur;
    }

    public static void setFileVerso ( String f)
    {
        Wagon.verso = f;
    }

    public static String getFileVerso ()
    {
        return Wagon.verso;
    }
}