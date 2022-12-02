package IG.outilsIG;

import javafx.scene.paint.Color;

public class ClientsColor
{
   private static final ClientsColor instance = new ClientsColor();
   
   /**
    * Gets instance.
    *
    * @return the instance
    */
   public static ClientsColor getInstance() { return instance; }
   
   protected Color[] lesCouleurs = new Color[10];
   protected int indice = -1;
   
   private ClientsColor()
   {
      this.lesCouleurs[0] = Color.DARKRED;
      this.lesCouleurs[1] = Color.RED;
      this.lesCouleurs[2] = Color.DARKGREEN;
      this.lesCouleurs[3] = Color.DARKBLUE;
      this.lesCouleurs[4] = Color.GREEN;
      this.lesCouleurs[5] = Color.ORANGE;
      this.lesCouleurs[6] = Color.VIOLET;
      this.lesCouleurs[7] = Color.YELLOWGREEN;
      this.lesCouleurs[8] = Color.AQUA;
      this.lesCouleurs[9] = Color.BLUEVIOLET;
   }
   
   /**
    * Gets color.
    *
    * @return the color
    */
   public Color getColor()
   {
      if(this.indice != 10)
      {
         return this.lesCouleurs[++this.indice];
      }
      this.indice = -1;
      return this.getColor();
      
   }
}
