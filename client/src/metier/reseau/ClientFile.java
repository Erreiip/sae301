package client.src.metier.reseau;

import java.io.*;
import java.net.Socket;

import server.src.Serveur;

public class ClientFile
{

    public static boolean bPause = false;


    public ClientFile(String adr)
    {
        try(Socket socket = new Socket(adr, Serveur.PORT_TRANSFERT)) {
            
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
    

            String ligne;
            int nbLignes = Integer.parseInt(in.readLine());
            String[] tabLigne = new String[nbLignes];

            int cpt = 0;
            while ( (ligne = in.readLine()) != null && cpt < nbLignes)
            {
                tabLigne[cpt++] = ligne;
            }

            out.println();
            out.close();
            in.close();
            socket.close();

            FileWriter fileWriter = new FileWriter("./jeu.xml");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            
            for (cpt = 0; cpt < nbLignes; cpt++)
            {
                printWriter.println(tabLigne[cpt]);
            }


            printWriter.close();
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void setBoolean(boolean b) { bPause = true;}
}