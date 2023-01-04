package client.src.metier;

import java.net.InetAddress;

import com.esotericsoftware.kryo.Kryo;

import client.src.Controleur;
import client.src.metier.common.*;
import common.Action;
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
        Serveur.kryoClass(kryo);


        this.start();

        try{
            
            InetAddress address = this.discoverHost(Serveur.PORT_UDP, 5000);
            this.connect(5000, address, Serveur.PORT_TCP, Serveur.PORT_UDP);
        }catch (Exception e) { e.printStackTrace(); }

        this.addListener( new ListenerClient(this));
    }

    public void setAction(Action act)
    {
        this.ctrl.supprimerWagons(act.getAlWAgons());
        this.ctrl.supprimerObj    (act.getAlObjectifs());
        this.ctrl.routePrise      (act.getAlRoutes());
    }
}
