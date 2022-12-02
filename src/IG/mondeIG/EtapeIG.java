package IG.mondeIG;

import twisk.simulation.Client;

import java.util.*;

public abstract class EtapeIG implements Iterable<PointDeControleIG>
{
   protected String nom, identifiant;
   protected double posX, posY;
   protected int largeur, hauteur;

   protected PointDeControleIG[] points = new PointDeControleIG[4];
   protected boolean select = false, pressed = false, entree = false, sortie = false;
   protected GestionnaireEtapeIG lesSuccess;
   protected ArrayList<Client> lesClients = new ArrayList<>();
   
   /**
    * Instantiates a new Etape ig.
    *
    * @param nom the nom
    * @param id  the id
    * @param l   the l
    * @param h   the h
    */
   public EtapeIG(String nom, String id, int l, int h)
   {
      this.nom = nom;
      this.identifiant = id;
      
      this.largeur = l;
      this.hauteur = h;
   
      Random r = new Random();
      this.posX = r.nextInt(900) + 25;
      this.posY = r.nextInt(700) + 25;

      //Haut
      this.points[0] = new PointDeControleIG((this.largeur/2) + this.posX, this.posY - 4, this.identifiant);
      //Bas
      this.points[1] = new PointDeControleIG((this.largeur/2) + this.posX, this.hauteur + this.posY + 4, this.identifiant);
      //Droite
      this.points[2] = new PointDeControleIG(this.largeur + this.posX + 3, (this.hauteur/2) + this.posY, this.identifiant);
      //Gauche
      this.points[3] = new PointDeControleIG(this.posX - 4, (this.hauteur/2) + this.posY, this.identifiant);

      this.lesSuccess= new GestionnaireEtapeIG();
   }
   
   /**
    * Sets points.
    *
    * @param x the x
    * @param y the y
    */
   public void setPoints(double x, double y)
   {
      this.posX = x;
      this.posY = y;
      
      //Haut
      this.points[0].relocate((this.largeur/2) + this.posX, this.posY - 4);
      //Bas
      this.points[1].relocate((this.largeur/2) + this.posX, this.hauteur + this.posY + 4);
      //Droite
      this.points[2].relocate(this.largeur + this.posX + 3, (this.hauteur/2) + this.posY);
      //Gauche
      this.points[3].relocate(this.posX - 4, (this.hauteur/2) + this.posY);
   }
   
   
   /**
    * Gets nom.
    *
    * @return the nom
    */
   public String getNom()
   {
      return this.nom;
   }
   
   /**
    * Sets nom.
    *
    * @param n the n
    */
   public void setNom(String n)
   {
      this.nom = n;
   }
   
   
   /**
    * Gets pos x.
    *
    * @return the pos x
    */
   public double getPosX()
   {
      return this.posX;
   }
   
   /**
    * Gets pos y.
    *
    * @return the pos y
    */
   public double getPosY()
   {
      return this.posY;
   }
   
   
   /**
    * Gets largeur.
    *
    * @return the largeur
    */
   public int getLargeur()
   {
      return this.largeur;
   }
   
   
   /**
    * Gets hauteur.
    *
    * @return the hauteur
    */
   public int getHauteur()
   {
      return this.hauteur;
   }
   
   
   /**
    * Gets select.
    *
    * @return the select
    */
   public boolean getSelect()
   {
      return this.select;
   }
   
   /**
    * Sets select.
    */
   public void setSelect()
   {
      this.select = !this.select;
   }
   
   
   /**
    * Sets pressed.
    */
   public void setPressed()
   {
      this.pressed = !this.pressed;
   }
   
   /**
    * Gets pressed.
    *
    * @return the pressed
    */
   public boolean getPressed()
   {
      return this.pressed;
   }
   
   
   /**
    * Sets entree.
    */
   public void setEntree()
   {
      this.entree = !this.entree;
   }
   
   /**
    * Gets entree.
    *
    * @return the entree
    */
   public boolean getEntree()
   {
      return this.entree;
   }
   
   
   /**
    * Sets sortie.
    */
   public void setSortie()
   {
      this.sortie = !this.sortie;
   }
   
   /**
    * Gets sortie.
    *
    * @return the sortie
    */
   public boolean getSortie()
   {
      return this.sortie;
   }
   
   
   /**
    * Gets id.
    *
    * @return the id
    */
   public String getId()
   {
      return this.identifiant;
   }
   
   
   /**
    * Est un guichet boolean.
    *
    * @return the boolean
    */
   public boolean estUnGuichet()
   {
      return false;
   }
   
   /**
    * Est une acticite restreinte boolean.
    *
    * @return the boolean
    */
   public boolean estUneActiciteRestreinte()
   {
      return false;
   }
   
   /**
    * Est une activite boolean.
    *
    * @return the boolean
    */
   public boolean estUneActivite()
   {
      return false;
   }
   
   /**
    * Ajout success.
    *
    * @param etapeIG the etape ig
    */
   public void ajoutSuccess(EtapeIG etapeIG){
      lesSuccess.ajouter(etapeIG);
   }
   
   /**
    * Supprimer success.
    */
   public void supprimerSuccess() { this.lesSuccess.supprimer(); }
   
   /**
    * Gets les success.
    *
    * @return the les success
    */
   public GestionnaireEtapeIG getLesSuccess()
   {
      return this.lesSuccess;
   }
   
   /**
    * A un success boolean.
    *
    * @return the boolean
    */
   public boolean aUnSuccess()
   {
      return this.lesSuccess.aUnSuccess();
   }
   
   @Override
   public Iterator<PointDeControleIG> iterator()
   {
      return Arrays.stream(this.points).iterator();
   }
   
   /**
    * Sets act restreinte.
    */
   public void setActRestreinte() {}
   
   /**
    * Gets jetons.
    *
    * @return the jetons
    */
   public int getJetons() { return 0; }
   
   /**
    * Sets jetons.
    *
    * @param j the j
    */
   public void setJetons(int j) {}
   
   
   /**
    * Gets ecart temps.
    *
    * @return the ecart temps
    */
   public int getEcart_temps() { return 0; }
   
   /**
    * Sets ecart temps.
    *
    * @param et the et
    */
   public void setEcart_temps(int et) {}
   
   
   /**
    * Gets temps.
    *
    * @return the temps
    */
   public int getTemps() { return 0; }
   
   /**
    * Sets temps.
    *
    * @param t the t
    */
   public void setTemps(int t) {}
   
   /**
    * Add client.
    *
    * @param c the c
    */
   public void addClient(Client c) { this.lesClients.add(c); }
   
   /**
    * Reset.
    */
   public void reset() { this.lesClients.clear(); }
   
   /**
    * Gets sens.
    *
    * @return the sens
    */
   public int getSens() { return 0; }
   
   /**
    * Sets sens.
    *
    * @param s the s
    */
   public void setSens(int s) {}
   
   /**
    * Est accessible depuis boolean.
    *
    * @param etape the etape
    * @return the boolean
    */
   public boolean estAccessibleDepuis(EtapeIG etape)
   {
      boolean estAccessible = false;
      for(EtapeIG e : etape.getLesSuccess())
      {
         if(e.equals(this))
         {
            return true;
         }
         else
         {
            estAccessible = estAccessible || this.estAccessibleDepuis(e);
         }
      }
      return estAccessible;
   }
   
   @Override
   public boolean equals(Object o)
   {
      if(this == o)
      {
         return true;
      }
      if(o == null || getClass() != o.getClass())
      {
         return false;
      }
      EtapeIG that = (EtapeIG) o;
      return Double.compare(that.posX, posX) == 0 && Double.compare(that.posY, posY) == 0 && Objects.equals(nom, that.nom) && Objects.equals(identifiant, that.identifiant);
   }
   
   @Override
   public int hashCode()
   {
      return Objects.hash(nom, identifiant);
   }
   
   /**
    * Gets success.
    *
    * @param i the
    * @return the success
    */
   public EtapeIG getSuccess(int i)
   {
      return this.lesSuccess.getSuccess(i);
   }
}
