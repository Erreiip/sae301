package client.src.metier.reseau;

import common.Action;

import com.esotericsoftware.kryonet.Listener;

import client.src.metier.common.Joueur;

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

        if ( object instanceof String)
        {
            String s = (String) object;

            if ( s.contains("xml"))
            {
                String[] tabString = s.split(":");
                new ClientFile(tabString[1]);
            }
        }

        if (object instanceof Joueur)
        {
            this.ctrl.setJoueurActif((Joueur) object);
        } 
    }
}
