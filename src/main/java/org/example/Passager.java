package org.example;

import java.util.ArrayList;
import java.util.Random;

public class Passager extends Personne {

    private final int passport;
    private ArrayList<Reservation> reservation;

    public Passager(String nom, String adresse, Personne contact) {
        super(nom, adresse, contact);
        this.passport = new Random().nextInt(1_000_000);
        this.reservation = new ArrayList<>();
    }

    public int getPassport() {
        return passport;
    }

    public ArrayList<Reservation> getReservation() {
        return reservation;
    }

    public void annulerReservation(int numeroReservation) {
        for (int i = 0; i < this.reservation.size(); i++) {
            if (this.reservation.get(i).getNumeroReservation() == numeroReservation) {
                this.reservation.get(i).annulerReservation();
                this.reservation.remove(i);
            }
        }
    }

    public String obtenirReservation(int numeroReservation) {
        for (Reservation reservation : this.reservation) {
            if (reservation.getNumeroReservation() == numeroReservation) {
                return "reservation trouvé pour le passager : "+reservation.toString();
            }
        }
        return "cette reservation n'existe pas pour le passager";
    }

    public void reserverVol(Vol vol, String dateReservation) {
        Reservation reservation = new Reservation(dateReservation, this);
        reservation.addVol(vol);
        reservations.add(reservation);
    }


}
