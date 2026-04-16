package org.example;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Rapport {

    private ArrayList<Planning> plannings;

    public Rapport(ArrayList<Planning> plannings) {
        this.plannings = plannings;
    }



    public int getNombreVols() {
        int total = 0;
        for (Planning p : plannings) {
            total += p.getVols().size();
        }
        return total;
    }


    public int getNombrePassagers() {
        int total = 0;
        for (Planning p : plannings) {
            for (Vol vol : p.getVols()) {
                for (Reservation r : vol.getReservations()) {
                    if (r.getStatut().equals("Confirmée")) {
                        total++;
                    }
                }
            }
        }
        return total;
    }


    public Map<String, Integer> getDestinationsPopulaires() {
        Map<String, Integer> destinations = new HashMap<>();

        for (Planning p : plannings) {
            for (Vol vol : p.getVols()) {
                String destination = vol.getDestination().getVille();
                if (destinations.containsKey(destination)) {
                    destinations.put(destination, destinations.get(destination) + 1);
                } else {
                    destinations.put(destination, 1);
                }
            }
        }
        return destinations;
    }


    public String getDestinationLaPlusPopulaire() {
        Map<String, Integer> destinations = getDestinationsPopulaires();
        String meilleureDestination = null;
        int max = 0;

        for (Map.Entry<String, Integer> entry : destinations.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                meilleureDestination = entry.getKey();
            }
        }
        return meilleureDestination;
    }

    // Rapport complet
    public void afficherRapport() {
        System.out.println("---");
        System.out.println("RAPPORT COMPLET");
        System.out.println("---");

        System.out.println("Nombre de vols : " + getNombreVols());
        System.out.println("Passagers transportés : " + getNombrePassagers());

        System.out.println("--- Destinations ---");
        Map<String, Integer> destinations = getDestinationsPopulaires();
        for (Map.Entry<String, Integer> entry : destinations.entrySet()) {
            System.out.printf(entry.getKey()+" : "+entry.getValue()+" vol(s)");
        }

        System.out.println("Destination la plus populaire : "+ getDestinationLaPlusPopulaire());
    }
}