package modele;

public class Joueur {

    private String nom;
    private int partiesGagnees;

    public Joueur(String nom) {
        this.nom = nom;
        partiesGagnees = 0;
    }

    public String getNom() {
        return nom;
    }

    public int getPartiesGagnees() {
        return partiesGagnees;
    }

    public void setPartiesGagnees(int sc) {
        partiesGagnees = partiesGagnees + sc;
    }

}
