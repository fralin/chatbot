package app.util;

import java.util.Arrays;

/**
 * Cette classe permet de stocker les différentes caractéristiques
 * d'un échange du chatbot
 */

public class Question {

    public String question;
    public String reponse;      // chaine de caractère réponse
    public double[] vecteur;     // question vectorisée

    /**
     *
     * @param reponse : Chaine de caractére à retourner par le chatbot
     * @param question : Chaine de caractère de la question type
     */

    public Question(String question, String reponse) {
        this.question = question;
        this.reponse = reponse;
        this.vecteur = null;
    }

    // Les getters
    public String getQuestion() { return question; }

    public String getReponse() {
        return reponse;
    }

    public double[] getVecteur() {
        return vecteur;
    }

    // Les setters

    public void setVecteur(double[] vecteur) {
        this.vecteur = vecteur;
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", reponse='" + reponse + '\'' +
                ", vecteur=" + Arrays.toString(vecteur) +
                '}';
    }
}