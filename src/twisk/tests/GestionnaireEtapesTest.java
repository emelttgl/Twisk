package twisk.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.monde.Activite;
import twisk.monde.Etape;
import twisk.monde.GestionnaireEtapes;
import twisk.monde.Guichet;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The type Gestionnaire etapes test.
 */
class GestionnaireEtapesTest
{
   private GestionnaireEtapes g_e;
   private Etape[] etapes;
   
   /**
    * Sets up.
    */
   @BeforeEach
   void setUp()
   {
      this.g_e = new GestionnaireEtapes();
      this.etapes = new Etape[5];
      for(int i = 0;i < 5;i++)
      {
         if(i % 2 == 0)
         {
            this.etapes[i] = new Activite("Act_" + i);
         }
         else
         {
            this.etapes[i] = new Guichet("Gui_" + i);
         }
      }
   }
   
   /**
    * Ajouter.
    */
   @Test
   void ajouter()
   {
      this.g_e.ajouter(this.etapes);
      assertEquals(5, this.g_e.nbEtapes());
   }
}