package client.src.metier.reseau;

import common.ActionDef;
import common.ActionRoute;
import common.ActionSuppr;

import com.esotericsoftware.kryonet.Listener;

import client.src.metier.common.Joueur;

import java.io.File;

import com.esotericsoftware.kryonet.Connection;


public class ListenerClient extends Listener
{
    private Client client;

    public ListenerClient( Client client)
    {
        this.client = client;
    }

    public void received (Connection connection, Object object) {

        if ( object instanceof ActionSuppr )
        {
            ActionSuppr jeu = (ActionSuppr) object;
            this.client.setAction(jeu);
        }

        if ( object instanceof ActionDef )
        {
            ActionDef jeu = (ActionDef) object;
            this.client.setAction(jeu);
        }

        if ( object instanceof ActionRoute )
        {
            ActionRoute jeu = (ActionRoute) object;
            this.client.setAction(jeu);
        }

        if ( object instanceof String)
        {
            String s = (String) object;

            if ( s.contains("xml"))
            {
                String[] tabString = s.split(":");
                new ClientFile(tabString[1]);
                this.client.lireXml(new File("./jeu.xml"), false);
            }
        }

        if (object instanceof Joueur)
        {
            this.client.setJoueur((Joueur) object);
        }
    }
}
