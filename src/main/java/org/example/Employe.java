package org.example;

public abstract class Employe extends Personne{

    private int NumeroEmploye;
    private String dateEmbauche;

    public Employe(String nom, String adresse, Personne contact, int numeroEmploye, String dateEmbauche) {
        super(nom, adresse, contact);
        this.NumeroEmploye = numeroEmploye;
        this.dateEmbauche = dateEmbauche;
    }

    public abstract String obtenirRole();


}
