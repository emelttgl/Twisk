package IG.vues;

import IG.mondeIG.MondeIG;
import IG.mondeIG.PointDeControleIG;
import IG.outilsIG.TailleComposants;
import javafx.scene.shape.Circle;

public class VuePointDeControleIG extends Circle implements Observateur
{
   protected MondeIG monde;
   protected PointDeControleIG point;
   
   /**
    * Instantiates a new Vue point de controle ig.
    *
    * @param m the m
    * @param p the p
    */
   public VuePointDeControleIG(MondeIG m, PointDeControleIG p)
   {
      this.monde = m;
      
      this.point = p;
      this.setCenterX(p.getPosX());
      this.setCenterY(p.getPosY());
      this.setRadius(TailleComposants.getInstance().getRadius());

      this.setOnMouseClicked(event -> this.point.setPressed());
   }
   
   @Override
   public void reagir() { }
}
