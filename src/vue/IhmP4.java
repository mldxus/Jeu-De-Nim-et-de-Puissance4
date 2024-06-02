package vue;

import modele.Plateau;

import java.util.Scanner;

public class IhmP4 extends Ihm {


    /**
     *
     * Cette méthode explique les regles du jeu
     */
    public void direRegle() {
        System.out.println("\nBienvenue dans le jeu Puissance 4 ! \n" + "\n" +
                "Vous et votre adversaire placez vos pions tour à tour dans une colonne de la grille.\n" +
                "L'objectif est d'aligner quatre de vos pions horizontalement, verticalement ou en diagonale. \n" +
                "Le premier à réussir remporte la partie. Si la grille est remplie sans vainqueur, c'est une égalité \n");
    }

    public void pasValiderCoup(String j) {
        System.out.println("\nLe couple n'est pas valide \n" + j.toUpperCase() + " tu dois rejouer !");
    }

    /**
     *
     * @param nomJoueur
     * @param couleurJoueur
     *                  Cette méthode permet à un joueur de saisir un coup dans le
     *                  jeu.
     *                  Elle vérifie si la saisie est bien un nombre et si elle est bien
     *                  entre 1 et 7. Si ce n'est pas valide alors elle
     *                  demande une nouvelle saisie.
     * @return coupJouer, la chaine de caractère du coup joué par le joueur
     */
    public String DemanderCoupP4(String nomJoueur, int couleurJoueur) {
        System.out.println("\n" + nomJoueur.toUpperCase() + ", à toi de jouer ! Tu es en "
                + ((couleurJoueur == Plateau.rouge) ? "rouge" : "jaune") + "\n");
        while (true) {
            System.out.print("Entrez le numéro de colonne où placer votre jeton (entre 1 et 7) : ");
            Scanner sc = new Scanner(System.in);
            String coupJouer = sc.nextLine();
            try {
                int colonne = Integer.parseInt(coupJouer);
                if (colonne >= 1 && colonne <= 7) {
                    return coupJouer;
                } else {
                    System.out.println("\nLa colonne doit être un nombre entre 1 et 7. Réessayez.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\nLa saisie n'est pas un entier. Vous devez rejouer.");
            }
        }
    }

    /**
     *
     * @param grille
     *                  Affiche la grille avec les pions de couleur et les batons
     */
    public void afficherGrilleP4(int[][] grille) {
        System.out.println();
        System.out.println("   1   2   3   4   5   6   7");
        System.out.println("-------------------------------");

        for (int[] ligne : grille) {
            System.out.print(" | ");
            for (int cellule : ligne) {
                if (cellule == Plateau.vide) {
                    System.out.print(' ');
                } else if (cellule == Plateau.rouge) {
                    System.out.print("\u001B[31m\u25CF\u001B[37m");// "\u001B[31mO\u001B[37m"
                } else {
                    System.out.print("\u001B[33m\u25CF\u001B[37m");// "\u001B[33mO\u001B[37m"
                }
                System.out.print(" | ");
            }
            System.out.println();
        }
    }

    /**
     * @param rotationActive
     *                  Cette méthode demande si l'utlisateur veut faire une partie avec rotation rotation
     * @return          return true ou false si l'utlisateur choisi de faire une rotation ou non
     */

    public boolean demanderRotation(boolean rotationActive) {

        String rotationActive1 = "vide";
        System.out.println(
                "\nSouhaitez-vous faire une partie avec rotation. Si oui tapez 'O' pour oui , si non tapez 'N' : ");
        while (rotationActive1.compareToIgnoreCase("o") != 0 && rotationActive1.compareToIgnoreCase("n") != 0) {

            Scanner sc = new Scanner(System.in);
            rotationActive1 = sc.nextLine();
        }
        if (rotationActive1.equalsIgnoreCase("o")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param nomJoueur
     *                  Lorsque l'utlisateur demande à faire une rotation
     *                  cette méthode lui demande dans quelle sens il voudra faire la rotation
     * @return          retourne true ou false si le joueur veut faire une rotation à droite ou non
     */

    public boolean sensRotation(String nomJoueur) {
        String sens = "vide";
        System.out.println(
                "\n" + nomJoueur.toUpperCase() + " " + "Choisissez le sens de rotation (droite = d ou gauche = g)\n");
        while (sens.compareToIgnoreCase("d") != 0 && sens.compareToIgnoreCase("g") != 0) {
            Scanner sc = new Scanner(System.in);
            sens = sc.nextLine();
        }
        if (sens.equalsIgnoreCase("d")) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * @param nomJoueur
     *                  Cette méthode demande à l'utilisateur si il veut jouer un coup
     *                  ou si il veut faire une rotation
     * @return          retourne true ou false si le joueur veut faire une rotation
     */

    public boolean DemanderSiRotation(String nomJoueur) {
        String rotation = "vide";
        boolean rt = false;
        System.out.println(
                "\n" + nomJoueur.toUpperCase() + " " + "Voulez vous faire une rotation: 'R' ou jouer un coup :'C' ?\n");
        while (rotation.compareToIgnoreCase("r") != 0 && rotation.compareToIgnoreCase("c") != 0) {
            Scanner sc = new Scanner(System.in);
            rotation = sc.nextLine();
        }
        if (rotation.equalsIgnoreCase("r")) {
            rt = true;
            return rt;
        } else {
            return rt;
        }
    }
}
