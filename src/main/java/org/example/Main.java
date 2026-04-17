package org.example;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {


        //Création des aéroports
        Aeroport cdg     = new Aeroport("Charles de Gaulle", "Paris", "Principal hub français");
        Aeroport dxb     = new Aeroport("Dubai International", "Dubai", "Hub du Moyen-Orient");
        Aeroport jfk     = new Aeroport("John F. Kennedy", "New York", "Principal hub américain");
        Aeroport hnd     = new Aeroport("Haneda", "Tokyo", "Principal hub japonais");
        Aeroport lyx     = new Aeroport("Lyon Saint-Exupéry", "Lyon", "Hub régional français");

        //Création des avions
        Avion a320  = new Avion( "A320", 180);
        Avion b777  = new Avion( "B777", 350);
        Avion a380  = new Avion( "A380", 500);
        

        //Création des vols + ajout des aéroports
        Vol v1 = new Vol( "10/06/2025 - 08:00", "10/06/2025 - 16:00", "Planifié");
        v1.ajouterAeroport(cdg);
        v1.ajouterAeroport(dxb);

        Vol v2 = new Vol( "10/06/2025 - 10:00", "11/06/2025 - 06:00", "Planifié");
        v2.ajouterAeroport(cdg);
        v2.ajouterAeroport(dxb);
        v2.ajouterAeroport(hnd);

        Vol v3 = new Vol( "10/06/2025 - 14:00", "10/06/2025 - 22:00", "Planifié");
        v3.ajouterAeroport(lyx);
        v3.ajouterAeroport(jfk);

        Vol v4 = new Vol("11/06/2025 - 09:00", "11/06/2025 - 17:00", "Planifié");
        v4.ajouterAeroport(cdg);
        v4.ajouterAeroport(dxb);

        //Affichage des itinéraires
        System.out.println("=== Itinéraires ===");
        v1.afficherItineraire();
        v2.afficherItineraire();
        v3.afficherItineraire();
        v4.afficherItineraire();

        //Affectation des avions aux vols
        System.out.println("\n=== Affectation des avions ===");
        v1.setAvion(a320);
        v2.setAvion(b777);
        v3.setAvion(a380);
        v4.setAvion(a380);

        //Création du personnel
        Pilote pilote1 = new Pilote( "Dupont", "Paris", "0600000001",
                "05/06/2025", 5000);
        Pilote pilote2 = new Pilote("Martin", "Lyon", "0600000002",
                "05/06/2025", 3000);

        PersonnelCabine pc1 = new PersonnelCabine("Durand", "Paris", "0600000003",
                "05/07/2024", "Hôtesse");
        PersonnelCabine pc2 = new PersonnelCabine("Bernard", "Paris", "0600000004",
                "12/12/2012", "Steward");
        PersonnelCabine pc3 = new PersonnelCabine("Leroy", "Lyon", "0600000005",
                "24/05/2022", "Hôtesse");

        //Affectation du personnel aux vols
        System.out.println("\n=== Affectation du personnel ===");
        v1.affecterPilote(pilote1);
        v1.affecterEquipage(pc1);
        v1.affecterEquipage(pc2);

        v2.affecterPilote(pilote2);
        v2.affecterEquipage(pc2);
        v2.affecterEquipage(pc3);

        v3.affecterPilote(pilote1);
        v3.affecterEquipage(pc1);

        v4.affecterPilote(pilote2);
        v4.affecterEquipage(pc3);

        //Création des passagers
        Passager passager1 = new Passager("Alice", "Paris", "0611111111");
        Passager passager2 = new Passager("Bob", "Lyon", "0622222222");
        Passager passager3 = new Passager("Charlie", "Paris", "0633333333");

        //Réservations
        System.out.println("\n=== Réservations ===");
        passager1.reserverVol(v1, "05/06/2025");
        passager1.reserverVol(v2, "15/04/2026");
        passager2.reserverVol(v1,  "20/06/2026");
        passager3.reserverVol(v3,   "11/08/2026");

        // Confirmation de certaines réservations
        passager1.getReservations().getFirst().confirmerReservation();
        passager2.getReservations().getFirst().confirmerReservation();

        // Affichage des réservations de chaque passager
        System.out.println("\n=== Réservations par passager ===");
        afficherReservations(passager1);
        afficherReservations(passager2);
        afficherReservations(passager3);

        //Planification
        System.out.println("\n=== Planification ===");
        Planning planning1 = new Planning("10/06/2025");
        planning1.planifierVol(v1);
        planning1.planifierVol(v2);
        planning1.planifierVol(v3);
        planning1.planifierVol(v4); // refusé — départ le 11/06

        Planning planning2 = new Planning("11/06/2025");
        planning2.planifierVol(v4);
        planning2.planifierVol(v2); // accepté — arrivée le 11/06

        planning1.afficherPlanning();
        planning2.afficherPlanning();

        //Rapport
        ArrayList<Planning> plannings = new ArrayList<>();
        plannings.add(planning1);
        plannings.add(planning2);

        Rapport rapport = new Rapport(plannings);
        rapport.afficherRapport();


        // TEST importFlights() — lecture Vols.csv
        System.out.println("--- Test import depuis Vols.csv ---");
        ArrayList<Aeroport> listeAeroports = new ArrayList<>();
        listeAeroports.add(cdg);
        listeAeroports.add(dxb);
        listeAeroports.add(jfk);
        listeAeroports.add(hnd);
        listeAeroports.add(lyx);

        ArrayList<Vol> volsImportes = Vol.importFlights(
                "src/main/resources/Vols.csv",
                listeAeroports
        );

        System.out.println("Vols importés :");
        for (Vol v : volsImportes) {
            v.afficherItineraire();
        }

        // TEST saveReservationFile() — écriture passagers.csv
        System.out.println("--- Test sauvegarde dans passagers.csv ---");
        String fichierPassagers = "src/main/resources/passagers.csv";

        for (Reservation r : passager1.getReservations()) {
            r.saveReservationFile(fichierPassagers);
        }
        for (Reservation r : passager2.getReservations()) {
            r.saveReservationFile(fichierPassagers);
        }
        for (Reservation r : passager3.getReservations()) {
            r.saveReservationFile(fichierPassagers);
        }

        System.out.println("Sauvegarde terminée -> " + fichierPassagers);

    }

    private static void afficherReservations(Passager passager) {
        System.out.println("Réservations de " + passager.getNom() + " :");
        for (Reservation r : passager.getReservations()) {
            System.out.println("  " + r.toString());
        }
    }
}
