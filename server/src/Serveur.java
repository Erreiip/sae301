package server.src;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.*;

import server.src.common.ListenerServer;

public class Serveur extends Server 
{
    public static int PORT_TCP = 7777;
    public static int PORT_UDP = 7778;


    public Serveur()
    {
        super();

        Kryo kryo = this.getKryo();
        kryo.register(String.class);

        try 
        {
            this.start();
            this.bind(PORT_TCP, PORT_UDP);
        } catch (Exception e) { e.printStackTrace(); }        

        this.addListener(new ListenerServer());
    }   
    
    
}
