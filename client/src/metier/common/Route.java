package client.src.metier.common;


public class Route implements Comparable<Route>
{
    private Ville v1;
    private Ville v2;
    private Integer cout;
    private Integer couleurVoie1;
    private Integer couleurVoie2;
    private Joueur joueur;

    public Route(){}

    public Route(Ville v1, Ville v2, int cout, Integer c)
    {
        this(v1,v2,cout,c,null, null);
    }

    public Route(Ville v1, Ville v2, int cout, Integer c1, Integer c2, Joueur j)
    {
        this.v1 = v1;
        this.v2 = v2;
        this.couleurVoie1 = c1;
        this.couleurVoie2 = c2;

        this.cout = cout;

        this.joueur = j;

        v1.ajouterRoute(this);
        v2.ajouterRoute(this);
    }

    public Ville getVille1   () { return this.v1;                   }
    public Ville getVille2   () { return this.v2;                   }
    public Integer getCouleur1 () { return this.couleurVoie1;         }
    public Integer getCouleur2 () { return this.couleurVoie2;         }
    public Integer getCout   () { return this.cout;                 }
    public Joueur  getJoueur () { return this.joueur;               } 

    public boolean estDouble () { return this.couleurVoie2 != null; } 
    public boolean estPrise  () { return this.joueur != null; } 


    public void setCout(int cout)
    {
        this.cout = cout;
    }

    public void setJoueur(Joueur j)
    {
        this.joueur = j;
    }


    public void setCouleur1(Integer c) {
        this.couleurVoie1 = c;
    }

    public void setCouleur2(Integer c) {
        this.couleurVoie2 = c;
    }

    public boolean contains( Ville v)
    {
        return (v1.equals(v) || v2.equals(v));
    }

    public String toString()
    {
        return "[" + this.v1 + " | " + this.v2 + "]";
    }

    @Override
    public int compareTo(Route o) {
        return this.couleurVoie1- o.couleurVoie1;
    }

    
}