package org.example;

import java.util.ArrayList;
import java.util.Random;

public class Passager extends Personne {

    private final int passport;
    private ArrayList<Reservation> reservations;

    public Passager(String nom, String adresse, String contact) {
        super(nom, adresse, contact);
        this.passport = new Random().nextInt(1_000_000);
        this.reservations = new ArrayList<>();
    }

    public int getPassport() {
        return passport;
    }

    public ArrayList<Reservation> getReservation() {
        return reservations;
    }

    public void annulerReservation(int numeroReservation) {
        for (int i = 0; i < this.reservations.size(); i++) {
            if (this.reservations.get(i).getNumeroReservation() == numeroReservation) {
                this.reservations.get(i).annulerReservation();
                this.reservations.remove(i);
            }
        }
    }

    public String obtenirReservation(int numeroReservation) {
        for (Reservation reservation : this.reservations) {
            if (reservation.getNumeroReservation() == numeroReservation) {
                return "reservation trouvé pour le passager : "+reservation.toString();
            }
        }
        return "cette reservation n'existe pas pour le passager";
    }

    public ArrayList<Reservation> getReservations() {
        return reservations;
    }

    public void reserverVol(Vol vol, String dateReservation) {
        Reservation reservation = new Reservation(dateReservation, this);
        reservation.addVol(vol);
        this.reservations.add(reservation);
    }

    @Override
    public String toString() {
        return "Passager{" +
                "passport=" + passport +
                ", reservations=" + reservations +
                '}';
    }
}
