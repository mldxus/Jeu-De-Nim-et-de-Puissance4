package vue;

import modele.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Ihm implements InterfaceIhm {

    /**
     * Permet de choisir le jeu
     * @return
     */
    public int choisirJeu() {
        Scanner scanner = new Scanner(System.in);
        int choix = 0;
        System.out.println("Bienvenue !\n" + " \nVeuillez choisir un jeu :");

        while (choix < 1 || choix > 3) {
            System.out.println("1. Jeu de Nim");
            System.out.println("2. Puissance 4");
            System.out.println("3. Quitter");

            System.out.print("\nVotre choix : ");
            try {
                choix = scanner.nextInt();
                if (choix < 1 || choix > 3) {
                    System.out.println("\nChoix invalide. Veuillez choisir à nouveau un jeu :");
                }
            } catch (InputMismatchException e) {
                System.out.println("Veuillez entrer un nombre valide.");
                scanner.next();
            }
        }

        return choix;
    }

    /**
     * Permet de choisir si l'on joue contre un joueur ou l'IA
     * @param nbTour
     * @param choix
     * @return
     */

    public int choisirModeJeu(boolean nbTour, int choix) {
        Scanner scanner = new Scanner(System.in);

        if (nbTour) {
            System.out.println("\nSouhaitez-vous jouer contre un autre joueur ou avec un ordinateur ? :");

            while (choix < 1 || choix > 2) {
                System.out.println("1. Jouer contre un humain");
                System.out.println("2. Jouer contre un ordinateur");
                System.out.print("\nVotre choix : ");
                try {
                    choix = scanner.nextInt();
                    if (choix < 1 || choix > 3) {
                        System.out.println("\nChoix invalide. Veuillez choisir à nouveau un jeu :");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Veuillez entrer un nombre valide.");
                    scanner.next();
                }
            }
        }
        return choix;
    }

    /**
     *
     * @param nj
     *           Ce code demande à l'utilisateur de saisir son nom et renvoie le nom
     *           saisi.
     *           Si aucun nom n'est saisi ou si le nom saisi est vide, elle demande
     *           à nouveau au joueur de saisir un nom valide
     * @return nom, le nom saisi
     */
    public String saisirNom(int nj, int jouerContreOrdinateur) {
        String message;
        if (jouerContreOrdinateur == 2) {
            message = "Saisir votre nom : ";
        } else {
            message = "Saisir le nom du joueur " + nj + " : ";
        }

        Scanner sc = new Scanner(System.in);
        String nom = "";
        while (nom.trim().isEmpty()) {
            try {
                System.out.print("\n" + message);
                nom = sc.nextLine();
                if (nom.trim().isEmpty()) {
                    throw new IllegalArgumentException("Le nom ne peut pas être vide");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("\nVous n'avez pas saisi de nom ! Veuillez en saisir un.\n");
            }
        }

        return nom;
    }

    public void afficherMessage(String message) {
        System.out.println(message);
    }

    /**
     * Cette méthode demande à l'utilisateur s'il souhaite rejouer une nouvelle
     * partie
     *
     * @return true or false, si le joueur souhait rejouer ou non
     */
    public Boolean reJouer() {
        String choix = "vide";
        Scanner sc = new Scanner(System.in);
        while (!choix.equalsIgnoreCase("o") && !choix.equalsIgnoreCase("q")) {
            System.out.println("\nSouhaitez-vous rejouer une nouvelle partie ? (O pour oui, Q pour quitter) : ");
            choix = sc.nextLine();
        }
        return choix.equalsIgnoreCase("o");
    }

    /**
     *
     * @param j1
     * @param j2
     * @param nomGagnant
     *                   Ce code affiche un message indiquant que le jeu est
     *                   terminé, puis elle informe le joueur gagnant et affiche le
     *                   nombre de victoires pour chaque joueur.
     *                   Si le jeu se termine par une égalité, elle affiche "ex
     *                   aequo".
     */
    public void FinirJeu(Joueur j1, Joueur j2, String nomGagnant) {
        System.out.println("\nLe jeu est terminé.");
        if (nomGagnant.equalsIgnoreCase("ex aequo")) {
            System.out.println("Vous êtes " + nomGagnant.toUpperCase());
        } else {
            System.out.println("Le gagnant est " + nomGagnant.toUpperCase());
        }
        System.out.println("\n" + j1.getNom().toUpperCase() + " a " + j1.getPartiesGagnees() + " victoire(s).");
        System.out.println(j2.getNom().toUpperCase() + " a " + j2.getPartiesGagnees() + " victoire(s).\n");
    }

    /**
     * Cette méthode détermine le vainqueur en comparant le nombre de parties
     * gagnées des deux joueurs
     *
     * @return gagnant, le nom du joueur ayant remporté le plus de parties ou "ex
     *         aequo" s'il y a une égalité
     */
    public String vainqueur(Joueur j1, Joueur j2) {
        String gagnant;
        if (j1.getPartiesGagnees() > j2.getPartiesGagnees()) {
            gagnant = j1.getNom();
        } else if (j2.getPartiesGagnees() > j1.getPartiesGagnees()) {
            gagnant = j2.getNom();
        } else {
            gagnant = "ex aequo";
        }
        return gagnant;
    }


    @Override
    public void reglePourJouer() {

    }

    @Override
    public int AfficherTas() {
        return 0;
    }

    @Override
    public int jouerNombreMax(int nbLigne) {
        return 0;
    }


    @Override
    public String DemanderCoupNim(String nomJoueur, int nbMax) {
        return null;
    }

    @Override
    public void pasValiderCoup(String nomJoueur) {

    }

    @Override
    public void direRegle() {

    }

    @Override
    public void afficherGrilleP4(int[][] grille) {

    }

    @Override
    public boolean demanderRotation(boolean rotationActive) {
        return false;
    }

    @Override
    public boolean DemanderSiRotation(String nomJoueur) {
        return false;
    }

    @Override
    public boolean sensRotation(String nomJoueur) {
        return false;
    }

    @Override
    public String DemanderCoupP4(String nomJoueur, int couleurJoueur) {
        return null;
    }

}
