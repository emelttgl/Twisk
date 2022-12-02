package IG.vues;

import IG.ecouteurs.EcouteurSelectArc;
import IG.mondeIG.LigneDroiteIG;
import IG.mondeIG.MondeIG;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class VueLigneDroiteIG extends VueArcIG
{
   protected LigneDroiteIG ligne;
   
   /**
    * Instantiates a new Vue ligne droite ig.
    *
    * @param m       the m
    * @param ligneIG the ligne ig
    */
   public VueLigneDroiteIG(MondeIG m, LigneDroiteIG ligneIG)
   {
      super(m);

      this.ligne = ligneIG;

      Line line = new Line(this.ligne.getDeb().getPosX(), this.ligne.getDeb().getPosY(), this.ligne.getFin().getPosX(), this.ligne.getFin().getPosY());
      line.setStroke(Color.BLACK);
      line.setStrokeWidth(4);
      
      this.getChildren().add(line);
      this.setPolyline(this.ligne.getDeb().getPosX(), this.ligne.getDeb().getPosY(), this.ligne.getFin().getPosX(), this.ligne.getFin().getPosY());

      this.setOnMouseClicked(new EcouteurSelectArc(this.monde, this.ligne));

      if(this.ligne.getSelect())
      {
         line.setStroke(Color.BLUE);
      }
   }
   
   @Override
   public void reagir() { }
}
