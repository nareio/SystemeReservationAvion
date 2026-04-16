package org.example;


import java.util.Random;

public class Personne {
    private final int identifiant;
    private String nom;
    private String adresse;
    private String contact;


    public Personne(String nom, String adresse, String contact) {
        this.identifiant = new Random().nextInt(1_000_000);
        this.nom = nom;
        this.adresse = adresse;
        this.contact = contact;
    }

    public String obtenirInfos(){
        return "Identifiant : " + this.identifiant + ", adresse : " + this.adresse + ", nom : " + this.nom + ", contact : " + this.contact;
    }

    public String getContact() {
        return contact;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getNom() {
        return nom;
    }

    public int getIdentifiant() {
        return identifiant;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
