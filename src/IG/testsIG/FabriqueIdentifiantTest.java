package IG.testsIG;

import IG.outilsIG.FabriqueIdentifiant;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The type Fabrique identifiant test.
 */
class FabriqueIdentifiantTest
{
   /**
    * Gets identifiant etape.
    */
   @Test
   void getIdentifiantEtape()
   {
      for(int i = 0;i < 5;i++)
      {
         assertEquals(i + "", FabriqueIdentifiant.getInstance().getIdentifiantEtape());
      }
   }
}