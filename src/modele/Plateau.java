package modele;

public class Plateau {
    private int[] tas;
    private int[][] grille;
    int length;
    public final static int vide = 0;
    public final static int rouge = 1;
    public final static int jaune = 2;

    /**
     * @param nbLigne
     *                Lors de la création d'un objet Tas, un tableau d'entiers est
     *                initialisé avec des valeurs croissantes de nombres impairs.
     *                Ces nombres représentent le nombre initial d'allumettes dans
     *                chaque ligne de tas
     */

    public Plateau(int nbLigne) {
        tas = new int[nbLigne];
        for (int i = 0; i < nbLigne; i++) {
            tas[i] = i * 2 + 1;
        }
    }

    /**
     *
     * @return tas renvoi la tableau d'allumettes
     */
    public int[] getTableau() {
        return tas;
    }


    /**
     *
     * @param tasIndex
     * @return le nombre d'allumettes
     */
    public int getNbAllumettes(int tasIndex) {
        if (tasIndex < 1 || tasIndex > tas.length) {
            throw new IllegalArgumentException("Index de tas invalide");
        }
        return tas[tasIndex - 1];
    }

    /**
     * @param tas
     *            Ce code affiche graphiquement le tas à l'aide de barres verticales
     *            représentant les allumettes
     */
    public void afficherTasNim(Plateau tas) {
        System.out.println("\n");
        int tab[] = tas.getTableau();
        for (int i = 0; i < tab.length; i++) {
            System.out.print("ligne " + (i + 1) + "   ");
            for (int j = 0; j < tab[i]; j++) {
                System.out.print("| ");
            }
            System.out.println(" ");
        }
    }

    /**
     * Si l'utilisateur choisit de faire une rotation cette
     * methode va alors
     * faire une rotation à droite ou a gauche selon le choix
     * de l'utilisateur
     * va copier la grille mettre les pions à leur nouvelle
     * place et applique
     * la gravité sur les pions
     *
     * @param grille
     * @param rotationAdroite
     */
    public static void rotationGrille(int[][] grille, boolean rotationAdroite) {
        int[][] grilleRotation = new int[grille[0].length][grille.length];
        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[0].length; j++) {
                if (rotationAdroite) {
                    grilleRotation[j][grille.length - 1 - i] = grille[i][j];
                } else {
                    grilleRotation[grille[0].length - 1 - j][i] = grille[i][j];
                }
            }
        }
        copierGrille(grilleRotation, grille);
        appliquerGravite(grille);
    }

    /**
     * Cette méthode va copier la grille actuelle
     * @param source
     * @param destination
     */

    static void copierGrille(int[][] source, int[][] destination) {
        for (int i = 0; i < source.length; i++) {
            System.arraycopy(source[i], 0, destination[i], 0, source[0].length);
        }
    }

    /**
     * Lors de la rotation et de la copie d'une grille cette méthode
     * va parcourir les colonnes de bas en haut et si la cellule au
     * dessus et vide
     * le pion va être déplacé vers le bas et videra la cellule
     * d'origine
     *
     * @param grille
     */

    private static void appliquerGravite(int[][] grille) {
        for (int j = 0; j < grille[0].length; j++) {
            for (int i = grille.length - 1; i >= 0; i--) {
                if (grille[i][j] == 0) {
                    for (int k = i - 1; k >= 0; k--) {
                        if (grille[k][j] != 0) {
                            grille[i][j] = grille[k][j];
                            grille[k][j] = 0;
                            break;
                        }
                    }
                }
            }
        }
    }


    /**
     * Cette méthode vérifie si toutes les lignes du tas sont vides en calculant la
     * somme de toutes les valeurs dans le tableau tas
     *
     * @return true si la somme est égale à zéro, sinon false
     */
    public boolean estTerminee() {
        int somme = 0;
        int[] plateauTableau = getTableau();
        for (int i = 0; i < plateauTableau.length; i++) {
            somme += plateauTableau[i];
        }
        if (somme == 0) {
            return true;
        } else {
            return false;
        }
    }

}
