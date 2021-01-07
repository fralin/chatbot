import java.net.*;
import java.io.*;

public class Serveur extends ServerSocket {


    private static int nbClient = 0;       /* POur connaitre le nombre de clients connectes */
    private final static int port = 12000; /* Port d'écoute */

    /* Constructeur */
    public Serveur() throws IOException {
        super(port);
        System.out.println("[Serveur] : Serveur sur " + (port));
    }

    public void execute() throws IOException {
        Socket connection;
        while (true) {
            System.out.println("[Serveur]:  en attente de connexion");
            connection = accept();
            String c_ip = connection.getInetAddress().toString();
            int c_port = connection.getPort();
            System.out.format("[Serveur] : Arr. Client IP %s sur %d\n", c_ip, c_port);
            System.out.format("[Serveur ]: Creation du thread T_%d\n", c_port);

            System.out.format("[Serveur]: %d client(s) actifs\n",++nbClient);
            new Thread(new ServiceClient(connection, "T_" + c_port)).start();
        }
    }
    public static void main(String[] args) throws Exception {
        System.out.println("Initialisation du dictionnaire");
        Csv2map.initDict();
        System.out.println("ChatBot Ready ...");
        Serveur connectionManager = new Serveur();
        connectionManager.execute();
    }

}
