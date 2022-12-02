package IG.mondeIG;

public class ActiviteIG extends EtapeIG
{
   protected int temps = 4, ecart_temps = 2;
   protected boolean actRestreinte = false;
   
   /**
    * Instantiates a new Activite ig.
    *
    * @param nom the nom
    * @param id  the id
    * @param l   the l
    * @param h   the h
    */
   public ActiviteIG(String nom, String id, int l, int h) { super(nom, id, l, h); }
   
   public int getTemps() { return this.temps; }
   public void setTemps(int t) { this.temps = t; }
   
   
   public int getEcart_temps() { return this.ecart_temps; }
   public void setEcart_temps(int e) { this.ecart_temps = e; }
   
   @Override
   public void setActRestreinte() { this.actRestreinte = !this.actRestreinte; }
   
   @Override
   public boolean estUneActivite() { return true; }
}
