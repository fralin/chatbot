package app.util;

import java.io.BufferedReader;
import java.io.FileReader;

public class Questions {

    public static Question[] qr;

    /**
     *
     * @param filename : nom du fichier contenant les questions et les réponses types
     * @throws Exception
     */

    public static void initQuestion (String filename) throws Exception {

        int len = fileLen(filename);
        qr = new Question[len];

        BufferedReader br = new BufferedReader(new FileReader(filename));
        String ligne = null;
        String[] data;

        for (int i=0; i<len; i++)
        {
            ligne = br.readLine();
            data = ligne.split(";");
            qr[i] = new Question (data[0], data[1]);
        }
        br.close();
    }

    /**
     *
     * @param filename : nom du fichier. Le caractère de séparation est le point virgule
     * @return le nombre de ligne du fichier csv
     * @throws Exception
     */

    private static int fileLen (String filename) throws Exception {
        int count =0;
        BufferedReader br = new BufferedReader(new FileReader(filename));
        while (br.readLine() != null)
            ++count;
        br.close();
        return count;
    }
}
