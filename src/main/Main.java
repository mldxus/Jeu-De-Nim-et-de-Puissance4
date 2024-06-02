package main;

import controleur.ControleurGeneral;
import vue.*;

public class Main {

    public static void main(String[] args) {
        InterfaceIhm ihm = new Ihm();

        ControleurGeneral controleurGeneral = new ControleurGeneral(ihm);

        controleurGeneral.demarrerJeu();
    }
}
