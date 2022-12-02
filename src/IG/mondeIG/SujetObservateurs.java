package IG.mondeIG;

import IG.vues.Observateur;

import java.util.ArrayList;

public class SujetObservateurs
{
   protected ArrayList<Observateur> obs = new ArrayList<>();
   
   /**
    * Ajouter observateur.
    *
    * @param o the o
    */
   public void ajouterObservateur(Observateur o) { this.obs.add(o); }
   
   /**
    * Notifier observateurs.
    */
   public void notifierObservateurs()
   {
      for(Observateur o : this.obs)
      {
         o.reagir();
      }
   }
}
