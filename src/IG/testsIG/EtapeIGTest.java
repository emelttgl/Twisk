package IG.testsIG;

import IG.mondeIG.ActiviteIG;
import IG.mondeIG.PointDeControleIG;
import IG.outilsIG.FabriqueIdentifiant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * The type Etape ig test.
 */
class EtapeIGTest
{
   /**
    * The Activite ig.
    */
   ActiviteIG activiteIG;
   
   /**
    * Sets up.
    */
   @BeforeEach
   void setUp()
   {
      this.activiteIG = new ActiviteIG("test", FabriqueIdentifiant.getInstance().getIdentifiantEtape(), 20, 20);
   }
   
   /**
    * Iterator.
    */
   @Test
   void iterator()
   {
      for(PointDeControleIG p : this.activiteIG)
      {
         assertNotNull(p);
      }
   }
}