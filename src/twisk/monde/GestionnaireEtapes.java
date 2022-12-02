package twisk.monde;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class GestionnaireEtapes implements Iterable<Etape>
{
   protected ArrayList<Etape> etapes = new ArrayList<>();
   
   /**
    * Ajoute une ou plusieurs étapes à la liste en attribut
    *
    * @param etapes Les étapes à ajouter
    */
   public void ajouter(Etape... etapes)
   {
      this.etapes.addAll(Arrays.asList(etapes));
   }
   
   /**
    * Retourne le nombre d'étapes
    *
    * @return Entier correspondant au nombre d'étapes
    */
   public int nbEtapes()
   {
      return this.etapes.size();
   }
   
   @Override
   public Iterator<Etape> iterator()
   {
      return this.etapes.iterator();
   }
   
   @Override
   public String toString()
   {
      StringBuilder str_b = new StringBuilder();
      for(Etape e : this.etapes)
      {
         str_b.append(e.toString()).append('\n');
      }
      
      return str_b.toString();
   }
   
   /**
    * Retourne les noms des étapes
    *
    * @return String contenant les noms des étapes
    */
   public String noms_etapes()
   {
      StringBuilder str_b = new StringBuilder();
      for(Etape e : this.etapes)
      {
         str_b.append(e.getNom()).append(", ");
      }
   
      return str_b.toString();
   }
   
   /**
    * Retourne le temps de la première étapes
    *
    * @param i the
    * @return String contenant le temps de la première étape
    */
   public String getTemps(int i)
   {
      if(this.etapes.size() > 0)
      {
         return this.etapes.get(i).getTemps();
      }
      return "";
   }
   
   /**
    * Retourne l'écart temps de la première étapes
    *
    * @param i the
    * @return String contenant l'écart temps de la première étape
    */
   public String getEcartTemps(int i)
   {
      if(this.etapes.size() > 0)
      {
      return this.etapes.get(i).getEcartTemps();
      }
      return "";
   }
   
   /**
    * Retourne le toC de la première étapes
    *
    * @param i the
    * @return String contenant le toC de la première étape
    */
   public String successeur_toC(int i)
   {
      if(this.etapes.size() > 0)
      {
         return this.etapes.get(i).toC();
      }
      return "";
   }
   
   /**
    * Retourne un booléen en fonction de la class du premier successeur
    *
    * @param i the
    * @return True si le premier successeur est une sortie, False dans le cas contraire
    */
   public boolean estUneSortie(int i)
   {
      if(this.etapes.size() > 0)
      {
         return !this.etapes.get(i).estUneSortie();
      }
      return true;
   }
   
   /**
    * Retourne le nom de la première étapes
    *
    * @param i the
    * @return String contenant le nom de la première étape
    */
   public String getNom(int i)
   {
      if(this.etapes.size() > 0)
      {
         return this.etapes.get(i).getNom();
      }
      return "";
   }
}
