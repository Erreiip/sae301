package client.src.metier;

import java.awt.Color;

import java.io.File;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.jdom2.*;
import org.jdom2.input.*;

import client.src.Controleur;

public class Metier 
{
    private Controleur           ctrl;

    private ArrayList<Ville>     alVilles;

    private ArrayList<Objectif>  alObjectifs;

    private ArrayList<Wagon>     alWagons;
    private ArrayList<Wagon>     alDefausse;

    private HashMap<String,File> hsmFichiers;

    private Integer[]            tabReglesJoueurs;
    private Integer              nbWagonsFinParties;

    private Joueur               joueur;


    public static String IMG_FOND  = "fond";
    public static String IMG_JOKER = "joker";

    public static int COULEUR = 12;
    public static int JOKER   = 14;


    public Metier(Controleur ctrl)
    {
        this.ctrl                = ctrl;
   
        this.joueur              = new Joueur("Joueur", 0);
   
        this.alVilles            = new ArrayList<Ville>   ();
        this.alObjectifs         = new ArrayList<Objectif>();
        this.alWagons            = new ArrayList<Wagon>   ();
        this.alDefausse          = new ArrayList<Wagon>   ();

        this.hsmFichiers         = new HashMap<String,File>();
        this.tabReglesJoueurs    = null;
        this.nbWagonsFinParties  = null;
    }


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

        new Route (v1, v2, cout, c, c2);
    }

    
    public void initCarteWagons()
    {
        int taille = this.alWagons.size();
        for ( int cpt = 0; cpt < taille; cpt++ )
        {
            Wagon w = this.alWagons.get(cpt);
            int taille2;

            if ( w.getCouleur() != Color.LIGHT_GRAY ) taille2 = Metier.COULEUR;
            else                                      taille2 = Metier.JOKER;
            
            for ( int cpt2 = 0; cpt2 < taille2; cpt2++)
            {
                this.alWagons.add(new Wagon(w.getCouleur(), w.getFileRecto()));
            }
        }   
    }


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
        Integer[] tabRegleJoueur = { nbJoueursMin, nbJoueursMax, nbJoueurVoieDouble};

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


        //ajout
        this.hsmFichiers.put("fond",fichierFond);
        this.hsmFichiers.put("joker", fImageJoker);

        Objectif.setFileVerso(fVersoObjectifs);
        Objectif.setFileRecto(fRectoObjectif);
        Wagon   .setFileVerso(fVersoWagons);

        for ( int cpt = 0; cpt < fWagons.length; cpt++ )
        {
            if ( fWagons[cpt] != null )
                this.alWagons.add(new Wagon(new Color(Integer.parseInt(cWagons[cpt])), fWagons[cpt]));
        }

        this.nbWagonsFinParties = nbWagonsFinParties;
        this.tabReglesJoueurs   = tabRegleJoueur;

        this.initCarteWagons();
        this.joueur.setNbMarqueurs(nbWagonsParJoueur);
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

}
