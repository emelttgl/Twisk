package twisk.simulation;

import IG.outilsIG.ClientsColor;
import javafx.scene.paint.Color;
import twisk.monde.Etape;

public class Client
{
   protected Etape etape_courante;
   protected int numeroClient, rang;
   protected Color couleurCercle = ClientsColor.getInstance().getColor();
   
   /**
    * Instantiates a new Client.
    *
    * @param numero the numero
    */
   public Client(int numero)
    {
        this.numeroClient = numero;
    }
   
   /**
    * Aller a.
    *
    * @param e    the e
    * @param rang the rang
    */
   public void allerA(Etape e, int rang)
    {
        this.etape_courante = e;
        this.rang = rang;
    }
   
   /**
    * Gets etape courante.
    *
    * @return the etape courante
    */
   public Etape getEtape_courante() { return this.etape_courante; }
   
   /**
    * Gets rang.
    *
    * @return the rang
    */
   public int getRang() { return this.rang; }
   
   /**
    * Gets numero client.
    *
    * @return the numero client
    */
   public int getNumeroClient() { return this.numeroClient; }
   
   /**
    * Gets color.
    *
    * @return the color
    */
   public Color getColor() { return this.couleurCercle; }
}
