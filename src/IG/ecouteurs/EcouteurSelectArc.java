package IG.ecouteurs;

import IG.mondeIG.ArcIG;
import IG.mondeIG.MondeIG;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class EcouteurSelectArc implements EventHandler<MouseEvent>
{
   protected MondeIG monde;
   protected ArcIG arc;
   
   /**
    * Instantiates a new Ecouteur select arc.
    *
    * @param m the m
    * @param a the a
    */
   public EcouteurSelectArc(MondeIG m, ArcIG a)
    {
        this.monde = m;
        this.arc = a;
    }

    @Override
    public void handle(MouseEvent mouseEvent)
    {
        if(!this.monde.getEstLancee())
        {
            this.arc.setSelect();
            this.arc.setPressed();
            this.monde.add_rm_SelectArc(this.arc);
            this.monde.notifierObservateurs();
        }
    }
}
