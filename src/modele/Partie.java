package modele;

public class Partie {
    private Plateau plateau;
    public final static int vide = 0;

    public Partie(int nbLigneNim) {
        plateau = new Plateau(nbLigneNim);

    }

    /**
     * @param grille
     *                Prend la grille est regarde si elle est complétement rempli
     * @return         retourne true ou false si la grille est complètement rempli
     */
    public static boolean estPlein(int[][] grille) {

        for (int cellule : grille[0]) {
            if (cellule == vide) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param grille
     * @param couleurJoueur
     *                Regarde par rapport à la couleur du joueur si il a bien
     *                aligné 4 pions horizontalement, diagonalement, verticalement
     * @return        retourne true ou false si le joueur c'est à dire si il a aligné 4 pions
     */
    public static boolean estBienVictoire(int[][] grille, int couleurJoueur) {

        for (int ligne = 0; ligne < grille.length; ++ligne) {
            for (int colonne = 0; colonne < grille[ligne].length; ++colonne) {
                int couleurCase = grille[ligne][colonne];

                if (couleurCase == couleurJoueur) {

                    for (int i = 0; i <= grille[ligne].length - 4; i++) {
                        if (grille[i][colonne] == couleurJoueur &&
                                grille[i + 1][colonne] == couleurJoueur &&
                                grille[i + 2][colonne] == couleurJoueur &&
                                grille[i + 3][colonne] == couleurJoueur) {
                            return true;
                        }
                    }

                    for (int i = 0; i <= grille[ligne].length - 4; i++) {
                        if (grille[ligne][i] == couleurJoueur &&
                                grille[ligne][i + 1] == couleurJoueur &&
                                grille[ligne][i + 2] == couleurJoueur &&
                                grille[ligne][i + 3] == couleurJoueur) {
                            return true;
                        }
                    }

                    for (int i = 0; i <= grille.length - 4; i++) {
                        for (int j = 0; j <= grille[i].length - 4; j++) {
                            if (grille[i][j] == couleurJoueur &&
                                    grille[i + 1][j + 1] == couleurJoueur &&
                                    grille[i + 2][j + 2] == couleurJoueur &&
                                    grille[i + 3][j + 3] == couleurJoueur) {
                                return true;
                            }
                        }
                    }

                    for (int i = 3; i < grille.length; i++) {
                        for (int j = 0; j <= grille[i].length - 4; j++) {
                            if (grille[i][j] == couleurJoueur &&
                                    grille[i - 1][j + 1] == couleurJoueur &&
                                    grille[i - 2][j + 2] == couleurJoueur &&
                                    grille[i - 3][j + 3] == couleurJoueur) {
                                return true;
                            }
                        }
                    }

                }
            }
        }

        return false;

    }
}
