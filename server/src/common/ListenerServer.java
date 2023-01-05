package server.src.common;

import java.net.InetAddress;

import com.esotericsoftware.kryonet.*;

import client.src.metier.common.Regles;
import server.src.Serveur;

public class ListenerServer extends Listener 
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
    }

    public void connected (Connection connection) {
        if ( connection.getID() != 1)
        {
            try{
                InetAddress adr = InetAddress.getLocalHost();
                new ServeurFile(this.serveur);
                this.serveur.sendToTCP(connection.getID(), "xml:" + adr );
            } catch (Exception e) { e.printStackTrace(); }
        }
        
        if ( this.serveur.getRegles() != null && connection.getID() > this.serveur.getRegles().getNbJoueursMini() )
        {
            this.serveur.sendToAllTCP("vous pouvez mettre pret");
        }
	}

}
