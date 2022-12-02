package IG.exceptions;

import javafx.animation.Animation;
import javafx.animation.PauseTransition;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.scene.control.Alert;
import javafx.util.Duration;

public class TwiskException extends Exception
{
   /**
    * Instantiates a new Twisk exception.
    *
    * @param message the message
    */
   public TwiskException(String message)
   {
      PauseTransition pause = new PauseTransition(Duration.seconds(5));
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("ERREUR");
      alert.headerTextProperty().bind(timeLeftAsString(pause));
      alert.setContentText("Erreur : " + message);
   
      pause.setOnFinished(event -> alert.close());
      
      alert.show();
      pause.play();
   }
   
   /**
    * Time left as string string binding.
    *
    * @param animation the animation
    * @return the string binding
    */
   public static StringBinding timeLeftAsString(Animation animation)
   {
      return Bindings.createStringBinding(
              () ->
              {
                 double currentTime = animation.getCurrentTime().toMillis();
                 double totalTime = animation.getCycleDuration().toMillis();
                 long remainingTime = Math.round(totalTime - currentTime);
                 // java.time.Duration
                 java.time.Duration dur = java.time.Duration.ofMillis(remainingTime);
                 return String.format(
                         "%02d:%03d", dur.toSecondsPart(), dur.toMillisPart());
              },
              animation.currentTimeProperty(),
              animation.cycleDurationProperty());
   }
}
