package IG.ecouteurs;

import IG.mondeIG.MondeIG;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class EcouteurGuichet implements EventHandler<ActionEvent>
{
   protected MondeIG monde;
   
   /**
    * Instantiates a new Ecouteur guichet.
    *
    * @param m the m
    */
   public EcouteurGuichet(MondeIG m)
    {
        this.monde = m;
    }

    @Override
    public void handle(ActionEvent e)
    {
        this.monde.ajouter("Guichet");
    }
}
