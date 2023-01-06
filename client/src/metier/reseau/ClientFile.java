package client.src.metier.reseau;

import java.io.*;
import java.net.Socket;

import server.src.Serveur;

public class ClientFile
{

    public static boolean bPause = false;


    public ClientFile(String adr)
    {
        InputStream in = null;
        OutputStream out = null;


        try(Socket socket = new Socket(adr,Serveur.PORT_TRANSFERT)) {
            
            while ( bPause ) {}

            in = socket.getInputStream();
        
            out = new FileOutputStream("jeu.xml");
    
            byte[] bytes = new byte[16*1024];
    
            int count;
            while ((count = in.read(bytes)) > 0) {
                out.write(bytes, 0, count);
            }
            
            bPause = false;

            out.close();
            in.close();
            socket.close();
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void setBoolean(boolean b) { bPause = true;}
}