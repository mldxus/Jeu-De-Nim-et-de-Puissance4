package modele;

public class Coup {
    public final static int vide = 0;
    public final static int rouge = 1;
    public final static int jaune = 2;

    /**
     *
     * @param coup
     *             Cette méthode vérifie si un coup entré par l'utilisateur est
     *             valide en extrayant la position m et le nombre d'allumettes n du
     *             coup.
     * @return true ou false si la position est inférieur ou égale à la longueur
     *         du tableau 'tas' et si le nombre d'allumettesest inférieur ou égale
     *         au nombre d'allumettes restantes à la position dans le tableau
     */
    public boolean validerCoup(Plateau tas, String coup, String nomJoueur) {
        int[] tab = tas.getTableau();
        int pos = coup.indexOf(' ');
        int m = Integer.parseInt(coup.substring(0, pos));
        int n = Integer.parseInt(coup.substring(pos + 1));

        if (!nomJoueur.equals(" ordinateur")) {
            if (m >= 1 && m <= tab.length && n <= tab[m - 1]) {
                tab[m - 1] -= n;
                return true;
            } else {
                return false;
            }
        } else {
            if (m >= 1 && m <= tas.length && n >= 1 && n <= tas.getNbAllumettes(m)) {
                tab[m - 1] -= n;
                return true;
            } else {
                return false;
            }
        }
    }


    /**
     *
     * @param grille
     * @param colonne
     * @param couleur
     *             Cette méthode vérifie si la colonne choisi par l'utilisateur est
     *             valide c'est à dire pas rempli
     * @return true ou false si la colonne est rempli
     */
    public static boolean JouerCoupP4(int[][] grille, int colonne, int couleur) {
        if (colonne >= grille[0].length) {
            return false;
        }
        int ligne = grille.length - 1;

        boolean rempli = false;
        while ((!rempli) && grille[ligne][colonne] != vide) {
            if (ligne == 0) {
                rempli = true;
            } else {
                --ligne;
            }
        }
        if (!rempli) {
            grille[ligne][colonne] = couleur;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Vérifie si le coup d'un joueur est valide
     * @param grille
     * @param colonne
     * @param couleur des jetons
     * @return
     */

    public static int evaluerCoup(int[][] grille, int colonne, int couleur) {
        int[][] grilleTemp = new int[grille.length][grille[0].length];
        Plateau.copierGrille(grille, grilleTemp);

        JouerCoupP4(grilleTemp, colonne, couleur);

        if (Partie.estBienVictoire(grilleTemp, couleur)) {
            return 7;
        }

        int adversaire = (couleur == rouge) ? jaune : rouge;
        if (Strategie.peutGagnerAvecCoupSuivant(grilleTemp, adversaire)) {
            return 0;
        }

        int maxAlignesJoueur = Strategie.calculerMaxAlignes(grilleTemp, couleur);

        int maxAlignesAdversaire = Strategie.calculerMaxAlignes(grilleTemp, adversaire);

        if (maxAlignesJoueur >= 4) {
            return 7;
        } else if (maxAlignesAdversaire >= 4) {
            return 6;
        } else if (maxAlignesJoueur >= 3) {
            return 5;
        } else if (maxAlignesAdversaire >= 3) {
            return 4;
        } else if (maxAlignesJoueur >= 2) {
            return 3;
        } else if (maxAlignesAdversaire >= 2) {
            return 2;
        } else {
            return 1;
        }
    }

    /**
     * permet à l'ordinateur de voir si un coup vaut le coup de le faire avec une rotation
     * @param grille
     * @param colonne
     * @param couleur
     * @return le nombre le plus grand que le coup de l'ordinateur permet de faire
     */

    public static int evaluerCoupRotation(int[][] grille, int colonne, int couleur) {
        // Plateau plateau = new Plateau(grille.length);
        int[][] grilleTemp = new int[grille.length][grille[0].length];
        Plateau.copierGrille(grille, grilleTemp);

        JouerCoupP4(grilleTemp, colonne, couleur);

        if (Partie.estBienVictoire(grilleTemp, couleur)) {
            return 7;
        }

        int adversaire = (couleur == rouge) ? jaune : rouge;
        if (Strategie.peutGagnerAvecCoupSuivant(grilleTemp, adversaire)) {
            return 0;
        }

        if (Strategie.peutGagnerAvecRotation(grilleTemp, couleur)) {
            return 8;
        }

        int maxAlignesJoueur = Strategie.calculerMaxAlignes(grilleTemp, couleur);

        int maxAlignesAdversaire = Strategie.calculerMaxAlignes(grilleTemp, adversaire);

        if (maxAlignesJoueur >= 4) {
            return 7;
        } else if (maxAlignesAdversaire >= 4) {
            return 6;
        } else if (maxAlignesJoueur >= 3) {
            return 5;
        } else if (maxAlignesAdversaire >= 3) {
            return 4;
        } else if (maxAlignesJoueur >= 2) {
            return 3;
        } else if (maxAlignesAdversaire >= 2) {
            return 2;
        } else {
            return 1;
        }
    }
}
