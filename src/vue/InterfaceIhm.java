package vue;

import modele.*;

public interface InterfaceIhm {
    int choisirJeu();

    int choisirModeJeu(boolean nbTour, int choix);

    String saisirNom(int nj, int jouerContreOrdinateur);

    void afficherMessage(String message);

    Boolean reJouer();

    void FinirJeu(Joueur j1, Joueur j2, String nomGagnant);

    String vainqueur(Joueur j1, Joueur j2);

    void reglePourJouer();

    int AfficherTas();

    int jouerNombreMax(int nbLigne);

    String DemanderCoupNim(String nomJoueur, int nbMax);

    void pasValiderCoup(String nomJoueur);

    void direRegle();

    void afficherGrilleP4(int[][] grille);

    boolean demanderRotation(boolean rotationActive);

    boolean DemanderSiRotation(String nomJoueur);

    boolean sensRotation(String nomJoueur);

    String DemanderCoupP4(String nomJoueur, int couleurJoueur);

}
