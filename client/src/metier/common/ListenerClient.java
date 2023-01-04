package client.src.metier.common;

import  client.src.metier.Client;

import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Connection;


public class ListenerClient extends Listener
{
    private Client client;

    public ListenerClient( Client client)
    {
        this.client = client;
    }

    public void received (Connection connection, Object object) {
        if ( object instanceof Regles )
        {
            Regles regles = (Regles) object;
            this.client.setRegles(regles);
        }
    }
}
