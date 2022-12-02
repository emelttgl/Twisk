package twisk.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.monde.Activite;
import twisk.monde.Etape;
import twisk.simulation.Client;
import twisk.simulation.GestionnaireClients;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The type Gestionnaire clients test.
 */
class GestionnaireClientsTest
{
   /**
    * The Test.
    */
   protected GestionnaireClients test = new GestionnaireClients();
   /**
    * The Set clients.
    */
   protected int[] setClients = new int[5];
   
   /**
    * Sets up.
    */
   @BeforeEach
    void setUp()
    {
        for(int i = 0;i < 5;i++)
        {
            this.setClients[i] = i;
        }
    }
   
   /**
    * Test set clients.
    */
   @Test
    void testSetClients()
    {
        int i = 0;
        this.test.setClients(this.setClients);
        for(Client c : this.test)
        {
            i++;
        }
        assertEquals(5, i);
    }
   
   /**
    * Test aller a.
    */
   @Test
    void testAllerA()
    {
        Etape e = new Activite("nom", 4, 5);
        this.test.setClients(this.setClients);
        this.test.allerA(2, e, 4);
        assertEquals(e, this.test.getClient(2).getEtape_courante());
        assertEquals(4, this.test.getClient(2).getRang());
    }
}