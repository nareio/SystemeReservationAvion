package org.example;

import java.util.ArrayList;
import java.util.Random;

public class Vol {

    private final int numeroVol;
    private String origine;
    private String Destination;
    private String DateHeureDepart; // on considère la date sous le format : "DD/MM/AAAA - hh:mm"
    private String DateHeureArrivee; // on considère la date sous le format : "DD/MM/AAAA - hh:mm"
    private String Etat;
    private Avion avion;
    private ArrayList<Reservation> reservations;

    public Vol(String origine, String destination, String dateHeureDepart, String dateHeureArrivee, String etat, Avion avion) {
        this.numeroVol = new Random().nextInt(1_000_000);
        this.origine = origine;
        Destination = destination;
        DateHeureDepart = dateHeureDepart;
        DateHeureArrivee = dateHeureArrivee;
        Etat = etat;
        this.avion = avion;
        reservations = new ArrayList<>();
    }

    public void modifierVol(String DateHeureDepart, String DateHeureArrivee) {
        this.DateHeureDepart = DateHeureDepart;
        this.DateHeureArrivee = DateHeureArrivee;
    }

    public int getNumeroVol() {
        return numeroVol;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public void listingPassager(){
        for(Reservation reservation : reservations){
            System.out.println(reservation.getPassager());
        }
    }

    private String toComparable(String date) {
        String[] parts = date.split("/");
        String dd   = parts[0];
        String mm   = parts[1];
        String rest = parts[2];// année + heure
        String[] yyyyTime = rest.split(" - ");
        String yyyy = yyyyTime[0];
        String time = yyyyTime[1];

        return yyyy + "/" + mm + "/" + dd + " - " + time;
    }

    public String getDeparComparable()  { return toComparable(DateHeureDepart); }
    public String getArriveeComparable(){ return toComparable(DateHeureArrivee); }

}
