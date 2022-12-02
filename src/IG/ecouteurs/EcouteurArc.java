package IG.ecouteurs;

import IG.exceptions.TwiskException;
import IG.mondeIG.MondeIG;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class EcouteurArc implements EventHandler<MouseEvent>
{
   protected MondeIG monde;
   
   /**
    * Instantiates a new Ecouteur arc.
    *
    * @param m the m
    */
   public EcouteurArc(MondeIG m)
   {
      this.monde = m;
   }
   
   @Override
   public void handle(MouseEvent mouseEvent)
   {
      try
      {
         if(!this.monde.getEstLancee())
         {
            this.monde.addPt(mouseEvent.getX(), mouseEvent.getY());
         }
      }
      catch(TwiskException ignored)
      {

      }
   }
}
