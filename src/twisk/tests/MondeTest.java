package twisk.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.monde.Activite;
import twisk.monde.Etape;
import twisk.monde.Guichet;
import twisk.monde.Monde;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * The type Monde test.
 */
class MondeTest
{
   private Monde m;
   private Etape[] etapes;
   
   /**
    * Sets up.
    */
   @BeforeEach
   void setUp()
   {
      this.m = new Monde();
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
    * A comme entree.
    */
   @Test
   void aCommeEntree()
   {
      this.m.aCommeEntree(this.etapes);
      assertEquals(5, this.m.getEntree().nbSuccesseurs());
   }
   
   /**
    * Ajouter.
    */
   @Test
   void ajouter()
   {
      this.m.ajouter(this.etapes);
      assertEquals(6, this.m.nbEtapes());
   }
   
   /**
    * A comme sortie.
    */
   @Test
   void aCommeSortie()
   {
      this.ajouter();
      this.m.aCommeSortie(this.etapes);
      for(Etape e : this.m)
      {
         if(e.getNum() != 0 && e.getNum() != 6)
         {
            assertEquals(1, e.nbSuccesseurs());
         }
      }
   }
   
   /**
    * Nb guichets.
    */
   @Test
   void nbGuichets()
   {
      this.m.ajouter(this.etapes);
      assertEquals(2, this.m.nbGuichets());
   }
   
   /**
    * To c.
    */
   @Test
   void toC()
   {
      this.m.aCommeEntree(this.etapes[0]);
      this.m.aCommeSortie(this.etapes[4]);
      this.m.ajouter(this.etapes);
      
      for(int i = 0;i < this.etapes.length - 1;i++)
      {
         this.etapes[i].ajouterSuccesseur(this.etapes[i + 1]);
      }
   
      System.out.println(this.m.toC());
      assertNotNull(this.m.toC());
   }
}