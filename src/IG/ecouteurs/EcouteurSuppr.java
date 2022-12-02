package IG.ecouteurs;

import IG.mondeIG.MondeIG;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class EcouteurSuppr implements EventHandler<ActionEvent>
{
   protected MondeIG monde;
   
   /**
    * Instantiates a new Ecouteur suppr.
    *
    * @param m the m
    */
   public EcouteurSuppr(MondeIG m)
    {
        this.monde = m;
    }

    @Override
    public void handle(ActionEvent event)
    {
        this.monde.supprimer_select();
    }
}
