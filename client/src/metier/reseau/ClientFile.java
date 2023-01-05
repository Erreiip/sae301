package client.src.metier.reseau;

import java.io.*;
import java.net.Socket;

import server.src.Serveur;

public class ClientFile
{
    private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;

    public ClientFile(String adr)
    {
        
        try(Socket socket = new Socket(adr,Serveur.PORT_TRANSFERT)) {
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

            receiveFile("jeu.xml");
            
            dataOutputStream.close();
            dataInputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void receiveFile(String fileName) throws Exception
    {
        int bytesRead;  
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        
        long size = dataInputStream.readLong();     // read file size
        byte[] buffer = new byte[4*1024];  
        while (size > 0 && (bytesRead = dataInputStream.read(buffer, 0, (int)Math.min(buffer.length, size))) != -1)   
        {   
            fileOutputStream.write(buffer, 0, bytesRead);   
            size -= bytesRead;   
        }

        fileOutputStream.close();
        dataInputStream.close();
        dataOutputStream.close();
    }
}