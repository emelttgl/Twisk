package twisk.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.outils.KitC;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The type Kit c test.
 */
class KitCTest {
    private KitC kitc;
    private String str;
   
   /**
    * Sets up.
    */
   @BeforeEach
    void setUp() {
        this.kitc = new KitC();
        this.str="hello";
    }
   
   /**
    * Creer fichier.
    */
   @Test
    void creerFichier() {
        this.kitc.creerEnvironnement();
        this.kitc.creerFichier(this.str);
        assertTrue(new File("/tmp/twisk/client.c").exists());
    }
   
   /**
    * Librairie.
    */
   @Test
    void librairie()
    {
        this.creerFichier();
        this.kitc.construireLaLibrairie();
    }
}