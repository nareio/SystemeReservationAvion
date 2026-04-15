package org.example;


import java.util.Random;

public class Personne {
    private final int identifiant;
    private String nom;
    private String adresse;
    private Personne contact;


    public Personne(String nom, String adresse, org.example.Personne contact) {
        this.identifiant = new Random().nextInt(1_000_000);
        this.nom = nom;
        this.adresse = adresse;
        this.contact = contact;
    }

    public String obtenirInfos(){
        return "Identifiant : " + this.identifiant + ", adresse : " + this.adresse + ", nom : " + this.nom + ", contact : " + this.contact;
    }

}
