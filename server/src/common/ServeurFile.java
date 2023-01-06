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
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import com.jcraft.jsch.*;

import server.src.Serveur;

public class ServeurFile {


    public ServeurFile(Serveur s) 
    {
        try(ServerSocket serverSocket = new ServerSocket(Serveur.PORT_TRANSFERT)){
            Socket socket = serverSocket.accept();
            
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in  = new BufferedReader( new InputStreamReader(socket.getInputStream()));

            //String m1 = in.readLine();
            
            
            
            socket.close();
            serverSocket.close();
            
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