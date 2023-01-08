package client.src.metier.reseau;

import java.io.File;
import java.net.InetAddress;

import com.esotericsoftware.kryo.Kryo;

import client.src.Controleur;
import client.src.metier.common.*;
import common.ActionDef;
import common.ActionRoute;
import common.ActionSuppr;
import server.src.Serveur;

public class Client extends com.esotericsoftware.kryonet.Client
{
    private Controleur ctrl;

    public Client(Regles regles, String path, Controleur ctrl)
    {
        this(ctrl);
        
        System.out.println("shesh");

        sendTCP(regles);  
        sendTCP(path);
    }

    public Client (Controleur ctrl)
    {
        super(1024*1024,2024*1024);

        this.ctrl = ctrl;

        Kryo kryo = this.getKryo();
        Serveur.kryoClass(kryo);

        this.start();

        try {

            InetAddress address = this.discoverHost(Serveur.PORT_UDP, 5000);
            this.connect(5000, address, Serveur.PORT_TCP, Serveur.PORT_UDP);
        } catch (Exception e) {
            ctrl.supprimerClient();
            return;
        }

        this.addListener(new ListenerClient(this));
    }
    
    public void lireXml(File f, boolean creerServeurClient)
    {
        this.ctrl.lireXml(f, creerServeurClient);
    }
    
    public void setJoueur(Joueur j)
    {
        if (this.ctrl.getJoueur() == null)
            this.ctrl.setJoueur(j);
        else  
            this.ctrl.setJoueurActif(j);
    }

    public void setAction(ActionDef act)
    {
        this.ctrl.setAction(act);
    }

    public void setAction(ActionSuppr act)
    {
        this.ctrl.setAction(act);
    }

    public void setAction(ActionRoute act)
    {
        this.ctrl.setAction(act);
    }
}
