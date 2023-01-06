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

import client.src.metier.reseau.ClientFile;
import server.src.Serveur;

public class ServeurFile {


    public ServeurFile(Serveur s) 
    {
        try(ServerSocket serverSocket = new ServerSocket(Serveur.PORT_TRANSFERT)){
            Socket socket = serverSocket.accept();

            File file = new File(s.getXml().getAbsolutePath());

            byte[] bytes = new byte[16 * 1024];

            InputStream in = new FileInputStream(file);
            OutputStream out = socket.getOutputStream();
            
            int count;
            while ((count = in.read(bytes)) > 0) {
                out.write(bytes, 0, count);
            }

            ClientFile.setBoolean(false);
            
            out.close();
            in.close();
            socket.close();
            
            while (true )
            {
                if ( socket.isClosed() ) 
                {
                    socket.close();
                    serverSocket.close();
                }
            }

        } catch (Exception e){
            e.printStackTrace();
        }

    }
}