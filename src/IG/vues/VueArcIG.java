package IG.vues;

import IG.mondeIG.MondeIG;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;

public abstract class VueArcIG extends Pane implements Observateur
{
   protected MondeIG monde;
   protected Polyline polyline = new Polyline();
   
   /**
    * Instantiates a new Vue arc ig.
    *
    * @param m the m
    */
   public VueArcIG(MondeIG m)
   {
      this.monde = m;
      
      this.setPickOnBounds(false);
   }
   
   /**
    * Sets polyline.
    *
    * @param debX the deb x
    * @param debY the deb y
    * @param finX the fin x
    * @param finY the fin y
    */
   public void setPolyline(double debX, double debY, double finX, double finY)
   {
      double incli = (debY - finY)/(debX - finX);
      double ang = Math.atan(incli);
      double fleche = debX > finX ? Math.toRadians(30) : -Math.toRadians(220);
      double fleche_lgth = 15;
   
      double pt1_x = finX + fleche_lgth * Math.cos(ang - fleche), pt2_x = finX + fleche_lgth * Math.cos(ang + fleche);
      double pt1_y = finY + fleche_lgth * Math.sin(ang - fleche), pt2_y = finY + fleche_lgth * Math.sin(ang + fleche);
   
      this.polyline.getPoints().addAll(finX, finY, pt1_x, pt1_y, pt2_x, pt2_y, finX, finY);
      this.polyline.setStrokeWidth(2.5);
      this.polyline.setFill(Color.BLACK);
      
      this.getChildren().add(this.polyline);
   }
}
