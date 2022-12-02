package IG.vues;

import IG.mondeIG.ActiviteIG;
import IG.mondeIG.MondeIG;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.util.Random;

public class VueActiviteIG extends VueEtapeIG
{
   protected Pane vueClients = new Pane();
   
   /**
    * Instantiates a new Vue activite ig.
    *
    * @param m the m
    * @param a the a
    */
   public VueActiviteIG(MondeIG m, ActiviteIG a)
   {
      super(m, a);
      
      this.visuel.setTooltip(new Tooltip(a.getTemps() + " ± " + a.getEcart_temps() + " temps"));
      
      this.vueClients.setStyle("-fx-border-width: 1 0 0 0; -fx-border-color: blue; -fx-background-insets: 0 0 -1 0, 0, 1, 2; -fx-background-radius: 3px, 3px, 2px, 1px;");
   
      this.setStyle("-fx-border-color : blue; -fx-border-width: 2.5 2.5 2.5 2.5; -fx-font : 18 arial;");
      this.getChildren().add(this.vueClients);
      
      if(this.etape.getSelect())
      {
         this.setStyle("-fx-border-color : black; -fx-border-width: 2.5 2.5 2.5 2.5; -fx-font : 18 arial;");
      }
   }
   
   @Override
   public void addToPane(Circle c)
   {
      Random r = new Random();
      c.setCenterX(this.vueClients.getLayoutX() + r.nextInt(250) + 6);
      c.setCenterY(this.vueClients.getLayoutY() + r.nextInt(75) + 31);
      this.vueClients.getChildren().add(c);
   }
   
   @Override
   public void reagir() {}
}
