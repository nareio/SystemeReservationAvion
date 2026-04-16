package org.example;

import java.util.ArrayList;
import java.util.Random;

public class Avion {

    private final int immatriculation;
    private String modele;
    private int capacite;
    private ArrayList<Vol> vols; //contradiction entre diagramme et énoncé -> nous avons choisi de faire une liste

    public Avion(String modele, int capacite) {
        this.immatriculation = new Random().nextInt(1_000_000);
        this.modele = modele;
        this.capacite = capacite;
    }

    public void affecterVol(Vol vol) {
        if (!verifierDisponibilite(vol)) {
            System.out.println("Avion " + immatriculation + " non disponible.");
            return;
        }
        vols.add(vol);
        vol.setAvion(this);
        System.out.println("Avion " + immatriculation + " affecté au vol " + vol.getNumeroVol());
    }

    public boolean verifierDisponibilite(Vol volVoulu) {
        boolean chevauchement;
        String debutDemande = volVoulu.getDepartComparable();
        String finDemande   = volVoulu.getArriveeComparable();

        for (Vol vol : vols) {
            String debutExistant = vol.getDepartComparable();
            String finExistante  = vol.getArriveeComparable();

            chevauchement = debutExistant.compareTo(finDemande) < 0 && finExistante.compareTo(debutDemande) > 0;

            if (chevauchement) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<Vol> getVols() {
        return vols;
    }

    public int getCapacite() {
        return capacite;
    }

    public String getModele() {
        return modele;
    }

    public int getImmatriculation() {
        return immatriculation;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }
}
