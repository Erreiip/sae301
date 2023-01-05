package server.src.common;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.jcraft.jsch.*;

import server.src.Serveur;

public class ServeurFile {
    private static OutputStream     out = null;
    private static InputStream      in = null;

    private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;

    public ServeurFile(Serveur s) 
    {
        try(ServerSocket serverSocket = new ServerSocket(Serveur.PORT_TRANSFERT)){
            Socket clientSocket = serverSocket.accept();

            in  = clientSocket.getInputStream();
            out = clientSocket.getOutputStream();
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

        byte[] bytes = new byte[16 * 1024];

        InputStream in = new FileInputStream(file);
        
        int count;
        while ((count = in.read(bytes)) > 0) 
        {
            out.write(bytes, 0, count);
        }

        in.close();
        out.close();
    }
}