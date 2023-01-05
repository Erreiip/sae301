package server.src;

import java.io.File;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.*;

import client.src.metier.common.Joueur;
import client.src.metier.common.Objectif;
import client.src.metier.common.Regles;
import client.src.metier.common.Wagon;
import common.Action;
import server.src.common.ListenerServer;

public class Serveur extends Server 
{
    public static int PORT_TCP = 7779;
    public static int PORT_UDP = 7780;
    public static int PORT_TRANSFERT = 7781;


    private Regles regles;
    private File    xml;

    private Joueur  joueurActif;
    private ArrayList<Joueur>  alJoueurs;
    private Integer index;
    private Integer indexDernier;


    public Serveur()
    {
        super();

        this.joueurActif = null;
        this.xml         = null;
        this.regles      = null;
        this.index       = 0;
        this.indexDernier= null;


        Kryo kryo = this.getKryo();
        Serveur.kryoClass(kryo);

        try 
        {
            this.start();
            this.bind(PORT_TCP, PORT_UDP);
        } catch (Exception e) { e.printStackTrace(); }        

        this.addListener(new ListenerServer(this));
    }   

    public void setRegles(Regles r)
    {
        this.regles = r;
    }

    public void setXml(String path)
    {
        this.xml = new File(path);
    }

    public void initJoueurs()
    {
        for ( Connection c : this.getConnections())
        {
            Joueur j = new Joueur();
            j.setNbMarqueurs(this.regles.getNbWagonsParJoueurs());
            j.setCouleur( new Color( (int) (Math.random() * 1000)).getRGB() ) ;
            this.alJoueurs.add(j);
            this.sendToTCP(c.getID(), j);
        }

        Collections.shuffle(alJoueurs);

        this.joueurActif = this.alJoueurs.get(this.index);
    }

    public void lancer()
    {
        this.initJoueurs();
        this.sendToAllTCP(this.joueurActif);
    }

    public void envoyerAction(Action a)
    {
        this.sendToAllTCP(a);

        if ( indexDernier != null)
        {
            for ( Joueur j : this.alJoueurs )
            {
                if ( this.regles.getNbWagonsFinParties() < j.getNbMarqueurs() )
                {
                    this.indexDernier = this.index;
                }
            }
        } else
        {
            if ( this.index+1 == indexDernier)
            {
                this.sendToAllTCP("fini");
            }
        }

        this.index++;

        if ( this.index == this.alJoueurs.size() )
            this.index = 0;

        this.joueurActif = this.alJoueurs.get(this.index);

        this.sendToAllTCP(this.joueurActif);
    }

    public Regles getRegles() { return this.regles; }
    public File   getXml   () { return this.xml;    }

    
    public static void kryoClass(Kryo kryo)
    {
        kryo.register(String.class);
        kryo.register(Float.class);
        kryo.register(Integer.class);
        kryo.register(Regles.class);
        kryo.register(Joueur.class);
        kryo.register(Wagon.class);
        kryo.register(Objectif.class);
        kryo.register(java.util.ArrayList.class);
        kryo.register(Integer[].class);
    }
}
