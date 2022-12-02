package IG.ecouteurs;

import IG.exceptions.MondeException;
import IG.mondeIG.MondeIG;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twisk.outils.FabriqueNumero;

public class EcouteurSimuler implements EventHandler<ActionEvent>
{
   protected MondeIG monde;
   
   /**
    * Instantiates a new Ecouteur simuler.
    *
    * @param m the m
    */
   public EcouteurSimuler(MondeIG m) { this.monde = m; }
   
   @Override
   public void handle(ActionEvent actionEvent)
   {
      if(!this.monde.getEstLancee())
      {
         try
         {
            this.monde.simuler();
         }
         catch(MondeException ignored)
         {}
      }
      else
      {
         this.monde.killProc();
         this.monde.setEstLancee(false);
         FabriqueNumero.getInstance().newLib();
         this.monde.notifierObservateurs();
      }
      
   }
}
