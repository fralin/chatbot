package app.util;

import java.util.HashMap;
import java.lang.Math;


public class Chatbot {

    public static final int SIZE = 200; // Dimension des vecteurs

    public static boolean init = false;

    /**
     * Fonction utilisée par le serveur pour interoger le bot
     * @param phrase
     * @return Revoit la réponse du bot au client
     */

    public static String reponse(String phrase) {
        // à la première réponse on initialise les réponse
        if (!init) {
            init = true;
            System.out.println("Initialialisation réponses ...");
            for (Question q : Questions.qr) {
                q.setVecteur(vectorise(q.getQuestion(), Csv2map.dictionnaire));
            }
        }
        double[] vecteur = vectorise(phrase, Csv2map.dictionnaire);
        return "[Chatbot] ->" + analyse(vecteur);
    }

    /**
     * Fait la somme des vecteurs v1 et v2
     * @param v1 un vecteur de double
     * @param v2 un vecteur de double
     * @return vecteur v1+v2
     */
    public static double[] sommeVecteurs (double[] v1, double[] v2) {
        double[] result = new double[v1.length];
        for (int i=0; i<v1.length; i++) {
            result[i] = v1[i]+v2[i];
        }
        return result;
    }

    /**
     * Calcul le produit scalaire de deux vecteurs
     * Permet de déterminer la distance entre deux vecteur
     * @param v1 un vecteur de double
     * @param v2 un vecteur de double
     * @return le produit scalaire
     */

    public static double norme2 (double[] v1, double[] v2) {

        double result = 0.0f;

        for (int i=0;i<v1.length;i++)
            result += v1[i]*v2[i];
        return  result;
    }

    /**
     * Normalisation du vecteur
     */

    public static void normalise(double[] v) {
        double norm = Math.sqrt(norme2(v,v));
        for (int i=0; i<v.length;i++)
            v[i]/=norm;
    }

    /**
     * Initialisation d'un vecteur nul de double
     * @param dim dimension du vecteur
     * @return un vecteur nul de dimension dim
     */

    public static double[] vecteur_null (int dim) {

        double[] vecteur = new double[dim];
        for (int i=0;i<dim; i++) {
            vecteur[i]=0.0;
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

    public static double[] vectorise (String phrase, HashMap<String, double[]> dictionnaire) {
        double[] v = vecteur_null(SIZE);
        String[] mots = phrase.toLowerCase().split(" ");
        for (String mot : mots)
            if (dictionnaire.containsKey(mot)) {
                System.out.println(mot);
                v = sommeVecteurs(v,dictionnaire.get(mot));
            }
        normalise(v);
        return v;
    }

    public static String analyse (double[] v) {
        Question reponse = Questions.qr[0];
        double norme;
        double normeMax = norme2(Questions.qr[0].getVecteur(), v);
        for (Question ref : Questions.qr) {
            norme = norme2(ref.getVecteur(),v);
            if (normeMax < norme ) {
                reponse = ref;
                normeMax = norme;
            }
        }
        System.out.println("norme max : "+normeMax);
        return (normeMax < 0.5)? "Désolé, je ne vous ai pas compris" :reponse.getReponse();
    }
}
