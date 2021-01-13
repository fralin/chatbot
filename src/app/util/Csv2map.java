package app.util;

import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;

public class Csv2map {
    public static int VECTEUR_SIZE = 200;
    public static HashMap<String, float []> dictionnaire = new HashMap<String, float[]>();

    public static void initDict () throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("dictionnaire.csv"));
        String ligne = null;
        String[] data;
        // Lecture de la première ligne

        br.readLine();

        while ((ligne = br.readLine()) != null)
        {

            data = ligne.split(" ");
            float[] vecteur = new float[VECTEUR_SIZE];
            for (int i=0; i<VECTEUR_SIZE; i++) {
                vecteur[i] = Float.parseFloat(data[i+1]);
            }
            dictionnaire.put(data[0], vecteur);
        }
        br.close();
    }


}
