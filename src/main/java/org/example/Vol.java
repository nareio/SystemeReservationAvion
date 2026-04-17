package org.example;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

    public Vol(int numeroVol, String dateHeureDepart, String dateHeureArrivee, String etat) {
        this.numeroVol = numeroVol;
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

    public static ArrayList<Vol> importFlights(String cheminFichier, ArrayList<Aeroport> aeroports) {
        ArrayList<Vol> vols = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(cheminFichier))) {

            String ligne;
            boolean premiereLigne = true;

            while ((ligne = br.readLine()) != null) {

                if (premiereLigne) {
                    premiereLigne = false;
                    continue;
                }

                if (ligne.trim().isEmpty()) continue;

                String[] colonnes = ligne.split(",");

                if (colonnes.length < 5) {
                    System.out.println("Ligne ignorée (format invalide) : " + ligne);
                    continue;
                }

                // Pas de numeroVol — généré automatiquement dans le constructeur
                String dateHeureDepart  = colonnes[0].trim();
                String dateHeureArrivee = colonnes[1].trim();
                String etat             = colonnes[2].trim();
                String nomDepart        = colonnes[3].trim();
                String nomArrivee       = colonnes[4].trim();

                // Constructeur sans numéro de vol
                Vol vol = new Vol(dateHeureDepart, dateHeureArrivee, etat);

                Aeroport aeroportDepart  = trouverAeroport(aeroports, nomDepart);
                Aeroport aeroportArrivee = trouverAeroport(aeroports, nomArrivee);

                if (aeroportDepart != null) {
                    vol.ajouterAeroport(aeroportDepart);
                } else {
                    Aeroport nouveau = new Aeroport(nomDepart, nomDepart, "");
                    vol.ajouterAeroport(nouveau);
                    aeroports.add(nouveau);
                    System.out.println("Aéroport créé automatiquement : " + nomDepart);
                }

                if (aeroportArrivee != null) {
                    vol.ajouterAeroport(aeroportArrivee);
                } else {
                    Aeroport nouveau = new Aeroport(nomArrivee, nomArrivee, "");
                    vol.ajouterAeroport(nouveau);
                    aeroports.add(nouveau);
                    System.out.println("Aéroport créé automatiquement : " + nomArrivee);
                }

                vols.add(vol);
                System.out.println("Vol importé : " + vol.getNumeroVol()
                        + " | " + nomDepart + " -> " + nomArrivee
                        + " | " + dateHeureDepart);
            }

        } catch (IOException e) {
            System.out.println("Erreur lecture fichier : " + e.getMessage());
        }

        System.out.println("\n" + vols.size() + " vol(s) importé(s) depuis " + cheminFichier);
        return vols;
    }

    //recherche un aéroport par ville dans la liste
    private static Aeroport trouverAeroport(List<Aeroport> aeroports, String ville) {
        for (Aeroport a : aeroports) {
            if (a.getVille().equalsIgnoreCase(ville)) {
                return a;
            }
        }
        return null;
    }

}
