package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Reservation {

    private final int numeroReservation;
    private String dateReservation;
    private String statut;
    private ArrayList<Vol> vols; //contradiction entre diagramme et énoncé -> nous avons choisi de faire une liste
    private Passager passager;


    public Reservation(String dateReservation, Passager passager) {
        this.numeroReservation = new Random().nextInt(1_000_000);
        this.dateReservation = dateReservation;
        this.statut = "en cours";
        this.vols =  new ArrayList<Vol>();
        this.passager = passager;
    }

    public int getNumeroReservation() {
        return numeroReservation;
    }
    public String getDateReservation() {
        return dateReservation;
    }
    public void setDateReservation(String dateReservation) {
        this.dateReservation = dateReservation;
    }
    public String getStatut() {
        return statut;
    }
    public void confirmerReservation() {
        this.statut = "confirmée";
    }
    public void annulerReservation() {
        this.statut = "annulée";
    }

    public void modifierReservation(String dateReservation) {
        this.dateReservation = dateReservation;
    }

    public ArrayList<Vol> getVols() {
        return vols;
    }

    public void addVol(Vol vol) {
        this.vols.add(vol);
    }

    public void removeVol(int numeroVol) {
        for (int i = 0; i < this.vols.size(); i++) {
            if (this.vols.get(i).getNumeroVol() == numeroVol) {
                this.vols.remove(i);
            }
        }
    }

    public void removeVol(Vol vol) {
        for (int i = 0; i < this.vols.size(); i++) {
            if (this.vols.get(i).getNumeroVol() == vol.getNumeroVol()) {
                this.vols.remove(i);
            }
        }
    }

    public Passager getPassager() {
        return passager;
    }

    public void setPassager(Passager passager) {
        this.passager = passager;
    }


    @Override
    public String toString() {
        List<Integer> numVols = new ArrayList<Integer>();
        for (Vol v : vols) {
            numVols.add(v.getNumeroVol());
        }

        return "Reservation{" +
                "numero=" + numeroReservation +
                ", date=" + dateReservation +
                ", statut=" + statut +
                ", vols=" + numVols +
                "}";
    }

    public void saveReservationFile(String cheminFichier) {

        // true = mode append, on écrit à la fin sans écraser
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(cheminFichier, true))) {

            // Vérifie si le fichier est vide
            java.io.File fichier = new java.io.File(cheminFichier);
            if (fichier.length() == 0) {
                bw.write("numeroReservation,dateReservation,statut,passager,vols");
                bw.newLine();
            }

            // Construit la liste des numéros de vol
            StringBuilder numVols = new StringBuilder();
            for (Vol v : vols) {
                if (!numVols.isEmpty()) numVols.append("|");
                numVols.append(v.getNumeroVol());
            }

            bw.write(
                    numeroReservation + "," +
                            dateReservation   + "," +
                            statut            + "," +
                            passager.getNom() + "," +
                            numVols.toString()
            );
            bw.newLine();

            System.out.println("Réservation " + numeroReservation + " sauvegardée dans " + cheminFichier);

        } catch (IOException e) {
            System.out.println("Erreur écriture fichier : " + e.getMessage());
        }
    }
}
