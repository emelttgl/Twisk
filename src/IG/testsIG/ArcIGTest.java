package IG.testsIG;

import IG.mondeIG.CourbeIG;
import IG.mondeIG.LigneDroiteIG;
import IG.mondeIG.PointDeControleIG;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * The type Arc ig test.
 */
class ArcIGTest
{
   /**
    * The C.
    */
   protected CourbeIG c;
   /**
    * The L.
    */
   protected LigneDroiteIG l;
   
   /**
    * Sets up.
    */
   @BeforeEach
   void setUp()
   {
      this.c = new CourbeIG(new PointDeControleIG(4,5,"t"), new PointDeControleIG(4,5,"t"), new Point(4,5), new Point(4,5));
      this.l = new LigneDroiteIG(new PointDeControleIG(4,5,"t"), new PointDeControleIG(4,5,"t"));
   }
   
   /**
    * Arc ig.
    */
   @Test
   void ArcIG()
   {
      assertNotNull(this.c);
      assertNotNull(this.l);
   }
}