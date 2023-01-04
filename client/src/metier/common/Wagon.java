package client.src.metier;

import java.io.File;
import java.awt.Color;


public class Wagon
{
    public static File verso = null;
    public File recto;

    public Color couleur;

    public Wagon(Color c, File f)
    {
        this.couleur = c;
        this.recto   = f;
    }

    public Color getCouleur() { return this.couleur; }

    public File getFileRecto() { return this.recto; }

    public String toString()
    {
        return "Couleur : " + this.couleur.getRGB();
    }

    public static void setFileVerso ( File f)
    {
        Wagon.verso = f;
    }

    public static File getFileVerso ()
    {
        return Wagon.verso;
    }
}