package IG.ecouteurs;

import IG.mondeIG.EtapeIG;
import IG.mondeIG.MondeIG;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class EcouteurSelectEtape implements EventHandler<MouseEvent>
{
   protected MondeIG monde;
   protected EtapeIG eta;
   
   /**
    * Instantiates a new Ecouteur select etape.
    *
    * @param m the m
    * @param e the e
    */
   public EcouteurSelectEtape(MondeIG m, EtapeIG e)
    {
        this.monde = m;
        this.eta = e;
    }

    @Override
    public void handle(MouseEvent mouseEvent)
    {
        if(!this.monde.getEstLancee())
        {
            this.eta.setSelect();
            this.eta.setPressed();
            this.monde.add_rm_SelectEtape(this.eta);
            this.monde.notifierObservateurs();
        }
    }
}
