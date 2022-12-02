package IG.mondeIG;

import java.awt.*;

public abstract class ArcIG
{
   protected PointDeControleIG[] pdc = new PointDeControleIG[2];
   protected boolean select = false, pressed = false;
   
   /**
    * Instantiates a new Arc ig.
    *
    * @param pt1 the pt 1
    * @param pt2 the pt 2
    */
   public ArcIG(PointDeControleIG pt1, PointDeControleIG pt2)
   {
      this.pdc[0] = pt1;
      this.pdc[1] = pt2;
   }
   
   /**
    * Gets deb.
    *
    * @return the deb
    */
   public PointDeControleIG getDeb()
   {
      return this.pdc[0];
   }
   
   /**
    * Gets fin.
    *
    * @return the fin
    */
   public PointDeControleIG getFin()
   {
      return this.pdc[1];
   }
   
   /**
    * Gets milieu 1.
    *
    * @return the milieu 1
    */
   public abstract Point getMilieu_1();
   
   /**
    * Gets milieu 2.
    *
    * @return the milieu 2
    */
   public abstract Point getMilieu_2();
   
   /**
    * Est une courbe boolean.
    *
    * @return the boolean
    */
   public abstract boolean estUneCourbe();
   
   /**
    * Sets select.
    */
   public void setSelect()
   {
      this.select = !this.select;
   }
   
   /**
    * Gets select.
    *
    * @return the select
    */
   public boolean getSelect()
   {
      return this.select;
   }
   
   /**
    * Sets pressed.
    */
   public void setPressed()
   {
      this.pressed = !this.pressed;
   }
   
   /**
    * Gets pressed.
    *
    * @return the pressed
    */
   public boolean getPressed()
   {
      return this.pressed;
   }
}
