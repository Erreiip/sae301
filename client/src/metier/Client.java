package client.src.metier;

import java.net.InetAddress;

import com.esotericsoftware.kryo.Kryo;

import client.src.Controleur;
import client.src.metier.common.Joueur;
import client.src.metier.common.ListenerClient;
import client.src.metier.common.Objectif;
import client.src.metier.common.Regles;
import client.src.metier.common.Wagon;
import server.src.Serveur;

public class Client extends com.esotericsoftware.kryonet.Client
{
    private Controleur ctrl;

    public Client(Regles regles, Controleur ctrl)
    {
        this(ctrl);
        
        sendTCP(regles);        
    }

    public Client (Controleur ctrl)
    {
        super();


        Kryo kryo = this.getKryo();
        kryo.register(String.class);
        kryo.register(Regles.class);
        kryo.register(Joueur.class);
        kryo.register(Wagon.class);
        kryo.register(Objectif.class);

        this.start();

        try{
            
            InetAddress address = this.discoverHost(Serveur.PORT_UDP, 5000);
            this.connect(5000, address, Serveur.PORT_TCP, Serveur.PORT_UDP);
        }catch (Exception e) { e.printStackTrace(); }

        this.addListener( new ListenerClient(this));
    }

    public void setRegles(Regles regles)
    {
        this.ctrl.setAlWagons(regles.getAlWagons());
        this.ctrl.setAlObjectif(regles.getAlObjectifs());
    }
}
