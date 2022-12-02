package IG.vues;

import IG.ecouteurs.EcouteurSelectArc;
import IG.mondeIG.CourbeIG;
import IG.mondeIG.MondeIG;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;

public class VueCourbeIG extends VueArcIG
{
   protected CourbeIG courbe;
   
   /**
    * Instantiates a new Vue courbe ig.
    *
    * @param m        the m
    * @param courbeIG the courbe ig
    */
   public VueCourbeIG(MondeIG m, CourbeIG courbeIG)
   {
      super(m);
      this.courbe = courbeIG;

      CubicCurve curve = new CubicCurve();
      
      curve.setStartX(this.courbe.getDeb().getPosX());
      curve.setStartY(this.courbe.getDeb().getPosY());
      
      curve.setControlX1(this.courbe.getMilieu_1().getX());
      curve.setControlY1(this.courbe.getMilieu_1().getY());
      
      curve.setControlX2(this.courbe.getMilieu_2().getX());
      curve.setControlY2(this.courbe.getMilieu_2().getY());
      
      curve.setEndX(this.courbe.getFin().getPosX());
      curve.setEndY(this.courbe.getFin().getPosY());

      curve.setFill(Color.TRANSPARENT);
      curve.setStroke(Color.BLACK);
      curve.setStrokeWidth(4);
      
      this.getChildren().add(curve);
      this.setPolyline(this.courbe.getMilieu_2().getX(), this.courbe.getMilieu_2().getY(), this.courbe.getFin().getPosX(), this.courbe.getFin().getPosY());
      
      this.setOnMouseClicked(new EcouteurSelectArc(this.monde, this.courbe));

      if(this.courbe.getSelect())
      {
         curve.setStroke(Color.BLUE);
      }
   }
   
   @Override
   public void reagir() { }
}
