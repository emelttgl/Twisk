package IG.ecouteurs;

import IG.exceptions.TwiskException;
import IG.mondeIG.MondeIG;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;

public class EcouteurParamAct implements EventHandler<ActionEvent>
{
   protected MondeIG monde;
   
   /**
    * Instantiates a new Ecouteur param act.
    *
    * @param m the m
    */
   public EcouteurParamAct(MondeIG m)
    {
        this.monde = m;
    }

    @Override
    public void handle(ActionEvent e)
    {
        try
        {
            if(this.monde.getSelectArcSize() == 0)
            {
                if (this.monde.getSelectEtapeSize() < 2)
                {
                    if (this.monde.getSelectEtapeSize() != 0)
                    {
                        if(this.monde.selectEstUnGuichet())
                        {
                            throw new TwiskException("l'étape selectionné est un guichet.");
                        }
                        
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText("Les valeurs qui ne sont pas acceptées\nen paramètres sont :");
                        alert.setTitle("Attention");
                        alert.setContentText("- 0\n- Les entiers en-dessous de 0.\n- Les entiers strictement supérieurs à 10.\n- Les valeurs décimals.\n- Tout ce qui est différent d'un chiffre/nombre.");
                        alert.showAndWait();

                        TextInputDialog text = new TextInputDialog();

                        text.setTitle("On change les paramètres c'est parti !");
                        text.setHeaderText("Entrer le premier paramètre.");
                        text.setContentText("Nouveau temps :");

                        text.showAndWait();
                        this.monde.estUnInteger(text.getResult());

                        int temps = Integer.parseInt(text.getResult());

                        text.setHeaderText("Entrer le deuxième paramètre.");
                        text.setContentText("Nouvel écart temps :");

                        text.showAndWait();
                        this.monde.estUnInteger(text.getResult());

                        this.monde.changer_delaitemps(temps, Integer.parseInt(text.getResult()));
                        this.monde.notifierObservateurs();
                    }
                    else
                    {
                        throw new TwiskException("aucune étape est sélectionné.");
                    }
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
        {

        }
    }
}
