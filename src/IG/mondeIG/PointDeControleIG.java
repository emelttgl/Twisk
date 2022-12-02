package IG.mondeIG;

public class PointDeControleIG
{
   protected double posX, posY;
   protected String identifiant;
   protected boolean pressed = false;
   
   /**
    * Instantiates a new Point de controle ig.
    *
    * @param x  the x
    * @param y  the y
    * @param id the id
    */
   public PointDeControleIG(double x, double y, String id)
   {
      this.posX = x;
      this.posY = y;
      
      this.identifiant = id;
   }
   
   /**
    * Gets pos x.
    *
    * @return the pos x
    */
   public double getPosX()
   {
      return this.posX;
   }
   
   /**
    * Gets pos y.
    *
    * @return the pos y
    */
   public double getPosY()
   {
      return this.posY;
   }
   
   /**
    * Relocate.
    *
    * @param x the x
    * @param y the y
    */
   public void relocate(double x, double y)
   {
      this.posX = x;
      this.posY = y;
   }
   
   /**
    * Gets id.
    *
    * @return the id
    */
   public String getId()
   {
      return this.identifiant;
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
   
   /**
    * Sets pressed.
    */
   public void setPressed()
   {
      this.pressed = !this.pressed;
   }
}
