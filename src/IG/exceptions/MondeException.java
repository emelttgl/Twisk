package IG.exceptions;

import javafx.animation.PauseTransition;
import javafx.scene.control.Alert;
import javafx.util.Duration;

public class MondeException extends Exception
{
   /**
    * Instantiates a new Monde exception.
    *
    * @param message the message
    */
   public MondeException(String message)
   {
      PauseTransition pause = new PauseTransition(Duration.seconds(5));
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("ERREUR");
      alert.headerTextProperty().bind(TwiskException.timeLeftAsString(pause));
      alert.setContentText("Erreur : " + message);
   
      pause.setOnFinished(event -> alert.close());
   
      alert.show();
      pause.play();
   }
}
