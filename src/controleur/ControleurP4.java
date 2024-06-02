package controleur;

import modele.*;
import vue.InterfaceIhm;

public class ControleurP4 {

    private InterfaceIhm ihmP4;
    private Joueur j1;
    private Joueur j2;
    private boolean partie = true;
    private String gagnant;
    private boolean rotationActive;
    private int resteRotationJ1 = 4;
    private int resteRotationJ2 = 4;
    private int ordi;
    boolean rotationEffectuee = false;

    /**
     *
     * @param ihmP4
     *            Ce constructeur initialise un contrôleur pour le jeu du puissance 4 en
     *            demandant les paramètres de jeu à l'utilisateur et en initialisant
     *            les joueurs avec leurs noms respectifs
     */
    public ControleurP4(Partie partie, InterfaceIhm ihmP4) {
        this.ihmP4 = ihmP4;
        initJoueurs();
        ihmP4.direRegle();

    }

    /**
     * Initialisation des joueurs pour le jeu de P4
     */

    private void initJoueurs() {
        ordi = ihmP4.choisirModeJeu(true, ordi);
        if (ordi == 1) {
            j1 = new Joueur(ihmP4.saisirNom(1, ordi));
            j2 = new Joueur(ihmP4.saisirNom(2, ordi));
        } else {
            j1 = new Joueur(ihmP4.saisirNom(1, ordi));
            j2 = new Joueur("Ordinateur");
        }
    }

    /**
     * Boucle du jeu de P4
     */

    public void jouer() {
        int[][] grille = new int[7][7];
        ihmP4.afficherGrilleP4(grille);

        int couleurJoueur = Plateau.rouge;

        while (partie) {
            if (ihmP4.demanderRotation(rotationActive)) {
                jouerCoupAvecRotation(couleurJoueur, grille);
            } else {
                jouerCoupSansRotation(couleurJoueur, grille);
            }
        }

        gagnant = ihmP4.vainqueur(j1, j2);
        partie = ihmP4.reJouer();

        if (!partie) {
            ihmP4.FinirJeu(j1, j2, gagnant);
        } else {
            jouer();
        }
    }

    /**
     * Jouer un coup avec rotation.
     * @param couleurJoueur Truc
     * @param grille Machin
     */
    private void jouerCoupAvecRotation(int couleurJoueur, int[][] grille) {
        boolean gagne = false;
        resteRotationJ2 = 4;
        resteRotationJ1 = 4;

        do {
            // boolean rotationEffectuee = false;
            String nomJoueur = (couleurJoueur == Plateau.rouge) ? j1.getNom() : j2.getNom();
            if (ordi == 2 && couleurJoueur == Plateau.jaune) {
                int coupJoue = Strategie.trouverMeilleurCoupRotation(grille);
                if (coupJoue == -1) {
                    rotationEffectuee = true;
                    Plateau.rotationGrille(grille, true);
                    ihmP4.afficherGrilleP4(grille);
                    ihmP4.afficherMessage("Ordinateur a effectué une rotation");
                } else {
                    rotationEffectuee = false;
                    Coup.JouerCoupP4(grille, coupJoue, couleurJoueur);
                    ihmP4.afficherGrilleP4(grille);
                    ihmP4.afficherMessage("\n Ordinateur a joué dans la colonne" + " " + (coupJoue + 1) + ".");

                }
                gagne = Partie.estBienVictoire(grille, couleurJoueur);
            } else {
                if (ihmP4.DemanderSiRotation(nomJoueur)) {
                    ihmP4.afficherMessage("Il vous reste :" + " "
                            + (couleurJoueur == Plateau.rouge ? resteRotationJ1-- : resteRotationJ2--) + " "
                            + "rotations" + " " + nomJoueur); // FIXME aide
                    if ((couleurJoueur == Plateau.rouge ? resteRotationJ1 : resteRotationJ2) >= 0) {
                        if (ihmP4.sensRotation(nomJoueur)) {
                            ihmP4.afficherMessage("La rotation du jeu à droite à été fait \n");
                            int i = couleurJoueur == Plateau.rouge ? resteRotationJ1 - 1 : resteRotationJ2 - 1;
                            Plateau.rotationGrille(grille, true);// FIXME mis true au lieu de false
                            ihmP4.afficherGrilleP4(grille);
                            gagne = Partie.estBienVictoire(grille, couleurJoueur);
                        } else {
                            ihmP4.afficherMessage("\n La rotation du jeu à gauche à été éffectué");
                            int i = couleurJoueur == Plateau.rouge ? resteRotationJ1-- : resteRotationJ2--;
                            Plateau.rotationGrille(grille, false); // FIXME mis false au lieu de true
                            ihmP4.afficherGrilleP4(grille);
                            gagne = Partie.estBienVictoire(grille, couleurJoueur);
                        }
                    } else {
                        ihmP4.afficherMessage("Vous ne pouvez plus faire de rotation\n");
                        String coupJoue = ihmP4.DemanderCoupP4(nomJoueur, couleurJoueur);
                        Coup.JouerCoupP4(grille, Integer.parseInt(coupJoue) - 1, couleurJoueur);

                        ihmP4.afficherGrilleP4(grille);
                        gagne = Partie.estBienVictoire(grille, couleurJoueur);

                    }
                    if (couleurJoueur == Plateau.rouge) {
                        couleurJoueur = Plateau.jaune;
                    } else {
                        couleurJoueur = Plateau.rouge;
                    }
                } else {
                    String coupJoue = ihmP4.DemanderCoupP4(nomJoueur, couleurJoueur);
                    Coup.JouerCoupP4(grille, Integer.parseInt(coupJoue) - 1, couleurJoueur);

                    ihmP4.afficherGrilleP4(grille);
                    gagne = Partie.estBienVictoire(grille, couleurJoueur);
                }
            }
            if (couleurJoueur == Plateau.rouge) {
                couleurJoueur = Plateau.jaune;
            } else {
                couleurJoueur = Plateau.rouge;
            }

        } while (!gagne && !Partie.estPlein(grille));

        if (gagne) {
            if (couleurJoueur == Plateau.rouge) {
                j2.setPartiesGagnees(1);
                ihmP4.afficherMessage("\nLe gagnant est" + " " + j2.getNom() + "\n");
            } else {
                j1.setPartiesGagnees(1);
                ihmP4.afficherMessage("\nLe gagnant est" + " " + j1.getNom() + "\n");
            }
        } else {
            ihmP4.afficherMessage("\n “partie nulle, personne n'a gagné” ");
        }
    }

    /**
     * Permet de jouer sans rotation
     * @param couleurJoueur
     * @param grille
     */
    private void jouerCoupSansRotation(int couleurJoueur, int[][] grille) {

        boolean gagne = false;

        do {
            String nomJoueur = (couleurJoueur == Plateau.rouge) ? j1.getNom() : j2.getNom();
            if (ordi == 2 && couleurJoueur == Plateau.jaune) {
                int coupJoue = Strategie.trouverMeilleurCoup(grille);
                Coup.JouerCoupP4(grille, coupJoue, couleurJoueur); // FIXME enlever integerParseint
                // //Integer.parseInt(coupJoue)
                ihmP4.afficherGrilleP4(grille);
                gagne = Partie.estBienVictoire(grille, couleurJoueur);
                ihmP4.afficherMessage("\n L'ordinateur a joué dans la colonne" + " " + (coupJoue + 1) + ".");
            } else {

                String coupJoue = ihmP4.DemanderCoupP4(nomJoueur, couleurJoueur);
                Coup.JouerCoupP4(grille, Integer.parseInt(coupJoue) - 1, couleurJoueur);
                ihmP4.afficherGrilleP4(grille);
                gagne = Partie.estBienVictoire(grille, couleurJoueur);
            }
            if (couleurJoueur == Plateau.rouge) {
                couleurJoueur = Plateau.jaune;
            } else {
                couleurJoueur = Plateau.rouge;
            }
        } while (!gagne && !Partie.estPlein(grille));

        if (gagne) {
            if (couleurJoueur == Plateau.rouge) {
                j2.setPartiesGagnees(1);
                ihmP4.afficherMessage("\nLe gagnant est" + " " + j2.getNom() + "\n");
            } else {
                j1.setPartiesGagnees(1);
                ihmP4.afficherMessage("\nLe gagnant est" + " " + j1.getNom() + "\n");
            }
        } else {
            ihmP4.afficherMessage("\n “partie nulle, personne n'a gagné” ");
        }
    }
}
