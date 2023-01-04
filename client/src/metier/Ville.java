package client.src.metier;

import java.awt.geom.Ellipse2D;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import client.src.common.RectangleNom;

import java.awt.Color;

public class Ville extends Ellipse2D.Double implements Comparable<Ville>
{
    private Color couleur;
    private String nom;

    private RectangleNom rectangleNom;
    private ArrayList<Route> alRoute;

    private boolean modifiable;


    private static ArrayList<String> alNomsVilles = new ArrayList<String>();
    private static int numVille = 0;

    static
    {
        Ville.initAlNoms();
    }


    public Ville(Color couleur, String nom, int x, int y, int taille)
    {
        super(x,y,taille,taille);
        this.couleur = couleur;
        this.nom = nom;

        this.rectangleNom = new RectangleNom(this, 0, 40);

        this.alRoute = new ArrayList<Route>();

        this.modifiable = true;
    }

    public Ville(String nom)
    {
        this(null, nom, 0, 0, 0);
    }
    
    public Ville ( Color c, int x, int y, int taille)
    {
        this(c, "", x, y, taille);
        
        if (Ville.numVille > Ville.alNomsVilles.size() - 1)
            Ville.numVille = 0;

        this.setNom(Ville.alNomsVilles.get(Ville.numVille++));
    }

    public ArrayList<Route> getAlRoutes()
    {
        return this.alRoute;
    }

    public void ajouterRoute(Route r)
    {
        this.alRoute.add(r);
    }

    public Color getCouleur() { return couleur;}
    public String getNom   () { return this.nom; }
    

    public double getX         () {return super.getX();        }
    public double getY         () {return super.getY();        }
    public double getTaille    () { return super.getHeight();  }
    public boolean isModifiable    () { return this.modifiable;    }
    
    public RectangleNom getRectangle() { return this.rectangleNom; }
    

    public void setNom(String nom)          {this.nom = nom; }
    public void setCouleur(Color couleur)   {this.couleur = couleur;}
    
    public void setModifiable (boolean modif) { this.modifiable = modif; }

    public void setX(int x) { super.x = x; }
    public void setY(int y) { super.y = y; }
    
    public void setTaille(double taille)
    {
        super.height = taille;
        super.width  = taille;
    }
    
    public void setCoordonnee(int x, int y, int taille, Color couleur) 
    {
        super.height = taille;
        super.width = taille;
        super.x = x;
        super.y = y;
        this.couleur = couleur;
    }


    public String toString()
    {
        return this.nom;
    }


    private static void initAlNoms()
    {
        try {
            Scanner sc = new Scanner(new File("./src/data/villes.data"));

            while ( sc.hasNextLine() )
            {
                Ville.alNomsVilles.add(sc.nextLine());
            }
        }catch(Exception e ) { e.printStackTrace(); }
    }

    @Override
    public int compareTo(Ville o) {
        return this.nom.compareTo(o.nom);
    }
}
