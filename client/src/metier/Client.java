package client.src.metier;

import java.net.InetAddress;

import com.esotericsoftware.kryo.Kryo;

import client.src.common.ListenerClient;
import client.src.metier.common.Regles;
import server.src.Serveur;

public class Client extends com.esotericsoftware.kryonet.Client
{

    public Client(Regles regles)
    {
        super();

        Kryo kryo = this.getKryo();
        kryo.register(String.class);

        this.start();
        try{
            
            InetAddress address = this.discoverHost(Serveur.PORT_UDP, 5000);
            this.connect(5000, address, Serveur.PORT_TCP, Serveur.PORT_UDP);
        }catch (Exception e) { e.printStackTrace(); }

        this.sendUDP(regles);
        this.addListener( new ListenerClient());
    }
}
