package twisk.monde;

public class SasSortie extends Activite
{
   /**
    * Construit le sas de sortie
    */
   public SasSortie()
   {
      super("Sortie");
   }
   
   @Override
   public String toString()
   {
      return this.nom + " = numéro : " + this.num + ",successeur -";
   }
   
   @Override
   public String toC()
   {
      return "";
   }
   
   @Override
   public boolean estUneSortie()
   {
      return true;
   }
}
