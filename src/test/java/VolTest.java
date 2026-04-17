package org.example;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class VolTest {

    private Vol vol;
    private Aeroport paris;
    private Aeroport dubai;

    @BeforeEach
    void setUp() {
        paris = new Aeroport("Charles de Gaulle", "Paris", "Hub français");
        dubai = new Aeroport("Dubai International", "Dubai", "Hub Moyen-Orient");
        vol   = new Vol("10/06/2025 - 08:00", "10/06/2025 - 16:00", "Planifié");
    }

    @Test
    void testNumeroVolGenere() {
        // Le numéro doit être entre 0 et 1 000 000
        assertTrue(vol.getNumeroVol() >= 0 && vol.getNumeroVol() < 1_000_000);
    }

    @Test
    void testEtatInitial() {
        assertEquals("Planifié", vol.getEtat());
    }

    @Test
    void testDates() {
        assertEquals("10/06/2025 - 08:00", vol.getDateHeureDepart());
        assertEquals("10/06/2025 - 16:00", vol.getDateHeureArrivee());
    }

    @Test
    void testModifierVol() {
        vol.modifierVol("11/06/2025 - 09:00", "11/06/2025 - 17:00");
        assertEquals("11/06/2025 - 09:00", vol.getDateHeureDepart());
        assertEquals("11/06/2025 - 17:00", vol.getDateHeureArrivee());
    }

    @Test
    void testAjouterAeroport() {
        vol.ajouterAeroport(paris);
        vol.ajouterAeroport(dubai);
        assertEquals(2, vol.getAeroports().size());
    }

    @Test
    void testOrigineEtDestination() {
        vol.ajouterAeroport(paris);
        vol.ajouterAeroport(dubai);
        assertEquals("Paris", vol.getOrigine().getVille());
        assertEquals("Dubai", vol.getDestination().getVille());
    }

    @Test
    void testFIFOOrdreAeroports() {
        Aeroport tokyo = new Aeroport("Haneda", "Tokyo", "Hub japonais");
        vol.ajouterAeroport(paris);
        vol.ajouterAeroport(dubai);
        vol.ajouterAeroport(tokyo);

        // peek = départ, ne retire pas
        assertEquals("Paris", vol.prochainAeroport().getVille());

        // poll = retire le premier
        assertEquals("Paris", vol.atteindreProchaineEscale().getVille());
        assertEquals("Dubai", vol.prochainAeroport().getVille());
    }

    @Test
    void testAvionNullParDefaut() {
        assertNull(vol.getAvion());
    }

    @Test
    void testPiloteNullParDefaut() {
        assertNull(vol.getPilote());
    }

    @Test
    void testAffecterPilote() {
        Pilote pilote = new Pilote("Dupont", "Paris", "0600000001", "01/01/2020", 5000);
        vol.affecterPilote(pilote);
        assertNotNull(vol.getPilote());
        assertEquals("Dupont", vol.getPilote().getNom());
    }

    @Test
    void testAjouterReservation() {
        Passager passager = new Passager("Alice", "Paris", "0611111111");
        Reservation r = new Reservation("10/06/2025", passager);
        vol.ajouterReservation(r);
        assertEquals(1, vol.getReservations().size());
    }

    @Test
    void testAnnulerReservation() {
        Passager passager = new Passager("Alice", "Paris", "0611111111");
        Reservation r = new Reservation("10/06/2025", passager);
        r.addVol(vol);
        vol.ajouterReservation(r);

        vol.annulerReservation(r);
        // Le vol est retiré de la réservation
        assertTrue(r.getVols().isEmpty());
    }

    @Test
    void testDepartComparable() {
        assertEquals("2025/06/10 - 08:00", vol.getDepartComparable());
    }

    @Test
    void testArriveeComparable() {
        assertEquals("2025/06/10 - 16:00", vol.getArriveeComparable());
    }
}