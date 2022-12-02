package IG;

import IG.mondeIG.MondeIG;
import IG.outilsIG.MondeEnregistre;
import IG.vues.VueMenu;
import IG.vues.VueMondeIG;
import IG.vues.VueOutils;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainTwisk extends Application
{
   @Override
   public void start(Stage primaryStage)
   {
      MondeIG monde = new MondeIG();
      BorderPane root = new BorderPane();
      
      root.setBottom(new VueOutils(monde));
      root.setCenter(new VueMondeIG(monde));
      root.setTop(new VueMenu(monde));
      
      primaryStage.setTitle("Twisk");
      primaryStage.setScene(new Scene(root, 1250, 1000));
      primaryStage.setOnCloseRequest(event -> {
         monde.killProc();
         MondeEnregistre.getInstance().close();
      });
      primaryStage.show();
   }
   
   public static void main(String[] args)
   {
      launch();
   }
}
