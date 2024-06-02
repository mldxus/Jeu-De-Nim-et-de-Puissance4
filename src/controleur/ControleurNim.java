package controleur;

import modele.*;
import vue.InterfaceIhm;

public class ControleurNim {
    private InterfaceIhm ihmNim;
    private Joueur j1, j2;
    private int nbLigne, nb_max, choix = 0;
    private boolean qjm, partie = true;
    private String coupJouer, gagnant;
    private boolean premierTour = true;

    /**
     *
     * @param ihmNim
     *            Ce constructeur initialise un contrôleur pour le jeu de Nim en
     *            demandant les paramètres de jeu à l'utilisateur et en initialisant
     *            les joueurs avec leurs noms respectifs
     */
    public ControleurNim(Partie partie, InterfaceIhm ihmNim) {
        this.ihmNim = ihmNim;
        initJoueurs();
        initJeu();
    }

    /**
     * Initialisation du jeu de Nim
     */

    private void initJeu() {
        qjm = true;
        ihmNim.reglePourJouer();
        nbLigne = ihmNim.AfficherTas();
        nb_max = ihmNim.jouerNombreMax(nbLigne);
    }

    /**
     * Initialisation des joueurs pour le jeu de Nim
     */

    private void initJoueurs() {
        choix = ihmNim.choisirModeJeu(true, choix);
        if (choix == 1) {
            j1 = new Joueur(ihmNim.saisirNom(1, 1));
            j2 = new Joueur(ihmNim.saisirNom(2, 1));
        } else {
            j1 = new Joueur(ihmNim.saisirNom(1, 2));
            j2 = new Joueur("Ordinateur");
        }
    }

    /**
     * Boucle du jeu de Nim
     */
    public void jouer() {
        while (partie) {
            Plateau tas = new Plateau(nbLigne);
            Partie etatTas = new Partie(nbLigne);
            Strategie strat = new Strategie();
            Coup coup = new Coup();
            while (!tas.estTerminee()) {
                tas.afficherTasNim(tas);
                String nomJoueur = qjm ? j1.getNom() : j2.getNom();

                if (choix == 2) {
                    if (nomJoueur.equals(j1.getNom())) {
                        coupJouer = coupJoueur(nomJoueur, tas, coup);
                    } else {
                        coupJouer = strat.CoupIA(tas, premierTour, nb_max);
                        while (!coup.validerCoup(tas, coupJouer, nomJoueur)) {
                            coupJouer = strat.CoupIA(tas, false, nb_max);
                        }
                    }

                } else {
                    coupJouer = coupJoueur(nomJoueur, tas, coup);
                }

                if (tas.estTerminee()) {
                    if (qjm) {
                        j1.setPartiesGagnees(1);
                    } else {
                        j2.setPartiesGagnees(1);
                    }
                }
                qjm = !qjm;
            }
            gagnant = ihmNim.vainqueur(j1, j2);
            partie = ihmNim.reJouer();
            if (partie) {
                nb_max = ihmNim.jouerNombreMax(nbLigne);
            } else {
                ihmNim.FinirJeu(j1, j2, gagnant);
            }
        }
    }

    /**
     * Permet à un joueur de jouer un coup en vérifiant si celui ci est valide
     * @param nomJoueur
     * @param tas
     * @param coup
     * @return
     */

    private String coupJoueur(String nomJoueur, Plateau tas, Coup coup) {
        String coupJouer = ihmNim.DemanderCoupNim(nomJoueur, nb_max);
        while (!coup.validerCoup(tas, coupJouer, nomJoueur)) {
            ihmNim.pasValiderCoup(nomJoueur);
            coupJouer = ihmNim.DemanderCoupNim(nomJoueur, nb_max);
        }
        return coupJouer;
    }

}
