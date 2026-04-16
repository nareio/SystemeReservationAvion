package org.example;

import java.util.*;

public class Vol {

    private final int numeroVol;
    private String DateHeureDepart; // on considère la date sous le format : "DD/MM/AAAA - hh:mm"
    private String DateHeureArrivee; // on considère la date sous le format : "DD/MM/AAAA - hh:mm"
    private String Etat;
    private Avion avion;
    private ArrayList<Reservation> reservations;
    private Pilote pilote;
    private ArrayList<Employe> equipageCabine; // laissé en employe pour pouvoir ajouter un co-pilote à l'équipage
    private ArrayDeque<Aeroport> aeroports; // FIFO : premier = départ, suivant = escales, dernier = arrivée

    public Vol(ArrayDeque<Aeroport> aeroports, String dateHeureDepart, String dateHeureArrivee, String etat, Avion avion, Pilote pilote) {
        this.numeroVol = new Random().nextInt(1_000_000);
        DateHeureDepart = dateHeureDepart;
        DateHeureArrivee = dateHeureArrivee;
        Etat = etat;
        this.avion = avion;
        reservations = new ArrayList<>();
        equipageCabine = new ArrayList<>();
        this.aeroports = aeroports;
        this.pilote = pilote;
    }

    public Vol(String dateHeureDepart, String dateHeureArrivee, String etat) {
        this.numeroVol = new Random().nextInt(1_000_000);
        DateHeureDepart = dateHeureDepart;
        DateHeureArrivee = dateHeureArrivee;
        Etat = etat;
        this.avion = null;
        reservations = new ArrayList<>();
        equipageCabine = new ArrayList<>();
        this.aeroports = new ArrayDeque<>();
        this.pilote = null;
    }

    public void affecterPilote(Pilote pilote) {
        this.pilote = pilote;
    }

    public Pilote getPilote() {
        return pilote;
    }

    public void affecterEquipage(PersonnelCabine personnel) {
        equipageCabine.add(personnel);
    }

    public ArrayDeque<Aeroport> getAeroports() {
        return aeroports;
    }

    public String getAeroportsString() {
        StringBuilder sb = new StringBuilder();
        for (Aeroport aeroport : aeroports) {
            sb.append(aeroport.toString());
        }
        return sb.toString();
    }

    public ArrayList<Reservation> getReservations() {
        return reservations;
    }

    public Avion getAvion() {
        return avion;
    }

    public String getEtat() {
        return Etat;
    }

    public String getDateHeureArrivee() {
        return DateHeureArrivee;
    }

    public String getDateHeureDepart() {
        return DateHeureDepart;
    }

    public void removeEquipage(Employe personnel) {
        equipageCabine.remove(personnel);
    }


    public void ajouterAeroport(Aeroport aeroport) {
        aeroports.add(aeroport);
    }

    public void ajouterReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public Aeroport prochainAeroport() {
        return aeroports.peek();
    }

    public Aeroport atteindreProchaineEscale() {
        return aeroports.poll();
    }

    public ArrayDeque<Aeroport> getEscales() {
        return aeroports;
    }

    public ArrayList<Employe> getEquipageCabine() {
        return equipageCabine;
    }

    public void afficherItineraire() {
        System.out.println("Itinéraire du vol " + numeroVol + " :");
        int etape = 0;
        for (Aeroport a : aeroports) {
            if (etape == 0)
                System.out.println("  Départ    : " + a.getNom() + " (" + a.getVille() + ")");
            else if (etape == aeroports.size() - 1)
                System.out.println("  Arrivée   : " + a.getNom() + " (" + a.getVille() + ")");
            else
                System.out.println("  Escale " + etape + "  : " + a.getNom() + " (" + a.getVille() + ")");
            etape++;
        }
    }

    public void annulerReservation(Reservation reservation) {
        reservation.removeVol(this.numeroVol);
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

    public String getDepartComparable()  { return toComparable(DateHeureDepart); }
    public String getArriveeComparable(){ return toComparable(DateHeureArrivee); }


    @Override
    public String toString() {
        List<String> nomsAeroports = new ArrayList<>();
        for (Aeroport a : aeroports) {
            nomsAeroports.add(a.getVille());
        }

        List<Integer> numReservations = new ArrayList<Integer>();
        for (Reservation r : reservations) {
            numReservations.add(r.getNumeroReservation());
        }

        List<String> nomsEquipage = new ArrayList<>();
        for (Employe p : equipageCabine) {
            nomsEquipage.add(p.getNom());
        }

        return "Vol{" +
                "numeroVol=" + numeroVol +
                ", depart='" + DateHeureDepart + "'" +
                ", arrivee='" + DateHeureArrivee + "'" +
                ", etat='" + Etat + "'" +
                // identifiant seulement, pas l'objet entier
                ", avion=" + (avion != null ? avion.getImmatriculation() : "non affecté") +
                ", pilote=" + (pilote != null ? pilote.getNom() : "non affecté") +
                ", equipage=" + nomsEquipage +
                ", aeroports=" + nomsAeroports +
                ", reservations=" + numReservations +
                "}";
    }



    public Aeroport getOrigine() {
        return aeroports.peek(); // ne retire pas
    }

    public Aeroport getDestination() {
        Aeroport derniere = null;
        for (Aeroport a : aeroports) {
            derniere = a;
        }
        return derniere;
    }

}
