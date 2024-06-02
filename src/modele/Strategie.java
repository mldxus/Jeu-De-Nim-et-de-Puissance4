package modele;

import java.util.ArrayList;
import java.util.Random;

public class Strategie {
    public final static int vide = 0;
    public final static int rouge = 1;
    public final static int jaune = 2;

    /**
     * Génération aléatoire des coups de l'IA
     * @param tas
     * @param premierTour
     * @param nb_max
     * @return
     */

    public String CoupIA(Plateau tas, boolean premierTour, int nb_max) {
        int tab[] = tas.getTableau();
        int resultatXor = 0;
        int tasChoisi = 0;
        int nombreAllumettes = 0;
        Random random = new Random();
        ArrayList<String> coupsPossibles = new ArrayList<>();

        if (premierTour) {
            System.out.println("\nC'est à l'ordinateur de jouer");
        }

        if (nb_max != 0) {

            for (int i = 0; i < tab.length; i++) {
                for (int j = 1; j <= Math.min(tab[i], nb_max); j++) {
                    coupsPossibles.add((i + 1) + " " + j);
                }
            }

            int indiceCoup = random.nextInt(coupsPossibles.size());
            return coupsPossibles.get(indiceCoup);
        } else {
            for (int i = 0; i < tab.length; i++) {
                resultatXor = resultatXor ^ tab[i];
            }

            if (resultatXor == 0) {
                for (int i = 0; i < tab.length; i++) {
                    if (tab[i] > 0) {
                        tasChoisi = i + 1;
                        nombreAllumettes = 1;
                        break;
                    }
                }

            } else {
                for (int i = 0; i < tab.length; i++) {
                    int xorTas = resultatXor ^ tab[i];
                    if (xorTas < tab[i]) {
                        tasChoisi = i + 1;
                        nombreAllumettes = tab[i] - xorTas;
                        break;
                    }
                }
            }
            return tasChoisi + " " + nombreAllumettes;
        }
    }

    /**
     * permet à l'ordinateur de jouer un coup
     * @param grille
     * @return le meilleur coup que l'ordinateur peut effectuer
     */


    public static int trouverMeilleurCoup(int[][] grille) {
        int meilleurCoup = -1;
        int meilleurScore = Integer.MIN_VALUE;

        for (int colonne = 0; colonne < grille[0].length; colonne++) {
            if (grille[0][colonne] == vide) {
                int score = Coup.evaluerCoup(grille, colonne, jaune);
                if (score > meilleurScore) {
                    meilleurScore = score;
                    meilleurCoup = colonne;
                }
            }
        }

        return meilleurCoup;
    }

    /**
     * permet à l'ordinateur de jouer un coup avec rotation
     * @param grille
     * @return le meilleur coup que l'ordinateur peut effectuer
     */

    public static int trouverMeilleurCoupRotation(int[][] grille) {
        int meilleurCoup = -1;
        int meilleurScore = Integer.MIN_VALUE;

        for (int colonne = 0; colonne < grille[0].length; colonne++) {
            if (grille[0][colonne] == vide) {
                int score = Coup.evaluerCoupRotation(grille, colonne, jaune);
                if (score > meilleurScore) {
                    meilleurScore = score;
                    meilleurCoup = colonne;
                }
            }
        }

        return meilleurCoup;
    }

    /**
     * Calcule le nombre de jeton de la meme couleur qu'un utilisateur a aligné
     * @param grille
     * @param couleur
     * @return retourne le nombre de jeton maximum aligné par un joueur
     */

    public static int calculerMaxAlignes(int[][] grille, int couleur) {
        int maxAlignes = 0;

        for (int ligne = 0; ligne < grille.length; ligne++) {
            int alignes = 0;
            for (int colonne = 0; colonne < grille[0].length; colonne++) {
                if (grille[ligne][colonne] == couleur) {
                    alignes++;
                } else {
                    alignes = 0;
                }
                maxAlignes = Math.max(maxAlignes, alignes);
            }
        }

        for (int colonne = 0; colonne < grille[0].length; colonne++) {
            int alignes = 0;
            for (int ligne = 0; ligne < grille.length; ligne++) {
                if (grille[ligne][colonne] == couleur) {
                    alignes++;
                } else {
                    alignes = 0;
                }
                maxAlignes = Math.max(maxAlignes, alignes);
            }
        }

        for (int ligne = 0; ligne < grille.length; ligne++) {
            for (int colonne = 0; colonne < grille[0].length; colonne++) {

                int alignes = 0;
                for (int i = 0; ligne + i < grille.length && colonne + i < grille[0].length; i++) {
                    if (grille[ligne + i][colonne + i] == couleur) {
                        alignes++;
                    } else {
                        alignes = 0;
                    }
                    maxAlignes = Math.max(maxAlignes, alignes);
                }

                alignes = 0;
                for (int i = 0; ligne - i >= 0 && colonne + i < grille[0].length; i++) {
                    if (grille[ligne - i][colonne + i] == couleur) {
                        alignes++;
                    } else {
                        alignes = 0;
                    }
                    maxAlignes = Math.max(maxAlignes, alignes);
                }
            }
        }

        return maxAlignes;
    }

    /**
     * Prend la grille et vois si l'ia peut gagner le coup d'apres
     * @param grille
     * @param couleur
     * @return return true si il peut gagner avec le coup d'apres ou faux sinon
     */

    public static boolean peutGagnerAvecCoupSuivant(int[][] grille, int couleur) {

        for (int colonne = 0; colonne < grille[0].length; colonne++) {
            if (grille[0][colonne] == vide) {
                int[][] grilleTemp = new int[grille.length][grille[0].length];
                Plateau.copierGrille(grille, grilleTemp);
                Coup.JouerCoupP4(grilleTemp, colonne, couleur);
                if (Partie.estBienVictoire(grilleTemp, couleur)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Prend la grille et vois si l'ia peut gagner le coup d'apres avec une rotation
     * @param grille
     * @param couleur
     * @return true si il peut gagner avec le coup d'apres ou faux sinon
     */

    public static boolean peutGagnerAvecRotation(int[][] grille, int couleur) {

        int[][] grilleTemp = new int[grille.length][grille[0].length];
        Plateau.copierGrille(grille, grilleTemp);

        Plateau.rotationGrille(grilleTemp, true);
        if (Partie.estBienVictoire(grilleTemp, couleur)) {
            return true;
        }

        Plateau.copierGrille(grille, grilleTemp);
        Plateau.rotationGrille(grilleTemp, false);
        if (Partie.estBienVictoire(grilleTemp, couleur)) {
            return true;
        }

        return false;
    }
}