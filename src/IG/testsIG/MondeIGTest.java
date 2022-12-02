package IG.testsIG;

import IG.mondeIG.EtapeIG;
import IG.mondeIG.MondeIG;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * The type Monde ig test.
 */
class MondeIGTest
{
   /**
    * The Monde.
    */
   MondeIG monde;
   /**
    * The Etapes.
    */
   String[] etapes;
   
   /**
    * Sets up.
    */
   @BeforeEach
   void setUp()
   {
      this.monde = new MondeIG();
      this.etapes = new String[5];
      for(int i = 0;i < this.etapes.length;i++)
      {
         this.etapes[i] = "Act_" + i;
      }
   }
   
   /**
    * Ajouter iterator.
    */
   @Test
   void ajouter_iterator()
   {
      for(String s :this.etapes)
      {
         this.monde.ajouter(s);
      }
   
      int i = 0;
      for(EtapeIG e : this.monde.iterator())
      {
         assertNotNull(e.toString());
         i++;
      }
      assertEquals(i, 6);
   }
}