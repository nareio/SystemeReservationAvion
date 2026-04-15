package org.example;

import java.util.Random;

public class Pilote extends Employe{

    private final int licence;
    private int heureDeVol;

    public Pilote(String nom, String adresse, Personne contact, int numeroEmploye, String dateEmbauche, int heureDeVol) {
        super(nom, adresse, contact, numeroEmploye, dateEmbauche);
        this.licence = new Random().nextInt(1_000_000);
        this.heureDeVol = heureDeVol;
    }

    @Override
    public String obtenirRole() {
        return "pilote";
    }

    @Override
    public String obtenirInfos() {
        return super.obtenirInfos() + "Role : " + obtenirRole() + ", numero licence : " + this.licence + ", nombre d'heures de vol : " + this.heureDeVol;
    }
}
