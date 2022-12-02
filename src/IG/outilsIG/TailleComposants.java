package IG.outilsIG;

public class TailleComposants
{
   private static final TailleComposants instance = new TailleComposants();
   
   protected int largeur = 275, hauteur = 150;
   protected double radius = 6;
   
   /**
    * Gets instance.
    *
    * @return the instance
    */
   public static TailleComposants getInstance() {
      return instance;
   }
   
   /**
    * Gets largeur.
    *
    * @return the largeur
    */
   public int getLargeur()
   {
      return this.largeur;
   }
   
   /**
    * Gets hauteur.
    *
    * @return the hauteur
    */
   public int getHauteur()
   {
      return this.hauteur;
   }
   
   /**
    * Gets radius.
    *
    * @return the radius
    */
   public double getRadius()
   {
      return this.radius;
   }
}