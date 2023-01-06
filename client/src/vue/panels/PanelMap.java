package client.src.vue.panels;

import client.src.Controleur;
import client.src.metier.common.RectangleNom;
import client.src.metier.common.Route;
import client.src.metier.common.Ville;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.geom.RoundRectangle2D;
import java.awt.geom.AffineTransform;

public class PanelMap extends JPanel
{
    private Controleur ctrl;

    private BufferedImage fond;
    private int x = 0;
    private int y = 0;

    public PanelMap(Controleur ctrl)
    {
        this.ctrl = ctrl;

        this.setLayout(null);

        try{
            this.fond = ImageIO.read(this.ctrl.getFond());
        } catch(Exception e) { e.printStackTrace(); }
    }  
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;

        g.drawImage(this.fond, this.x, this.y, this.fond.getWidth(), this.fond.getHeight(), null);

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

            double angle = Math.atan((double) (r.getVille2().getY() - r.getVille1().getY()) / 
                                            (r.getVille2().getX() - r.getVille1().getX())  );

            int adj = (int) (12 * Math.cos(angle + 1.57)); //90° = 1.57
            int opp = (int) (12 * Math.sin(angle + 1.57));

            n1 = new Point((int)r.getVille1().getX() + adj, (int)r.getVille1().getY() + opp );

            n3 = new Point((int)r.getVille1().getX() - adj, (int)r.getVille1().getY() - opp );

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
}
