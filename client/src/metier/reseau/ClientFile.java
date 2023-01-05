package client.src.metier.reseau;

import java.io.*;
import java.net.Socket;

import server.src.Serveur;

public class ClientFile
{

    private static InputStream      in = null;
    private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;

    public ClientFile(String adr)
    {
        try(Socket socket = new Socket(adr,Serveur.PORT_TRANSFERT)) {
            in            = socket.getInputStream();
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

            receiveFile("jeu.xml");
            
            dataOutputStream.close();
            dataInputStream.close();
            socket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void receiveFile(String fileName) throws Exception
    {
        File file = new File(fileName);
        file.createNewFile();
        FileOutputStream out = new FileOutputStream(file);

        
        byte[] bytes = new byte[16*1024];

        int count;
        while ((count = in.read(bytes)) > 0) {
            out.write(bytes, 0, count); 
        }

        out.close();
        in.close();
    }
}