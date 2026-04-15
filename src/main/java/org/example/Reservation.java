package org.example;

import java.util.ArrayList;
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

    public Passager getPassager() {
        return passager;
    }

    public void setPassager(Passager passager) {
        this.passager = passager;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "numeroReservation=" + numeroReservation +
                ", dateReservation='" + dateReservation + '\'' +
                ", statut='" + statut + '\'' +
                ", vols=" + vols +
                ", passager=" + passager +
                '}';
    }
}
