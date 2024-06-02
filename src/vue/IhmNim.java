package vue;

import modele.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class IhmNim extends Ihm {

    public void reglePourJouer() {
        System.out.println(
                "Bienvenue dans le jeu de Nîm ! \n"
                        + "\nRègle du Jeu : Le jeu de Nim consiste à disposer les unes à côtés des autres des allumettes, formant un ou plusieurs tas. Les joueurs vont, chacun leur tour, prendre un certain nombre d\'allumettes sur une ligne non vide. Le joueur gagnant est celui qui prendra la dernière allumette.\n");
    }

    /**
     * Ce code demande au joueur de saisir un nombre de tas entre 1 et 9 et retourne
     * la valeur saisie
     *
     * @return tas, le nombre de tas saisi
     */

    public int AfficherTas() {
        Scanner sc = new Scanner(System.in);
        int tas;
        while (true) {
            try {
                System.out.print("\nSaisir un nombre de tas entre 1 et 9 : ");
                tas = Integer.parseInt(sc.nextLine());

                if (tas >= 1 && tas <= 9) {
                    break;
                } else {
                    System.out.println("\nVeuillez saisir un nombre entre 1 et 9.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\nVeuillez saisir un nombre entier.");
            }
        }
        return tas;
    }

    /**
     *
     * @param nomJoueur
     * @param nbMax
     *                  Cette méthode permet à un joueur de saisir un coup dans le
     *                  jeu.
     *                  Elle vérifie si le nombre d'allumettes saisi respecte une
     *                  limite fixée par nbMax. Si la limite est dépassée, elle
     *                  demande une nouvelle saisie.
     * @return coupJouer, la chaine de caractère du coup joué par le joueur
     */

    public String DemanderCoupNim(String nomJoueur, int nbMax) {
        String coupJouer;
        System.out.println("\n" + nomJoueur.toUpperCase() + " à toi de jouer ! \n");
        System.out.print(
                "Entrez le numéro du tas et le nombre d'allumettes à retirer (sous la forme 'tas allumettes') : ");
        Scanner sc = new Scanner(System.in);
        coupJouer = sc.nextLine();
        try {
            int coup = Integer.parseInt(coupJouer.substring(2, 3));
            if (nbMax != 0) {
                while (coup > nbMax) {
                    System.out.println("\nVous n'avez pas respecté la limite qui est de " + nbMax + " allumettes");
                    System.out.print(
                            "\nEntrez le numéro du tas et le nombre d'allumettes à retirer (sous la forme 'tas allumettes') : ");
                    coupJouer = sc.nextLine();
                    coup = Integer.parseInt(coupJouer.substring(2, 3));
                }
            }
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            System.out.println(
                    "\nLe format de la saisie est invalide. Veuillez entrer une saisie valide sous la forme 'tas allumettes'");
            coupJouer = DemanderCoupNim(nomJoueur, nbMax);
        }
        return coupJouer;
    }

    public void pasValiderCoup(String j) {
        System.out.println("\nLe couple n'est pas valide \n" + j.toUpperCase() + " tu dois rejouer !");
    }

    /**
     * @param nbLigne
     *                Ce code permet à l'utilisateur de choisir s'il souhaite jouer
     *                avec un nombre maximum d'allumettes à retirer par tour,
     *                puis saisit ce nombre si oui, en vérifiant que la saisie est
     *                valide. Sinon, il retourne 0
     * @return nb_max, le nombre maximum d'allumettes à retirer ou 0
     */

    public int jouerNombreMax(int nbLigne) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nSouhaitez-vous jouer avec un nombre maximum d'allumettes à retirer ? ");
        String choix = "";
        while (!choix.equals("o") && !choix.equals("n")) {
            System.out.print(" (O pour oui, N pour non)  : ");
            Scanner s = new Scanner(System.in);
            choix = s.nextLine();
        }
        if (choix.equals("o")) {
            int nb_max = 0;
            boolean valide = false;
            while (!valide) {
                try {
                    System.out.print("Combien ? : ");
                    nb_max = Integer.parseInt(scanner.nextLine());
                    if (nb_max > 0 && nb_max <= nbLigne) {
                        valide = true;
                    } else {
                        System.out.println("\nLe nombre doit être entre 1 et " + nbLigne);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("\nVeuillez saisir un nombre entier !");
                }
            }
            return nb_max;
        } else {
            return 0;
        }
    }



}
