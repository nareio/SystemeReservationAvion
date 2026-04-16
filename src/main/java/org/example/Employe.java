package org.example;

import java.util.Random;

public abstract class Employe extends Personne{

    private final int NumeroEmploye;
    private String dateEmbauche;

    public Employe(String nom, String adresse, String contact, String dateEmbauche) {
        super(nom, adresse, contact);
        this.NumeroEmploye = new Random().nextInt(1_000_000);
        this.dateEmbauche = dateEmbauche;
    }

    public String getDateEmbauche() {
        return dateEmbauche;
    }

    public int getNumeroEmploye() {
        return NumeroEmploye;
    }

    public void setDateEmbauche(String dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    public abstract String obtenirRole();

    //affecte dans le vol donné l'employé appelant la méthode
    //différent traitement si pilote ou PersonnelCabine
    public abstract void affecterVol(Vol vol);

    //donne les informations sur le vol spécifié
    public void obtenirVol(Vol vol){
        System.out.println("informations sur le vol demandé : "+vol.toString());
    }

}
