package IG.ecouteurs;

import IG.exceptions.TwiskException;
import IG.mondeIG.MondeIG;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputDialog;

public class EcouteurRenommer implements EventHandler<ActionEvent>
{
   protected MondeIG monde;
   
   /**
    * Instantiates a new Ecouteur renommer.
    *
    * @param m the m
    */
   public EcouteurRenommer(MondeIG m)
    {
        this.monde = m;
    }

    @Override
    public void handle(ActionEvent event)
    {
        try
        {
            if(this.monde.getSelectArcSize() == 0)
            {
                if (this.monde.getSelectEtapeSize() < 2)
                {
                    if (this.monde.getSelectEtapeSize() == 0)
                    {
                        throw new TwiskException("aucune étape est sélectionné.");
                    }
                    TextInputDialog text = new TextInputDialog();
                    text.setTitle("On renomme c'est parti !");
                    text.setHeaderText("Entrer le nouveau nom.");
                    text.showAndWait();

                    this.monde.renommer_select(text.getResult());
                    this.monde.notifierObservateurs();
                }
                else
                {
                    throw new TwiskException("il y a plus d'une étape sélectionné.");
                }
            }
            else
            {
                throw new TwiskException("des arcs sont sélectionné.");
            }
        }
        catch(TwiskException ignored)
        {}
    }
}
