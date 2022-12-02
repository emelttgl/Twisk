package twisk.monde;

public class ActiviteRestreinte extends Activite
{
   /**
    * Construit une activité restreinte à partir d'un nom
    *
    * @param nom Nom de l'acitivité restreinte
    */
   public ActiviteRestreinte(String nom)
   {
      super(nom);
   }
   
   /**
    * Construit une activité restreinte à partir d'un nom, d'un temp et d'un écart temps
    *
    * @param nom Nom de l'activité
    * @param t   Temps pour les delai en C
    * @param e   Ecart temps pour les delai en C
    */
   public ActiviteRestreinte(String nom, int t, int e)
   {
      super(nom, t, e);
   }
   
   @Override
   public String toString()
   {
      return this.nom + " = numéro : " + this.num + ", temps : " + this.temps + ", ecartTemps : " + this.ecartTemps + ", successeur - " + this.lesSucces.noms_etapes();
   }

   @Override
   public boolean estUneSortie()
   {
      return false;
   }
}
