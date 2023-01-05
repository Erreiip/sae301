package server.src;

import java.io.File;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.*;

import client.src.metier.common.Joueur;
import client.src.metier.common.Objectif;
import client.src.metier.common.Regles;
import client.src.metier.common.Wagon;
import server.src.common.ListenerServer;

public class Serveur extends Server 
{
    public static int PORT_TCP = 7779;
    public static int PORT_UDP = 7780;
    public static int PORT_TRANSFERT = 7781;


    private Regles regles;
    private File    xml;

    private Joueur  joueurActif;


    public Serveur()
    {
        super();

        this.joueurActif = null;
        this.xml         = null;
        this.regles      = null;

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
