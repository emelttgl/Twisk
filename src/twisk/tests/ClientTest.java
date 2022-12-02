package twisk.tests;

import org.junit.jupiter.api.Test;
import twisk.monde.Activite;
import twisk.monde.Etape;
import twisk.simulation.Client;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The type Client test.
 */
class ClientTest
{
   /**
    * The Test.
    */
   protected Client test = new Client(5);
   
   /**
    * Aller a.
    */
   @Test
    void allerA()
    {
        Etape e = new Activite("nom", 4, 5);
        this.test.allerA(e, 4);
        assertEquals(e, this.test.getEtape_courante());
        assertEquals(4, this.test.getRang());
    }
}