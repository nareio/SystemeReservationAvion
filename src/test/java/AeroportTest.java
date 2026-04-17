package org.example;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class AeroportTest {

    private Aeroport aeroport;
    private Vol vol;

    @BeforeEach
    void setUp() {
        aeroport = new Aeroport("Charles de Gaulle", "Paris", "Hub français");
        vol      = new Vol("10/06/2025 - 08:00", "10/06/2025 - 16:00", "Planifié");
    }

    @Test
    void testGetNom() {
        assertEquals("Charles de Gaulle", aeroport.getNom());
    }

    @Test
    void testGetVille() {
        assertEquals("Paris", aeroport.getVille());
    }

    @Test
    void testAjouterVolDepart() {
        aeroport.ajouterVolDepart(vol);
        assertEquals(1, aeroport.getVolsDepart().size());  // à ajouter si pas de getter
    }

    @Test
    void testSetVille() {
        aeroport.setVille("Lyon");
        assertEquals("Lyon", aeroport.getVille());
    }
}