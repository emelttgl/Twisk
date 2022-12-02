package IG.mondeIG;

import java.awt.*;

public class CourbeIG extends ArcIG
{
   protected Point[] pts = new Point[2];
   
   /**
    * Instantiates a new Courbe ig.
    *
    * @param pdc1 the pdc 1
    * @param pdc2 the pdc 2
    * @param p1   the p 1
    * @param p2   the p 2
    */
   public CourbeIG(PointDeControleIG pdc1, PointDeControleIG pdc2, Point p1, Point p2)
   {
      super(pdc1, pdc2);

      this.pts[0] = p1;
      this.pts[1] = p2;
   }
   
   @Override
   public Point getMilieu_1()
   {
      return this.pts[0];
   }
   
   @Override
   public Point getMilieu_2()
   {
      return this.pts[1];
   }
   
   @Override
   public boolean estUneCourbe()
   {
      return true;
   }
}
