package org.example;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class PassagerTest {

    private Passager passager;
    private Vol vol;

    @BeforeEach
    void setUp() {
        passager = new Passager("Bob", "Lyon", "0622222222");
        vol      = new Vol("10/06/2025 - 10:00", "10/06/2025 - 18:00", "Planifié");
    }

    @Test
    void testPassportGenere() {
        assertTrue(passager.getPassport() >= 0 && passager.getPassport() < 1_000_000);
    }

    @Test
    void testReservationsVideesAuDepart() {
        assertTrue(passager.getReservations().isEmpty());
    }

    @Test
    void testReserverVol() {
        passager.reserverVol(vol, "10/06/2025");
        assertEquals(1, passager.getReservations().size());
    }

    @Test
    void testReserverVolAjouteLeVol() {
        passager.reserverVol(vol, "10/06/2025");
        Reservation r = passager.getReservations().get(0);
        assertEquals(vol.getNumeroVol(), r.getVols().get(0).getNumeroVol());
    }

    @Test
    void testAnnulerReservation() {
        passager.reserverVol(vol, "10/06/2025");
        int numero = passager.getReservations().get(0).getNumeroReservation();

        passager.annulerReservation(numero);
        assertTrue(passager.getReservations().isEmpty());
    }

    @Test
    void testObtenirReservationExistante() {
        passager.reserverVol(vol, "10/06/2025");
        int numero = passager.getReservations().get(0).getNumeroReservation();

        String result = passager.obtenirReservation(numero);
        assertTrue(result.contains("reservation trouvé"));
    }

    @Test
    void testObtenirReservationInexistante() {
        String result = passager.obtenirReservation(999999);
        assertEquals("cette reservation n'existe pas pour le passager", result);
    }
}
