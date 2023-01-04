package client.src.metier;

import java.net.InetAddress;

import com.esotericsoftware.kryo.Kryo;

import server.src.Serveur;

public class Client extends com.esotericsoftware.kryonet.Client
{

    public Client()
    {
        super();

        Kryo kryo = this.getKryo();
        kryo.register(String.class);

        this.start();
        try{
            
            InetAddress address = this.discoverHost(Serveur.PORT_UDP, 5000);
            this.connect(5000, address, Serveur.PORT_TCP, Serveur.PORT_UDP);
        }catch (Exception e) { e.printStackTrace(); }
    }
}
