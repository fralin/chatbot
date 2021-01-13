package app.util;

import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;


public class ServiceClient  implements Runnable{
    //String to finish the communication  ici c est ctrl-d
    //private static final  	String Finish="end";

    private final String Finish=""+(char) 4;
    private  Socket connection;
    private  String id;

    private BufferedReader flux_entrant=null;
    private PrintWriter flux_sortant =null;

    /**
     *
     * @param connection
     * @param id
     */
    public ServiceClient(Socket connection, String id)
    {
        this.connection= connection;
        this.id=id;
        System.out.format("Thread T__%s créé pour traiter la connection\n",id);
    }

    /**
     *
     */
    private  void terminer(){
        try{
            if (connection != null)
            {
                System.out.format("Terminaison pour %s\n", id);
                flux_sortant.println("Bye");
                connection.close();
            }
        }
        catch (IOException e) {
            System.out.format("Terminaison pour %s\n", id);
            e.printStackTrace();
        }
    }

    /**
     *
     */
    public  void run(){

        try{
            InputStreamReader isr = new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8);
            flux_entrant = new BufferedReader(isr) ; // file d'entrée
            // flux de sortie en mode autoflush
            flux_sortant = new PrintWriter(connection.getOutputStream() , true);
            String c_ip = connection.getInetAddress().toString() ;
            int c_port= connection.getPort();
            System.out.format("[%s] client admis IP %s  sur le port %d\n", id,c_ip, c_port);
            flux_sortant.format("[%s] : Hello %s  sur le port %d, \n" ,  id, c_ip, c_port );
        }
        catch (Exception e1) {
            System.out.println("Erreur d initialisation") ;e1.printStackTrace();}

        String  message_lu = "";
        int line_num =0 ;
        // Fin de l initialisation

        // Boucle principale //
        while ( true  )
        {
            try {
                message_lu = flux_entrant.readLine();
            }
            catch (IOException ioe) {
                ;
            }
            if (message_lu == null){
                System.out.println( "Client deconnecté, je termine\n" )  ;
                terminer();
                return; }
            System.out.format( "%s [line_%d]--> [%s]]\n", id,line_num, message_lu);
            if (message_lu.contains(Finish) ){
                System.out.format ("[%s] :  [%s] recu, Transmission terminées\n",id,message_lu);
                flux_sortant.println("Fermeture de la connexion");
                terminer();
                return;
            }
            line_num++;
            flux_sortant.format("\n%d :  %s\n>>>",line_num, Chatbot.reponse(message_lu));
        }

    }
}