package IG.vues;

import IG.ecouteurs.EcouteurActivite;
import IG.ecouteurs.EcouteurGuichet;
import IG.ecouteurs.EcouteurSimuler;
import IG.mondeIG.MondeIG;
import IG.outilsIG.TailleComposants;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class VueOutils extends TilePane implements Observateur
{
   protected MondeIG monde;
   protected Button activite = new Button("Activite"), guichet = new Button("Guichet"), lancerSimulation = new Button();
   protected Circle[] aides_circle = new Circle[4];
   protected Label[] aides_label = new Label[4];
   protected ImageView play = new ImageView(new Image(String.valueOf(VueOutils.class.getResource("/play.png"))));
   protected ImageView stop = new ImageView(new Image(String.valueOf(VueOutils.class.getResource("/stop.jpg"))));
   
   
   /**
    * Instantiates a new Vue outils.
    *
    * @param m the m
    */
   public VueOutils(MondeIG m)
   {
      this.monde = m;
      this.monde.ajouterObservateur(this);

      this.activite.setOnAction(new EcouteurActivite(this.monde));
      this.activite.setMinHeight(50);
      this.activite.setMinWidth(75);
      this.activite.setTooltip(new Tooltip("Ajouter une activité au monde."));
      this.activite.setStyle("-fx-border-color : blue;" + "-fx-border-width: 2 2 2 2;");
      
      this.guichet.setOnAction(new EcouteurGuichet(this.monde));
      this.guichet.setMinHeight(50);
      this.guichet.setMinWidth(75);
      this.guichet.setTooltip(new Tooltip("Ajouter un guichet au monde."));
      this.guichet.setStyle("-fx-border-color : green;" + "-fx-border-width: 2 2 2 2;");
      
      this.play.setFitWidth(50);
      this.play.setFitHeight(50);
   
      this.stop.setFitWidth(62.5);
      this.stop.setFitHeight(50);
      
      this.lancerSimulation.setGraphic(this.play);
      this.lancerSimulation.setOnAction(new EcouteurSimuler(this.monde));
      this.lancerSimulation.setMinHeight(50);
      this.lancerSimulation.setMinWidth(75);
      this.lancerSimulation.setTooltip(new Tooltip("Lancer la simulation."));
      this.lancerSimulation.setStyle("-fx-border-color : red;" + "-fx-border-width: 2 2 2 2;");

      this.aides_circle[0] = new Circle();
      this.aides_circle[0].setRadius(TailleComposants.getInstance().getRadius());
      this.aides_circle[0].setFill(Color.BLUE);
      this.aides_label[0] = new Label("Ni Entrée ni Sortie", this.aides_circle[0]);

      this.aides_circle[1] = new Circle();
      this.aides_circle[1].setRadius(TailleComposants.getInstance().getRadius());
      this.aides_circle[1].setFill(Color.GREEN);
      this.aides_label[1] = new Label(" Entrée", this.aides_circle[1]);

      this.aides_circle[2] = new Circle();
      this.aides_circle[2].setRadius(TailleComposants.getInstance().getRadius());
      this.aides_circle[2].setFill(Color.RED);
      this.aides_label[2] = new Label(" Sortie", this.aides_circle[2]);

      this.aides_circle[3] = new Circle();
      this.aides_circle[3].setRadius(TailleComposants.getInstance().getRadius());
      this.aides_circle[3].setFill(Color.YELLOW);
      this.aides_label[3] = new Label(" Entrée et Sortie", this.aides_circle[3]);
      
      this.getChildren().addAll(this.aides_label[0], this.aides_label[1], this.activite, this.lancerSimulation, this.guichet, this.aides_label[2], this.aides_label[3]);
      this.setAlignment(Pos.CENTER);
      this.setStyle("-fx-font : 16 arial;");
   }
   
   @Override
   public void reagir()
   {
      if(this.monde.getEstLancee())
      {
         this.lancerSimulation.setGraphic(this.stop);
         this.activite.setDisable(true);
         this.guichet.setDisable(true);
      }
      else
      {
         this.lancerSimulation.setGraphic(this.play);
         this.activite.setDisable(false);
         this.guichet.setDisable(false);
      }
   }
}