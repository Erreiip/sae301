package server.src.common;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
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

            FileReader file = new FileReader(new File(s.getXml().getAbsolutePath()));
            
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in  = new BufferedReader( new InputStreamReader(socket.getInputStream()));

            //String m1 = in.readLine();
            BufferedReader br=new BufferedReader(file);
            int c = 0;             
            while((c = br.read()) != -1) 
            {
                  char character = (char) c;        
                  System.out.println(character);  
            }
            
            
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