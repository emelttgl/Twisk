package twisk.monde;

import twisk.outils.FabriqueNumero;

public class Guichet extends Etape
{
   protected int nbjetons;
   protected int semaphore;
   
   /**
    * Construit un guichet à partir d'un nom
    *
    * @param nom Nom du guichet
    */
   public Guichet(String nom)
   {
      super(nom);
      this.nbjetons = 3;
      this.semaphore = FabriqueNumero.getInstance().getNumeroSemaphore();
   }
   
   /**
    * Construit un guichet à partir d'un nom et d'un nombre de jetons
    *
    * @param nom Nom du guichet
    * @param nb  Nombre de jetons
    */
   public Guichet(String nom, int nb)
   {
      super(nom);
      this.nbjetons = nb;
      this.semaphore = FabriqueNumero.getInstance().getNumeroSemaphore();
   }
   
   @Override
   public int getSemaphore()
   {
      return this.semaphore;
   }

   @Override
   public int getJetons()
   {
      return this.nbjetons;
   }

   @Override
   public String toString()
   {
      return this.nom + " = numéro : " + this.num + ", nbjetons : " + this.nbjetons + " successeur - " + this.lesSucces.noms_etapes();
   }
   
   @Override
   public boolean estUnGuichet()
   {
      return true;
   }
   
   @Override
   public String toC()
   {
      return "P(ids, sem_" + this.nom + ");\n"
              + "transfert(" + this.nom + ", " + this.lesSucces.getNom(0) + ");\n"
              + "delai(" + this.lesSucces.getTemps(0) + ", " + this.lesSucces.getEcartTemps(0) + ");\n"
              + "V(ids, sem_" + this.nom + ");\n\n"
              + this.lesSucces.successeur_toC(0);
   }
   
   @Override
   public String getTemps()
   {
      return null;
   }
   
   @Override
   public String getEcartTemps()
   {
      return null;
   }

   @Override
   public boolean estUneSortie()
   {
      return false;
   }
}