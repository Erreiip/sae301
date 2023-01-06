package client.src.metier;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Iterator;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.geom.Ellipse2D;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;


import org.apache.commons.io.FileUtils;
import org.jdom2.*;
import org.jdom2.input.*;

import client.src.Controleur;
import client.src.metier.common.Joueur;
import client.src.metier.common.Objectif;
import client.src.metier.common.Regles;
import client.src.metier.common.Route;
import client.src.metier.common.Ville;
import client.src.metier.common.Wagon;
import client.src.metier.reseau.Client;
import common.Action;
import server.src.Serveur;

public class Metier 
{
    private Controleur           ctrl;

    private ArrayList<Ville>     alVilles;

    private ArrayList<Objectif>  alObjectifs;
    private ArrayList<Objectif>  alDefausseO;


    private ArrayList<Wagon>     alWagons;
    private ArrayList<Wagon>     alDefausseW;


    private BufferedImage        fond;
    private File                 imgJoker;

    private Joueur               joueur;
    private Joueur               joueurActif;

    private Wagon                derniereCartePioche;

    private Regles               regles;
    private Action               action;

    private Client               client;

    public final static String IMG_FOND  = "fond";
    public final static String IMG_JOKER = "joker";

    public final static int COULEUR = 12;
    public final static int JOKER   = 14;

    private static int nbRecto = 0;


    public Metier(Controleur ctrl)
    {
        this.ctrl                = ctrl;
   
        //this.joueur              = null;
        this.joueur              = new Joueur(11111, 0); 
        this.joueurActif         = this.joueur;

   
        this.alVilles            = new ArrayList<Ville>   ();
        this.alObjectifs         = new ArrayList<Objectif>();
        this.alWagons            = new ArrayList<Wagon>   ();

        this.alDefausseO         = new ArrayList<Objectif>();
        this.alDefausseW         = new ArrayList<Wagon>   ();

        this.derniereCartePioche = null;
        this.fond                = null;
        this.imgJoker            = null;
        this.regles              = null;
        this.client              = null;
    }


    //--------------//
    //   TRAJET     //
    //--------------//

    public void creerTrajet(Ville v1, Ville v2, int cout, Color c)
    {
        this.creerTrajet(v1, v2, cout, c, null);
    }


    public void creerTrajet(Ville v1, Ville v2, int cout, Color c, Color c2)
    {
        for ( Route r : v1.getAlRoutes())
        {
            if ( r.getVille2() == v2 || r.getVille1() == v2 ) return;
        }

        new Route (v1, v2, cout, c, c2, null);
    }


    //--------------//
    //   OBJECTIF   //
    //--------------//

    public boolean verifierObjectif(Objectif obj)
    {
        Ville v1 = obj.getV1();
        Ville v2 = obj.getV2();
        ArrayList<Ville> alVillesVisitees = new ArrayList<Ville>();

        boolean b = rechercheObjectif(v1, v2, alVillesVisitees);

        obj.setPrit(b); 
        return b;
    }

    public boolean rechercheObjectif(Ville v, Ville vRecherchee, ArrayList<Ville> alVillesVisitees)
    {
        boolean retour = false;
        alVillesVisitees.add(v);
        if( v == vRecherchee ) return true;
        for ( Route r : v.getAlRoutes())
        {
            if ( r.getVille1() == v && r.getJoueur() == getJoueur() && !alVillesVisitees.contains(r.getVille2())) retour = rechercheObjectif(r.getVille2(), vRecherchee, alVillesVisitees);
            if ( r.getVille2() == v && r.getJoueur() == getJoueur() && !alVillesVisitees.contains(r.getVille1()) && !retour) retour = rechercheObjectif(r.getVille1(), vRecherchee, alVillesVisitees);
        }
        return retour;
    }


    //--------------//
    //   ROUTE      //
    //--------------//

    public boolean piocherWagon(Wagon w)
    {
        if ( derniereCartePioche == null )
        {
            derniereCartePioche = w;
            return true;
        }

        if ( derniereCartePioche != null && derniereCartePioche.getCouleur() != Color.LIGHT_GRAY.getRGB() )
        {
            derniereCartePioche = w;
            return true;
        }
    
        return false;
    } 



    //--------------//
    //   ROUTE      //
    //--------------//

    public void routePrise(ArrayList<Route> alRoute)
    {
        for ( Route r : alRoute)
        {
            r.setJoueur(this.joueur);
        }
    }

    public boolean ajouterRoute(Route r)
    {
        if ( r.estPrise() ) return false;

        if ( this.joueur.enleverMarqueurs(r.getCout()) ) 
        {
            r.setJoueur(this.joueur);
            return true;
        }

        return false;
    }


    //--------------//
    //   INIT       //
    //--------------//

    public void initCarteWagons()
    {
        int taille = this.alWagons.size();
        for ( int cpt = 0; cpt < taille; cpt++ )
        {
            Wagon w = this.alWagons.get(cpt);
            int taille2;

            if ( w.getCouleur() != Color.LIGHT_GRAY.getRGB() ) taille2 = Metier.COULEUR;
            else                                      taille2 = Metier.JOKER;
            
            for ( int cpt2 = 0; cpt2 < taille2; cpt2++)
            {
                this.alWagons.add(new Wagon(w.getCouleur(), w.getFileRecto()));
            }
        }   
    }


    //--------------//
    //   PIOCHE     //
    //--------------//

    public Wagon[] getPiocheVisible  () 
    { 
        if ( this.alWagons.size() < 7 ) { rajouterDefausseW(); }        
        
        Wagon[] tabWagonVisible = new Wagon[5];
        for ( int cpt = 0; cpt < tabWagonVisible.length; cpt++ )
        {
            tabWagonVisible[cpt] = this.alWagons.get(cpt);
        }

        return tabWagonVisible;
    } 
    
    public Objectif[] getPiocheVisibleObj() 
    { 
        if ( this.alObjectifs.size() < 5 ) { rajouterDefausseO(); } 

        Objectif[] tabObjectif = new Objectif[3];
        for ( int cpt = 0; cpt < tabObjectif.length; cpt++ )
        {
            tabObjectif[cpt] =this.alObjectifs.get(cpt);
        }

        return tabObjectif;
    }


    //--------------//
    //   DEFAUSSE   //
    //--------------//

    public void rajouterDefausseW()
    {
        for ( Wagon w : this.alWagons )
        {
            this.alDefausseW.add(w);
        }

        Collections.shuffle(this.alDefausseW);
        this.alWagons = new ArrayList<Wagon>(this.alDefausseW);
    }

    public void rajouterDefausseO()
    {
        for ( Objectif o : this.alObjectifs )
        {
            this.alDefausseO.add(o);
        }

        Collections.shuffle(this.alDefausseO);
        this.alObjectifs = new ArrayList<Objectif>(this.alDefausseO);
    }
    

    //--------------//
    //   SUPPRIMER  //
    //--------------//

    public void supprimerObj(ArrayList<Objectif> alObj) 
    {
        for ( Objectif o : alObj )
        {
            this.alObjectifs.remove(o);
        }
    }

    public void supprimerWagons(ArrayList<Wagon> alWagons) 
    {
        for ( Wagon w : alWagons )
        {
            this.alWagons.remove(w);
        }
    }

    public void supprimerObjToDef(ArrayList<Objectif> alObj) 
    {
        for ( Objectif o : alObj )
        {
            this.alObjectifs.remove(o);
            this.alDefausseO.add(o);
        }
    }


    public void supprimerWagonsToDef(ArrayList<Wagon> alWagons) 
    {
        for ( Wagon w : alWagons )
        {
            this.alWagons.remove(w);
            this.alDefausseW.add(w);
        }
    }


    //--------------//
    //   GETTERS  //
    //--------------//

    public BufferedImage getFond    () { return this.fond;     }
    public File          getImgJoker() { return this.imgJoker; }


    public boolean actionPossible() { return this.joueur == joueurActif; }

    public Joueur getJoueurActif() { return this.joueurActif; }
    public Joueur getJoueur() { return this.joueur; }

    public ArrayList<Ville> getAlVilles() { return this.alVilles; }
    public ArrayList<Route> getAlRoutes() 
    {
        ArrayList<Route> alRet = new ArrayList<Route>();

        for ( Ville v : this.alVilles )
        {
            for ( Route r : v.getAlRoutes())
                if ( !alRet.contains(r) ) alRet.add(r);
        }

        return alRet;
    }

    public Wagon getWagonVerso        ()        
    { 
        if ( this.alWagons.size() >= 6)
        {
            return this.alWagons.get(5);
        }

        return null;
    }

    public boolean secondWagon () { return this.derniereCartePioche != null; }



    public void creerClient           () { this.client = new Client(this.ctrl); }
    public void supprimerClient       () { this.client = null; }
    public void setJoueurActif(Joueur j) { this.joueurActif = joueur; }


    //--------------//
    //   XML        //
    //--------------//

    public void lectureXML(File f)
    {

        /* Création du système */
        SAXBuilder sxb              = new SAXBuilder();
        org.jdom2.Document document = null;
        File file                   = f;

        try 
        {
            document = sxb.build(file);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            return;
        }

        assert document    != null;
        Element racine      = document.getRootElement();

        /* Lecture des balises */

        //Parametre
        Element parametres      = racine.getChild("parametres");
        Element regleJeu        = parametres.getChild("regleJeu"); 
        Element reglesJP        = parametres.getChild("regleJoueurEtPoints");
    
        String fond             = regleJeu.getAttributeValue("fond");
        String[] dimensions     = new String[2];
        dimensions[0]           = regleJeu.getAttributeValue("x");
        dimensions[1]           = regleJeu.getAttributeValue("y");
        //Set du fond
        File fichierFond        = upImage(fond, "fond");

        int nbJoueursMin        = Integer.parseInt(reglesJP.getChild("joueursMini").getText());
        int nbJoueursMax        = Integer.parseInt(reglesJP.getChild("joueursMaxi").getText());
        int nbJoueurVoieDouble  = Integer.parseInt(reglesJP.getChild("nbJoueursVoiesDoubles").getText());

        //Set des reglesJoueurs
        Integer[] tabReglesJoueur = { nbJoueursMin, nbJoueursMax, nbJoueurVoieDouble};

        int nbWagonsParJoueur   = Integer.parseInt(reglesJP.getChild("wagonsParJoueur").getText());
        int nbWagonsFinParties  = Integer.parseInt(reglesJP.getChild("nbWagonsFinParties").getText());
        
        Element ptParRoute      = reglesJP.getChild("ptParRoute");
        String[] ppWagons       = new String[6];
        for (int i = 0; i < 6; i++) ppWagons[i] = ptParRoute.getAttributeValue("p" + (i+1));

        //Set RegleNbPoints

        Element reglesWO        = parametres.getChild("regleWagonsObjectif");

        String  imgJoker        = reglesWO.getChild("imageJoker").getText();
        File    fImageJoker     = upImage(imgJoker, "imageJoker");

        String  versoWagons     = reglesWO.getChild("imageVersoWagons").getText();
        File    fVersoWagons    = upImage(versoWagons, "versoWagons");

        String  rectoObjectif   = reglesWO.getChild("imageRectoObjectif").getText();
        File    fRectoObjectif  = upImage(rectoObjectif, "rectoObjectif");
        
        String  versoObjectifs  = reglesWO.getChild("imageVersoObjectif").getText();
        File    fVersoObjectifs = upImage(versoObjectifs, "versoObjectifs");

        Element couleursWagons       = reglesWO.getChild("couleursWagons");
        Element imagesRectoWagons    = reglesWO.getChild("imagesRectoWagons");

        String[]  cWagons            = new String[16];
        String[]  iRectoWagons       = new String[16];
        File  []  fWagons            = new File  [16];
        for (int i = 0; i < iRectoWagons.length; i++) {
            try {
                cWagons[i]      = couleursWagons.getAttributeValue("c" + (i + 1));
                iRectoWagons[i] = imagesRectoWagons.getAttributeValue("i" + (i + 1));
                fWagons[i]      = upImage(iRectoWagons[i], "i" + i);
            } catch (Exception e) {
            }
        }

        // ListeVille
        Element listeVille       = racine.getChild("listeVille");
        List<Element> villes     = listeVille.getChildren("ville");
        Iterator<Element> iVille =  villes.iterator();
        while(iVille.hasNext())
        {
            Element ville        = (Element) iVille.next();
  
            String nomVille      =                            ville.getAttributeValue("nom");
            Color couleurVille   = new Color(Integer.parseInt(ville.getChild("couleur").getText()));
            int taille           =           Integer.parseInt(ville.getChild("taille").getText());
            Element coordonnes   =                            ville.getChild("coordonnes");

            int xVille           = Integer.parseInt(coordonnes.getAttributeValue("x"));
            int yVille           = Integer.parseInt(coordonnes.getAttributeValue("y"));

            Element listeRoute   = ville.getChild("listeRoute");
            List<Element> routes = listeRoute.getChildren("route");
            Iterator<Element> i2 = routes.iterator();

            boolean villeExist = false;

            // Si la ville existe déjà MAIS sans les autres param, on les set
            for(Ville villeLl : this.alVilles)
            {
                if(villeLl.getNom().equals(nomVille))
                {
                    villeLl.setCoordonnee(xVille, yVille, taille, couleurVille);
                    villeExist = true;
                }
            }

            // Si la ville n'existe pas on l'a créer avec tout les attributs
            if(!villeExist)
            {
                Ville v = new Ville(couleurVille, nomVille, xVille, yVille, taille);
                this.alVilles.add(v);
            }

            while(i2.hasNext())
            {
                //Route

                Element route        = (Element) i2.next();
                String nomVille1     = route.getChild("ville1").getText();
                String nomVille2     = route.getChild("ville2").getText();
                String cout          = route.getChild("cout").getText();
                Element couleursR    = route.getChild("couleurR");
                String couleurRoute1 = couleursR.getAttributeValue("c1");
                String couleurRoute2 = couleursR.getAttributeValue("c2");
                boolean routeExist   = false;

                Ville vl1 = null;
                Ville vl2 = null;

                for(Ville villeL : this.alVilles)
                {
                    if(nomVille1.equals(villeL.getNom())) 
                    {
                        vl1 = villeL;
                    }

                    if(nomVille2.equals(villeL.getNom())) 
                    {
                        vl2 = villeL;
                    }

                }

                //Si elles n'existent pas on les créer et ajoute avec juste le nom ( car on a pas encore lu les autres attributs )
                if(vl1 == null)
                {
                    vl1 = new Ville(nomVille1);
                    this.alVilles.add(vl1);
                }

                if(vl2 == null) 
                {
                    vl2 = new Ville(nomVille2);
                    this.alVilles.add(vl2);
                }

                for(Route rte : vl1.getAlRoutes())
                {
                    //On vérifie si la route existe déjà
                    if(rte.getVille1().getNom().equals(vl1.getNom()) && rte.getVille2().getNom().equals(vl2.getNom()))
                    {
                        routeExist = true; break;
                    }
                }

                //Si elle n'existe pas on l'a créer
                if(!routeExist) 
                {
                    if(couleurRoute2.equals("null")) this.creerTrajet(vl1, vl2, Integer.parseInt(cout), new Color(Integer.parseInt(couleurRoute1)));
                    else this.creerTrajet(vl1, vl2, Integer.parseInt(cout), new Color(Integer.parseInt(couleurRoute1)), new Color(Integer.parseInt(couleurRoute2)));
                }
            }
            
        }

        //Liste Objectif
        Element listeCarteObjectif   = racine.getChild("listeCarteObjectif");
        List<Element> objectifs      = listeCarteObjectif.getChildren("objectif");
        Iterator<Element> iObjectif  =  objectifs.iterator();
        while(iObjectif.hasNext())
        {
            Element objectif         = (Element) iObjectif.next();

            String nville1           = objectif.getChild("ville1").getText();
            String nville2           = objectif.getChild("ville2").getText();
            int nbPoint              = Integer.parseInt(objectif.getChild("nbPoint").getText());

            Ville villeObj1          = null;
            Ville villeObj2          = null;

            for(Ville v : this.alVilles) 
            {
                if(v.getNom().equals(nville1))
                {
                    villeObj1 = v;
                }

                if(v.getNom().equals(nville2))
                {
                    villeObj2 = v;
                }
            }
           
            this.alObjectifs.add(new Objectif(villeObj1, villeObj2, nbPoint));
        }

        BufferedImage fichierFondTaille = null;
        if(dimensions[0] != "" && dimensions[1] != "")
        {
            int width  = Integer.parseInt(dimensions[0]);
            int height = Integer.parseInt(dimensions[1]);

            try
            {
                BufferedImage img = ImageIO.read(fichierFond);
                Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                fichierFondTaille = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

                Graphics2D g2d = fichierFondTaille.createGraphics();
                g2d.drawImage(tmp, 0, 0, null);
                g2d.dispose();

            } catch (Exception e) { e.printStackTrace(); }
        }


        //ajout
        this.fond     = fichierFondTaille;
        this.imgJoker = fImageJoker;

        Objectif.setFileVerso (fVersoObjectifs.getAbsolutePath());
        Objectif.setFileRectoS(fRectoObjectif.getAbsolutePath());
        Wagon   .setFileVerso (fVersoWagons.getAbsolutePath());

        for ( int cpt = 0; cpt < fWagons.length; cpt++ )
        {
            if ( fWagons[cpt] != null )
                this.alWagons.add(new Wagon(Integer.parseInt(cWagons[cpt]), fWagons[cpt].getAbsolutePath()));
        }

        this.regles = new Regles(nbWagonsParJoueur, nbWagonsFinParties, tabReglesJoueur);

        this.initCarteWagons();

        //a enlever 
        for ( int cpt = 0; cpt < 10; cpt++)
            this.joueur.ajouterWagon(this.alWagons.get(cpt));
        
        //a enlever
        for ( Objectif o : this.alObjectifs)
        {
            Metier.colorier(o, ctrl);
            this.joueur.ajouterObjectif(o);
        }


        new Serveur();
        this.client = new Client(this.regles, fichierFond.getAbsolutePath(), this.ctrl);
    }


    public File upImage(String imageEncodee, String nom) 
    {
        byte[] decodedBytes = Base64.getDecoder().decode(imageEncodee);
        File file = new File("assets/" + nom + ".png");
        try {
			FileUtils.writeByteArrayToFile(file, decodedBytes);
		}
        catch (Exception e) { e.printStackTrace(); }
        return file;
    }


    private static void colorier(Objectif obj, Controleur ctrl)
    {
        BufferedImage img = null, fond = null;

        Ville v1 = obj.getV1();
        Ville v2 = obj.getV2();

        try{
            img = ImageIO.read(new File(Objectif.getFileRectoS()));
            fond = ctrl.getFond();
        }catch (Exception e) { e.printStackTrace();  }

        int widthFond  = fond.getWidth();
        int heightFond = fond.getHeight();

        int widthRecto  = img.getWidth();
        int heightRecto = img.getHeight();

        double propotionsWidth  = (widthFond  + 0d) / widthRecto;
        double propotionsHeight = (heightFond + 0d) / heightRecto;  

        double v1X = v1.getCenterX() / propotionsWidth;
        double v1Y = v1.getCenterY() / propotionsHeight;

        double v2X = v2.getCenterX() / propotionsWidth;
        double v2Y = v1.getCenterY() / propotionsHeight;
        
        if ( v2X > widthRecto - 230 ) { v2X = widthRecto - 230; }
        if ( v1X > widthRecto - 230 ) { v1X = widthRecto - 230; }

        if ( v2Y < 50 ) { v2Y = 50; }
        if ( v1Y < 50 ) { v1Y = 50; }
        
        double taille = v1.getWidth() / (propotionsWidth / 2);

        Ellipse2D ville1 = new Ellipse2D.Double(v1X, v1Y, taille, taille );
        Ellipse2D ville2 = new Ellipse2D.Double(v2X, v2Y, taille, taille );

        String coutObj = obj.getNbPoints() + "";

        Graphics2D g = (Graphics2D) img.getGraphics();
        g.setColor(Color.BLACK);
        
        g.fill(ville1);
        g.fill(ville2);

        ((Graphics)g).setFont(new Font("TimesRoman", Font.PLAIN, 120)); 
        g.drawString(coutObj, widthRecto - 160 , heightRecto - 70);
        ((Graphics)g).setFont(new Font("TimesRoman", Font.PLAIN, 30)); 
        String s = v1.getNom() + " - " + v2.getNom();
        g.drawString(s, 80, 80);

        File file = new File("./assets/recto" + Metier.nbRecto++ + ".png");
        try {
            file.createNewFile();
            ImageIO.write(img, "png", file);
        } catch (Exception e ) { e.printStackTrace(); }

        obj.setFileRecto(file.getAbsolutePath());
    }

}
