package server.src.common;

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
    }

    public void connected (Connection connection) {
        //envoyer le xml

        if ( this.serveur.getRegles() != null && connection.getID() > this.serveur.getRegles().getNbJoueursMini() )
        {
            this.serveur.sendToAllTCP("vous pouvez mettre pret");
        }
	}

}
