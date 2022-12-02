package IG.vues;

import IG.ecouteurs.EcouteurSelectEtape;
import IG.mondeIG.EtapeIG;
import IG.mondeIG.MondeIG;
import IG.outilsIG.TailleComposants;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import twisk.simulation.Client;

public abstract class VueEtapeIG extends VBox implements Observateur
{
   protected MondeIG monde;
   protected EtapeIG etape;
   protected Label visuel;
   protected Circle entree_sortie = new Circle();
   
   /**
    * Instantiates a new Vue etape ig.
    *
    * @param m the m
    * @param e the e
    */
   public VueEtapeIG(MondeIG m, EtapeIG e)
   {
      this.monde = m;
      this.etape = e;
   
      this.visuel = new Label(this.etape.getNom());
      this.visuel.setPadding(new Insets(2));
      this.visuel.setMinWidth(this.etape.getLargeur());
      this.visuel.setMinHeight(25);
      this.visuel.setAlignment(Pos.CENTER);
      
      this.setMinSize(this.etape.getLargeur(), this.etape.getHauteur());
      this.setMaxSize(this.etape.getLargeur(), this.etape.getHauteur());
      
      this.setSpacing(10);

      this.getChildren().add(this.visuel);

      this.setOnMouseClicked(new EcouteurSelectEtape(this.monde, this.etape));

      this.setOnDragDetected(dragEvent -> {
         if(!this.monde.getEstLancee())
         {
            Dragboard db = this.startDragAndDrop(TransferMode.ANY);
            ClipboardContent cbc = new ClipboardContent();
   
            WritableImage capture = this.snapshot(null, null);
            cbc.putImage(capture);
   
            db.setContent(cbc);
            dragEvent.consume();
         }
      });

      this.entree_sortie.setCenterX(this.etape.getPosX());
      this.entree_sortie.setCenterY(this.etape.getPosY());
      this.entree_sortie.setRadius(TailleComposants.getInstance().getRadius());
      
   }
   
   /**
    * Sets vue.
    *
    * @param x the x
    * @param y the y
    */
   public void setVue(double x, double y)
   {
      this.etape.setPoints(x, y);
   }
   
   /**
    * Gets entree sortie.
    *
    * @return the entree sortie
    */
   public Circle getEntree_sortie()
   {
      return this.entree_sortie;
   }
   
   /**
    * Sets entree sortie color.
    */
   public void setEntreeSortieColor()
   {
      if(this.etape.getEntree() && this.etape.getSortie())
      {
         this.entree_sortie.setFill(Color.YELLOW);
      }
      else if(this.etape.getEntree())
      {
         this.entree_sortie.setFill(Color.GREEN);
      }
      else if(this.etape.getSortie())
      {
         this.entree_sortie.setFill(Color.RED);
      }
      else
      {
         this.entree_sortie.setFill(Color.BLUE);
      }
   }
   
   /**
    * Add client.
    *
    * @param c the c
    */
   public void addClient(Client c)
   {
      this.etape.addClient(c);
   }
   
   /**
    * Add to pane.
    *
    * @param c the c
    */
   public abstract void addToPane(Circle c);
}