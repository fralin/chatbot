package app.util;

import java.util.Arrays;
import java.util.HashMap;


public class Chatbot {

    public static int SIZE = 200; // Dimension des vecteurs

    /**
     * Fonction utilisée par le serveur pour interoger le bot
     * @param phrase
     * @return Revoit la réponse du bot au client
     */

    public static String reponse(String phrase) {
        float[] vecteur = vectorise(phrase, Csv2map.dictionnaire);
        return "[Chatbot] ->" + Arrays.toString(vecteur);
    }

    /**
     * Fait la somme des vecteurs v1 et v2
     * @param v1 un vecteur de float
     * @param v2 un vecteur de float
     * @return vecteur v1+v2
     */
    public static float[] sommeVecteurs (float[] v1, float[] v2) {
        float[] result = new float[v1.length];
        for (int i=0; i<v1.length; i++) {
            result[i] = v1[i]+v2[i];
        }
        return result;
    }

    /**
     * Calcul le produit scalaire de deux vecteurs
     * Permet de déterminer la distance entre deux vecteur
     * @param v1 un vecteur de float
     * @param v2 un vecteur de float
     * @return le produit scalaire
     */

    public static float norme2 (float[] v1, float[] v2) {

        float result = 0.0f;

        for (int i=0;i<v1.length;i++)
            result += v1[i]*v2[i];
        return  result;
    }

    /**
     * Initialisation d'un vecteur nul de float
     * @param dim dimension du vecteur
     * @return un vecteur nul de dimension dim
     */

    public static float[] vecteur_null (int dim) {

        float[] vecteur = new float[dim];
        for (int i=0;i<dim; i++) {
            vecteur[i]=0.0f;
        }
        return vecteur;
    }

    /**
     * Vectorise une phrase à partir d'une Map dictionnaire
     * @param phrase un String contenant la phrase à vectoriser
     * @param dictionnaire une map dont les clés sont les mots du dictionnaire
     *                     et les valeurs sont les vecteurs correspondants
     * @return retourne le vecteur de la phrase correspondant au vecteur somme de
     * tous les vecteurs des mots constituant la phrase
     */

    public static float[] vectorise (String phrase, HashMap<String, float[]> dictionnaire) {
        float[] v = vecteur_null(SIZE);
        String[] mots = phrase.toLowerCase().split(" ");
        for (String mot : mots)
            if (dictionnaire.containsKey(mot)) {
                System.out.println(mot);
                v = sommeVecteurs(v,dictionnaire.get(mot));
            }
        return v;
    }
}
