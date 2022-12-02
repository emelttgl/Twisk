package IG.testsIG;

import IG.mondeIG.ActiviteIG;
import IG.mondeIG.PointDeControleIG;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * The type Point de controle ig test.
 */
class PointDeControleIGTest
{
   /**
    * The Etape test.
    */
   ActiviteIG etape_test;
   
   /**
    * Sets up.
    */
   @BeforeEach
   void setUp()
   {
      this.etape_test = new ActiviteIG("Test", "0", 50, 50);
   }
   
   /**
    * Point de controle.
    */
   @Test
   void PointDeControle()
   {
      for(PointDeControleIG p : this.etape_test)
      {
         assertNotNull(p);
      }
   }
}