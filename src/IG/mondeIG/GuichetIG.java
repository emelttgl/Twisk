package IG.mondeIG;

public class GuichetIG extends EtapeIG
{
   protected int jetons = 2, sens = 0;
   
   /**
    * Instantiates a new Guichet ig.
    *
    * @param nom the nom
    * @param id  the id
    * @param l   the l
    * @param h   the h
    */
   public GuichetIG(String nom, String id, int l, int h) { super(nom, id, l, h); }
    
    public int getJetons() { return this.jetons; }
    
    public void setJetons(int j) { this.jetons=j; }
    
    public int getSens() { return this.sens; }
    
    public void setSens(int s) { this.sens = s; }

    @Override
    public boolean estUnGuichet() { return true; }
}
