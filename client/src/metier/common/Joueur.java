package client.src.metier.common;

import java.awt.Color;
import java.util.ArrayList;

public class Joueur implements Comparable<Joueur>
{
    private Integer             couleur;
    
    private int                 id;
    private String              nom;

    private int                 nbMarqueurs;
    private int                 nbPv;
    private ArrayList<Wagon>    alWagons;
    private ArrayList<Objectif> alObjectifs;

    public Joueur(){}

    public Joueur(Integer couleur, int nbMarqueurs, int id)
    {
        this.couleur = couleur;
        this.nbMarqueurs = nbMarqueurs;
        this.nbPv = 0;

        this.id = id;

        this.alObjectifs = new ArrayList<Objectif>();
        this.alWagons    = new ArrayList<Wagon>();
    }

    public boolean enleverMarqueurs(int nbMarqueurs) 
    { 
        if ( (this.nbMarqueurs - nbMarqueurs) < 0) return false;
        
        this.nbMarqueurs -= nbMarqueurs;  
        return true;
    }

    public ArrayList<Wagon> enleverJetons(int couleur, int nb)
    {
        ArrayList<Wagon> alWagon = new ArrayList<Wagon>();

        for ( int cpt = 0; cpt < this.alWagons.size(); cpt++)
        {
            Wagon j = this.alWagons.get(cpt);

            if ( j.getCouleur() == couleur) 
            {
                this.alWagons.remove(j);
                alWagon.add(j);
                nb--;
                cpt--;
            }

            if (nb == 0 ) return alWagon;
        }

        for ( int cpt = 0; cpt < this.alWagons.size(); cpt++)
        {
            Wagon j = this.alWagons.get(cpt);

            if ( j.getCouleur() == Color.LIGHT_GRAY.getRGB() )
            {
                this.alWagons.remove(j);
                alWagon.add(j);
                nb--;
                cpt--;
            }

            if (nb == 0 ) return alWagon;
        }

        return null;
    }




    public ArrayList<Wagon>    getMain()      { return this.alWagons; }
    public ArrayList<Objectif> getObjectifs() { return this.alObjectifs; }

    public Integer getCouleur        () {return couleur;}
    public int     getNbMarqueurs    () {return nbMarqueurs;}
    public int     getNbPv           () {return nbPv;}

    public void setNbMarqueurs(int nbMarqueurs) { this.nbMarqueurs = nbMarqueurs; }
    public void setCouleur    (Integer couleur) { this.couleur = couleur;         }


    public void ajouterWagon   (Wagon wagon)  { this.alWagons.add(wagon); }
    public void ajouterObjectif(Objectif obj) { this.alObjectifs.add(obj);}
    public void ajouterPV      (Integer pv )  { this.nbPv += pv;  } 

    public void retirerPv      (Integer pv )  { this.nbPv -= pv;  }
    public void setPv          (Integer pv )  { this.nbPv  = pv;  }

    
    public int getId() { return this.id; }

    public String getNom()           { return this.nom; }
    public void   setNom(String nom) { this.nom = nom;  }

    @Override
    public int compareTo(Joueur o) {
        return o.nbPv - this.nbPv;
    }
    
}