package server.src.common;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


import server.src.Serveur;

public class ServeurFile {
    private static OutputStream     os = null;
    private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;

    public ServeurFile(Serveur s) 
    {

        try(ServerSocket serverSocket = new ServerSocket(Serveur.PORT_TRANSFERT)){
            Socket clientSocket = serverSocket.accept();

            os = clientSocket.getOutputStream();
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
        dataInputStream.close();
        dataOutputStream.close();

        byte[] mybytearray = new byte[(int) file.length()];  
           
        FileInputStream fis = new FileInputStream(file);  
        BufferedInputStream bis = new BufferedInputStream(fis);  
        //bis.read(mybytearray, 0, mybytearray.length);  
           
        DataInputStream dis = new DataInputStream(bis);     
        dis.readFully(mybytearray, 0, mybytearray.length);  
               
        dataOutputStream.writeLong(mybytearray.length);     
        dataOutputStream.write(mybytearray, 0, mybytearray.length);     
        dataOutputStream.flush();  
           
        //Sending file data to the server  
        os.write(mybytearray, 0, mybytearray.length);  
        os.flush();  
    }
}