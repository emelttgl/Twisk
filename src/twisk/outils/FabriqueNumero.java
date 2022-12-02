package twisk.outils;

public class FabriqueNumero
{
   protected int cptEtape = -1;
   protected int cptSemaphore = 0;
   protected int cptLib = 0;
   private static final FabriqueNumero instance = new FabriqueNumero();
   
   /**
    * Retourne l'instance de la class FabriqueNumero
    *
    * @return L 'instance de FabriqueNumero
    */
   public static FabriqueNumero getInstance()
   {
      return instance;
   }
   
   /**
    * Retourne le numéro de l'étape et l'incrémente
    *
    * @return Entier correspondant au numéro de l'étape
    */
   public int getNumeroEtape()
   {
      return ++this.cptEtape;
   }
   
   /**
    * Incrémente le numéro de sémaphore et le retourne
    *
    * @return Entier correspondant au numéro de sémaphore
    */
   public int getNumeroSemaphore()
   {
      return ++this.cptSemaphore;
   }
   
   /**
    * Retourne le numéro de la librairie
    *
    * @return Entier correspondant au numéro de la librairie
    */
   public int getNumeroLib()
   {
      return this.cptLib;
   }
   
   /**
    * Remet à zéro les attributs de la class
    */
   public void newLib()
   {
      this.reset();
      ++this.cptLib;
   }
   
   /**
    * Reset.
    */
   public void reset()
   {
      this.cptEtape = -1;
      this.cptSemaphore = 0;
   }
}