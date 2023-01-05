package client.src.metier.common;

import  client.src.metier.Client;
import common.Action;

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
        if ( object instanceof Action )
        {
            Action jeu = (Action) object;
            this.client.setAction(jeu);
        }

        System.out.println(object);
    }
}
