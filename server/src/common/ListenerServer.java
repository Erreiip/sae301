package server.src.common;

import java.net.InetAddress;

import com.esotericsoftware.kryonet.*;

import client.src.metier.common.Regles;
import common.Action;
import server.src.Serveur;

public class ListenerServer extends Listener implements Runnable
{
    private Serveur serveur;

    public ListenerServer( Serveur serv)
    {
        this.serveur = serv;
    }

    public void received (Connection connection, Object object) {
        
        if ( object instanceof Regles)
        {
            this.serveur.setRegles((Regles) object);
        }

        if ( object instanceof String)
        {
            if ( connection.getID() == 1 )
                this.serveur.setXml((String) object);
        }

        if ( object instanceof Action )
        {
            this.serveur.envoyerAction((Action) object);
        }
    }

    public void connected (Connection connection) {
        if ( this.serveur.getRegles() != null && connection.getID() > this.serveur.getRegles().getNbJoueursMaxi() )
        {
            connection.close();
        }


        if ( connection.getID() != 1)
        {
            try{
                Thread thread =  new Thread(this) ;
                thread.start() ;

                InetAddress adr = InetAddress.getLocalHost();
                this.serveur.sendToTCP(connection.getID(), "xml:" + adr.getHostName() );
            } catch (Exception e) { e.printStackTrace(); }
        }
        
        if ( this.serveur.getRegles() != null && connection.getID() > this.serveur.getRegles().getNbJoueursMini() )
        {
            this.serveur.sendToAllTCP("vous pouvez mettre pret");
        }

	}

    
    @Override
    public void run() 
    {
        new ServeurFile(this.serveur);
    }

}
