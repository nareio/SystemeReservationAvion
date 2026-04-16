package org.example;

/*
Classe ajoutée pour inclure la fonction planningVol() initialement placé dans la classe Vol.
Nous avons jugé cette fonction mal placée, et avons donc crée une classe correspondante.
*/

import java.util.ArrayList;

public class Planning {

    private String date; // DD/MM/AAAA
    private ArrayList<Vol> vols;


    public Planning(String date) {
        this.date = date;
        this.vols = new ArrayList<>();
    }

    public void planifierVol(Vol vol) {
        // Extrait "DD/MM/AAAA" depuis "DD/MM/AAAA - hh:mm"
        String dateDepart  = vol.getDateHeureDepart().split(" - ")[0];
        String dateArrivee = vol.getDateHeureArrivee().split(" - ")[0];

        if (!dateDepart.equals(date) && !dateArrivee.equals(date)) {
            System.out.println("Vol " + vol.getNumeroVol()
                    + " refusé : ni le départ (" + dateDepart
                    + ") ni l'arrivée (" + dateArrivee
                    + ") ne correspondent au planning du " + date);
        }
        else vols.add(vol);
    }

    public ArrayList<Vol> getVols() {
        return vols;
    }

    public void afficherPlanning() {
        System.out.println("--- Planning du " + date + " ---");
        for (Vol vol : vols) {
            System.out.println("  Vol : " + vol.getNumeroVol()
                    + " passant par :"+ vol.getAeroportsString()
                    + " | Départ : " + vol.getDateHeureDepart()
                    + "et arrivant à : "+vol.getDateHeureArrivee());
        }
    }
}
