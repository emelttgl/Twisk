package twisk.monde;

public class SasEntree extends Activite
{
   /**
    * Construit le sas d'entrée
    */
   public SasEntree()
   {
      super("Entree");
   }
   
   @Override
   public String toString()
   {
      return this.nom + " = numéro : " + this.num + ", successeur - " + this.lesSucces.noms_etapes();
   }

   @Override
   public boolean estUneSortie()
   {
      return false;
   }
}
