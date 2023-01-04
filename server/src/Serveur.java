package server.src;

import com.esotericsoftware.kryonet.Server;

public class Serveur 
{
    public static int PORT_TCP = 5455;
    public static int PORT_UDP = 5457;

    public Serveur()
    {
        try 
        {
            Server server = new Server();
            server.start();
            server.bind(PORT_TCP, PORT_UDP);
        } catch (Exception e) { e.printStackTrace(); }
    }    
}
