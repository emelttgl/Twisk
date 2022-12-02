package IG.vues;

import IG.mondeIG.GuichetIG;
import IG.mondeIG.MondeIG;
import javafx.geometry.Pos;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class VueGuichetIG extends VueEtapeIG
{
   protected HBox laFileHaute = new HBox(), laFileBasse = new HBox();
   protected Pane[] emplacementFile = new Pane[10];
   protected int emplacement, sens;
   
   /**
    * Instantiates a new Vue guichet ig.
    *
    * @param m the m
    * @param g the g
    */
   public VueGuichetIG(MondeIG m, GuichetIG g)
   {
      super(m, g);
   
      this.sens = g.getSens();
      this.visuel.setTooltip(new Tooltip(g.getJetons() + " jetons"));
      
      for(int i = 0;i < 10;i++)
      {
         this.emplacementFile[i] = new Pane();
         this.emplacementFile[i].setMinSize(45, 45);
         this.emplacementFile[i].setMaxSize(45, 45);
         this.emplacementFile[i].setStyle("-fx-border-width: 1 1 1 1; -fx-border-color: green; -fx-background-insets: 0 0 -1 0, 0, 1, 2; -fx-background-radius: 3px, 3px, 2px, 1px;");
         if(i < 5)
         {
            this.laFileHaute.getChildren().add(this.emplacementFile[i]);
         }
         else
         {
            this.laFileBasse.getChildren().add(this.emplacementFile[i]);
         }
      }
   
      this.laFileHaute.setMinSize(this.etape.getLargeur(), 45);
      this.laFileHaute.setAlignment(Pos.CENTER);
      this.laFileHaute.setSpacing(5);
   
      this.laFileBasse.setMinSize(this.etape.getLargeur(), 45);
      this.laFileBasse.setAlignment(Pos.CENTER);
      this.laFileBasse.setSpacing(5);
      
      this.setStyle("-fx-border-color : green; -fx-border-width: 2.5 2.5 2.5 2.5; -fx-font : 18 arial;");
      this.getChildren().addAll(this.laFileHaute, this.laFileBasse);
      
      if(this.sens == 1)
      {
         this.emplacement = 0;
      }
      else if(this.sens == -1)
      {
         this.emplacement = 9;
      }
      
      if(this.etape.getSelect())
      {
         this.setStyle("-fx-border-color : black; -fx-border-width: 2.5 2.5 2.5 2.5; -fx-font : 18 arial;");
      }
   }
   
   @Override
   public void addToPane(Circle c)
   {
      c.setCenterX(22.5);
      c.setCenterY(22.5);
      this.emplacementFile[this.emplacement].getChildren().add(c);
      this.emplacement += this.sens;
   }

   @Override
   public void reagir() {}
}
