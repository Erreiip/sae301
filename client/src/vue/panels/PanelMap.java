package client.src.vue.panels;

import client.src.Controleur;
import client.src.metier.common.RectangleNom;
import client.src.metier.common.Route;
import client.src.metier.common.Ville;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.awt.geom.RoundRectangle2D;
import java.awt.geom.AffineTransform;

public class PanelMap extends JPanel implements MouseListener
{
    private Controleur ctrl;

    private ArrayList<Ville> alVilleAColorier;

    private BufferedImage fond;

    private HashMap<Route, Polygon> hmRoutesPolygons;

    public PanelMap(Controleur ctrl)
    {
        this.ctrl = ctrl;

        this.fond = this.ctrl.getFond();
        this.alVilleAColorier = new ArrayList<Ville>();


        this.hmRoutesPolygons = new HashMap<Route, Polygon>();

        this.addMouseListener(this);

        this.setLayout(null);
    }  
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;

        g.drawImage(this.fond, this.x, this.y, this.fond.getWidth(), this.fond.getHeight(), null);

        // ROUTE
        for (Route r : this.ctrl.getAlRoutes())
        {
            g.setColor(r.getCouleur1());
            
            int tailleX = Math.subtractExact((int) (r.getVille1().getCenterX() + r.getVille1().getTaille()), (int) (r.getVille2().getCenterX() + r.getVille1().getTaille()));
            int tailleY = Math.subtractExact((int) (r.getVille1().getCenterY() + r.getVille1().getTaille()), (int) (r.getVille2().getCenterY() + r.getVille1().getTaille()));

            int width = tailleX / (r.getCout()+1);
            int height = tailleY / (r.getCout()+1);

            int longueur = Math.max( Math.abs(tailleX / r.getCout()), Math.abs(tailleY/r.getCout()));
            if ( r.getCout() > 1 ) longueur -= longueur /r.getCout();
            int largeur =  18;

            Point n1,n3;
            Point n2,n4;

            double angle = Math.atan((double) (r.getVille2().getY() - r.getVille1().getY()) / 
                                            (r.getVille2().getX() - r.getVille1().getX())  );

            int adj = (int) (12 * Math.cos(angle + 1.57)); //90° = 1.57
            int opp = (int) (12 * Math.sin(angle + 1.57));

            n1 = new Point((int)r.getVille1().getX() + adj, (int)r.getVille1().getY() + opp );
            n3 = new Point((int)r.getVille1().getX() - adj, (int)r.getVille1().getY() - opp );

            n2 = new Point((int)(r.getVille2().getX() + adj), (int)(r.getVille2().getY() + opp));
            n4 = new Point((int)(r.getVille2().getX() - adj), (int)(r.getVille2().getY() - opp));

            // -- Polygon pour identifier la route
            g.setColor(new Color(0,0,0,0));
        
            int[] tabx = new int[4];
            int[] taby = new int[4];
            
            int tailleVille = (int)(r.getVille1().getTaille())/2;

            tabx[0] = (int)n1.getX() + tailleVille;
            tabx[1] = (int)n2.getX() + tailleVille;
            tabx[2] = (int)n4.getX() + tailleVille;
            tabx[3] = (int)n3.getX() + tailleVille;

            taby[0] = (int)n1.getY() + tailleVille;
            taby[1] = (int)n2.getY() + tailleVille;
            taby[2] = (int)n4.getY() + tailleVille;
            taby[3] = (int)n3.getY() + tailleVille;

            int nbPoints = 4;
            
            Polygon p = new Polygon(tabx, taby, nbPoints);

            g.drawPolygon(tabx, taby, nbPoints);

            this.hmRoutesPolygons.put( r, p );
            // --

            g.setColor(r.getCouleur1());

            Ville v = r.getVille1();

            int x = width;
            int y = height;               
            

            RoundRectangle2D fig1, fig2 = null;
            Shape fig3;
            for ( int cpt = 0; cpt < r.getCout(); cpt++)
            {
                if ( r.estDouble() )
                {   
                    fig1 = new RoundRectangle2D.Double( n1.getX() - x , n1.getY() - y , longueur, largeur, 15d, 15d);
                    fig2 = new RoundRectangle2D.Double( n3.getX() - x , n3.getY() - y , longueur, largeur, 15d, 15d); //v.getCenterX() - x - longueur/2 , v.getCenterY() - y - largeur/2 , longueur, largeur, 15d, 15d

                } else {
                    fig1 = new RoundRectangle2D.Double( v.getCenterX() - x - longueur/2 , v.getCenterY() - y - largeur/2, longueur, largeur, 15d, 15d);
                }

                AffineTransform t = new AffineTransform();
                t.rotate(angle, fig1.getCenterX(), fig1.getCenterY());
                fig3 = t.createTransformedShape(fig1);

                g2d.setColor(r.getCouleur1());
                g2d.fill(fig3);

                g2d.setColor(Color.BLACK);
                g2d.draw(fig3);

                if ( r.estDouble() )
                {
                    AffineTransform t2 = new AffineTransform();
                    t2.rotate(angle, fig2.getCenterX(), fig2.getCenterY());
                    fig3 = t2.createTransformedShape(fig2);
                    
                    g2d.setColor(r.getCouleur2());
                    g2d.fill(fig3);
                    
                    g2d.setColor(Color.BLACK);
                    g2d.draw(fig3);
                }

                x += width;
                y += height;
            }
        }

        // ELLIPSE VILLE
        for (Ville v : this.ctrl.getAlVilles())
        {
            g2d.setColor(v.getCouleur());
            g2d.fillOval((int)v.getX(), (int)v.getY(), 20, 20);
            g2d.setColor(Color.BLACK);
            g2d.drawOval((int)v.getX(), (int)v.getY(), 20, 20);
        }

        // RECTANGLE NOM

        for ( Ville v : this.ctrl.getAlVilles())
        {
            RectangleNom r = v.getRectangle();

            r.maj();

            g.setColor(Color.WHITE);
            g2d.fill(r);

            g.setColor(Color.BLACK);
            g2d.draw(r);

            g.setColor(Color.BLACK);
            g2d.drawString(r.getNom(), r.getPosX()+2, (int) r.getCenterY() + 5);
        }
    }

    public Dimension getPreferredSize() 
    {
        return new Dimension(this.fond.getWidth(), this.fond.getHeight());
    }

    public void mouseClicked(MouseEvent e)
    {
        int x = e.getX();
        int y = e.getY();

        for (Route r : this.hmRoutesPolygons.keySet())
        {
            if ( this.hmRoutesPolygons.get(r).contains(x, y) )
            {
                System.out.println("Route : " + r.getVille1().getNom() + " - " + r.getVille2().getNom());
            }
        }
    }

    public void mouseEntered(MouseEvent e)
    {

    }

    public void mouseExited(MouseEvent e)
    {

    }

    public void mousePressed(MouseEvent e)
    {

    }

    public void mouseReleased(MouseEvent e)
    {

    }
}
