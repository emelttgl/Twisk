package IG.outilsIG;

public class FabriqueIdentifiant
{
   protected static final FabriqueIdentifiant instance = new FabriqueIdentifiant();
   
   /**
    * Gets instance.
    *
    * @return the instance
    */
   public static FabriqueIdentifiant getInstance() { return instance; }
   
   protected int noEtape = -1;
   
   /**
    * Gets identifiant etape.
    *
    * @return the identifiant etape
    */
   public String getIdentifiantEtape() { return "" + ++this.noEtape; }
   
   /**
    * Sets identifiant etape.
    *
    * @param n the n
    */
   public void setIdentifiantEtape(int n) { this.noEtape = n; }
}
