package controleur;

import modele.*;
import vue.IhmNim;
import vue.IhmP4;
import vue.InterfaceIhm;

public class ControleurGeneral {
    private InterfaceIhm ihm;
    private Partie partie;


    public ControleurGeneral(InterfaceIhm ihm) {
        this.ihm = ihm;
    }

    public void demarrerJeu() {

        // Afficher les diférents choix de jeux possibles et/ou règles du jeu
        // et demander à l'IHM de choisir le jeu
        boolean continuer = true;

        while (continuer) {
            int choixJeu = this.ihm.choisirJeu();

            // Utiliser le choix pour démarrer le jeu approprié
            switch (choixJeu) {
                case 1:
                    // Démarrer le jeu de Nim
                    IhmNim ihmNim = new IhmNim();
                    ControleurNim controleurNim = new ControleurNim(partie, ihmNim);
                    controleurNim.jouer();
                    break;
                case 2:
                    // Démarrer le jeu de Puissance 4
                    IhmP4 ihmP4 = new IhmP4();
                    ControleurP4 controleurP4 = new ControleurP4(partie, ihmP4);
                    controleurP4.jouer();
                    break;
                case 3:
                    // Quitter
                    continuer = false;
                    break;
                default:
                    ihm.afficherMessage("Choix invalide. Veuillez choisir à nouveau.");
                    break;
            }
        }
        ihm.afficherMessage("\nMerci d'avoir joué !\n");
    }
}

