import java.util.Arrays;

/**
 * Cette classe permet de stocker les différentes caractéristiques
 * d'un échange du chatbot
 */

public class Question {

    public static int count = 0;    // nombre total de questions crées

    public int index;           // numéro de l'instance de la question
    public String reponse;      // chaine de caractère réponse
    public float[] vecteur;     // question vectorisée

    /**
     *
     * @param reponse : Chaine de caractére à retourner par le chatbot
     * @param vecteur : Question type vectorisée
     */

    public Question(String reponse, float[] vecteur) {
        this.index = count++;
        this.reponse = reponse;
        this.vecteur = vecteur;
    }

    public int getIndex() {
        return index;
    }

    public String getReponse() {
        return reponse;
    }

    public float[] getVecteur() {
        return vecteur;
    }

    @Override
    public String toString() {
        return "Question{" +
                "index=" + index +
                ", reponse='" + reponse + '\'' +
                ", vecteur=" + Arrays.toString(vecteur) +
                '}';
    }
}
