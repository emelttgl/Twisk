package IG.mondeIG;

import java.awt.*;

public class LigneDroiteIG extends ArcIG
{
   /**
    * Instantiates a new Ligne droite ig.
    *
    * @param deb the deb
    * @param fin the fin
    */
   public LigneDroiteIG(PointDeControleIG deb, PointDeControleIG fin)
   {
      super(deb, fin);
   }
   
   @Override
   public Point getMilieu_1() { return null; }
   
   @Override
   public Point getMilieu_2() { return null; }
   
   @Override
   public boolean estUneCourbe()
   {
      return false;
   }
}
