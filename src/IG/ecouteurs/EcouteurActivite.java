package IG.ecouteurs;

import IG.mondeIG.MondeIG;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class EcouteurActivite implements EventHandler<ActionEvent>
{
   protected MondeIG monde;
   
   /**
    * Instantiates a new Ecouteur activite.
    *
    * @param m the m
    */
   public EcouteurActivite(MondeIG m)
   {
      this.monde = m;
   }
   
   @Override
   public void handle(ActionEvent e)
   {
      this.monde.ajouter("Activité");
   }
}
