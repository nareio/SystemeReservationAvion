package org.example;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ReservationTest {

    private Passager passager;
    private Vol vol;
    private Reservation reservation;

    @BeforeEach
    void setUp() {
        passager    = new Passager("Alice", "Paris", "0611111111");
        vol         = new Vol("10/06/2025 - 08:00", "10/06/2025 - 16:00", "Planifié");
        reservation = new Reservation("10/06/2025", passager);
    }

    @Test
    void testStatutInitial() {
        assertEquals("en cours", reservation.getStatut());
    }

    @Test
    void testConfirmerReservation() {
        reservation.confirmerReservation();
        assertEquals("confirmée", reservation.getStatut());
    }

    @Test
    void testAnnulerReservation() {
        reservation.annulerReservation();
        assertEquals("annulée", reservation.getStatut());
    }

    @Test
    void testModifierReservation() {
        reservation.modifierReservation("15/06/2025");
        assertEquals("15/06/2025", reservation.getDateReservation());
    }

    @Test
    void testAddVol() {
        reservation.addVol(vol);
        assertEquals(1, reservation.getVols().size());
    }

    @Test
    void testRemoveVolParNumero() {
        reservation.addVol(vol);
        reservation.removeVol(vol.getNumeroVol());
        assertTrue(reservation.getVols().isEmpty());
    }

    @Test
    void testRemoveVolParObjet() {
        reservation.addVol(vol);
        reservation.removeVol(vol);
        assertTrue(reservation.getVols().isEmpty());
    }

    @Test
    void testGetPassager() {
        assertEquals("Alice", reservation.getPassager().getNom());
    }

    @Test
    void testNumeroReservationGenere() {
        assertTrue(reservation.getNumeroReservation() >= 0
                && reservation.getNumeroReservation() < 1_000_000);
    }
}