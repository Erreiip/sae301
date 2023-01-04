package server.src;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.*;

import client.src.metier.common.Joueur;
import client.src.metier.common.Objectif;
import client.src.metier.common.Regles;
import client.src.metier.common.Wagon;
import server.src.common.ListenerServer;

public class Serveur extends Server 
{
    public static int PORT_TCP = 7777;
    public static int PORT_UDP = 7778;

    private Regles regles;


    public Serveur()
    {
        super();

        this.regles = null;

        Kryo kryo = this.getKryo();
        kryo.register(String.class);
        kryo.register(Regles.class);
        kryo.register(Joueur.class);
        kryo.register(Wagon.class);
        kryo.register(Objectif.class);




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

    public Regles getRegles() { return this.regles; }
    
    
}
