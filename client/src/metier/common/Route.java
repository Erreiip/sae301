package client.src.metier;

import java.awt.Color;

public class Route implements Comparable<Route>
{
    private Ville v1;
    private Ville v2;
    private Integer cout;
    private Color couleurVoie1;
    private Color couleurVoie2;

    public Route(Ville v1, Ville v2, int cout, Color c)
    {
        this(v1,v2,cout,c,null);
    }

    public Route(Ville v1, Ville v2, int cout, Color c1, Color c2)
    {
        this.v1 = v1;
        this.v2 = v2;
        this.couleurVoie1 = c1;
        this.couleurVoie2 = c2;

        this.cout = cout;

        v1.ajouterRoute(this);
        v2.ajouterRoute(this);
    }

    public Ville getVille1   () { return this.v1;                   }
    public Ville getVille2   () { return this.v2;                   }
    public Color getCouleur1 () { return this.couleurVoie1;         }
    public Color getCouleur2 () { return this.couleurVoie2;         }
    public Integer getCout   () { return this.cout;                 } 
    public boolean estDouble () { return this.couleurVoie2 != null; } 

    public void setCout(int cout)
    {
        this.cout = cout;
    }

    public void setCouleur1(Color c) {
        this.couleurVoie1 = c;
    }

    public void setCouleur2(Color c) {
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
        return this.couleurVoie1.getRGB() - o.couleurVoie1.getRGB();
    }

    
}