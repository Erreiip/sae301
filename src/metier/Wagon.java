package src.metier;

import java.io.File;
import java.awt.Color;


public class Wagon
{
    public static File verso;

    public Color couleur;

    public Wagon(Color c)
    {
        this.couleur = c;
    }

    public Color getCouleur() { return this.couleur; }

    public static void setFile ( File f)
    {
        Wagon.verso = f;
    }

    public static File getFile ()
    {
        return Wagon.verso;
    }
}