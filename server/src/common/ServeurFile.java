package server.src.common;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.ServerSocket;
import java.net.Socket;


import server.src.Serveur;

public class ServeurFile {
    private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;

    public ServeurFile(Serveur s) 
    {
        System.out.println("serveur : lancé");

        try(ServerSocket serverSocket = new ServerSocket(Serveur.PORT_TRANSFERT)){
            Socket clientSocket = serverSocket.accept();

            dataInputStream = new DataInputStream(clientSocket.getInputStream());
            dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());

            sendFile(s.getXml().getAbsolutePath());

            dataInputStream.close();
            dataOutputStream.close();
            clientSocket.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void sendFile(String path) throws Exception{
        File file = new File(path);
        FileInputStream fileInputStream = new FileInputStream(file);
        
        dataOutputStream.writeLong(file.length());  

        int count;
        byte[] buffer = new byte[8192]; // or 4096, or more
        while ((count = dataInputStream.read(buffer)) > 0)
        {
            dataOutputStream.write(buffer, 0, count);
        }

        fileInputStream.close();
    }
}