package server.src;

public class Serveur 
{
    public static int PORT_TCP = 5455;
    public static int PORT_UDP = 5457;

    public Serveur()
    {
        Server server = new Server();
    server.start();
    server.bind(54555, 54777);
    }    
}
