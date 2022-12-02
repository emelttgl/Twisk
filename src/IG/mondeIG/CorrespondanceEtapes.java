package IG.mondeIG;

import twisk.monde.Etape;

import java.util.HashMap;

public class CorrespondanceEtapes
{
   protected HashMap<EtapeIG, Etape> lesEtapes = new HashMap<>();
   
   /**
    * Ajouter.
    *
    * @param etig the etig
    * @param et   the et
    */
   public void ajouter(EtapeIG etig, Etape et)
   {
      this.lesEtapes.put(etig, et);
   }
   
   /**
    * Gets etape.
    *
    * @param e the e
    * @return the etape
    */
   public Etape getEtape(EtapeIG e) { return this.lesEtapes.get(e); }
}
