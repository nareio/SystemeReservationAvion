package org.example;

import java.util.ArrayList;

public class Aeroport {

    private String nom;
    private String ville;
    private String Description;
    private ArrayList<Vol> volsDepart;
    private ArrayList<Vol> volsArrivee;


    public Aeroport(String nom, String ville, String description) {
        this.nom = nom;
        this.ville = ville;
        Description = description;
        volsDepart = new ArrayList<>();
        volsArrivee = new ArrayList<>();
    }

    public void ajouterVolDepart(Vol vol) {
        volsDepart.add(vol);
    }

    public void ajouterVolArrivee(Vol vol) {
        volsArrivee.add(vol);
    }

    public void removeVolDepart(Vol vol) {
        volsDepart.remove(vol);
    }

    public void removeVolArrivee(Vol vol) {
        volsArrivee.remove(vol);
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return Description;
    }

    public String getVille() {
        return ville;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @Override
    public String toString() {
        return "Aeroport{" +
                "nom='" + nom + '\'' +
                ", ville='" + ville + '\'' +
                ", Description='" + Description + '\'' +
                ", volsDepart=" + volsDepart +
                ", volsArrivee=" + volsArrivee +
                '}';
    }
}
