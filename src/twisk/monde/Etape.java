package twisk.monde;

import twisk.outils.FabriqueNumero;

import java.util.Iterator;
import java.util.Objects;

public abstract class Etape implements Iterable<Etape>
{
   protected int num;
   protected String nom;
   protected GestionnaireEtapes lesSucces;
   
   /**
    * Construit une étape à partir d'un nom
    *
    * @param nom Nom de l'étape
    */
   public Etape(String nom)
   {
      this.num = FabriqueNumero.getInstance().getNumeroEtape();
      this.nom = nom;
      this.lesSucces = new GestionnaireEtapes();
   }
   
   /**
    * Ajoute un ou plusieurs successeur à l'attribut qui gère les successeurs
    *
    * @param e Une ou plusieurs étapes à ajouter en successeur.
    */
   public void ajouterSuccesseur(Etape... e)
   {
      this.lesSucces.ajouter(e);
   }
   
   /**
    * Retourne le nombre de successeur de l'étape
    *
    * @return Entier correspondant au nombre de successeur
    */
   public int nbSuccesseurs()
   {
      return this.lesSucces.nbEtapes();
   }
   
   /**
    * Retourne le nom de l'étape
    *
    * @return String contenant le nom de l'étape
    */
   public String getNom()
   {
      return this.nom;
   }
   
   /**
    * Retourne le numéro de l'étape
    *
    * @return Entier correspondant au numéro de l'étape
    */
   public int getNum()
   {
      return this.num;
   }

   @Override
   public Iterator<Etape> iterator()
   {
      return this.lesSucces.iterator();
   }

   /**
    * Retourne les informations de l'étape
    * @return String contenant toute les informations de l'étape
    */
   public abstract String toString();
   
   /**
    * Retourne une valeur booléen
    *
    * @return True si l'étape est un guichet, False si l'étape n'en est pas une
    */
   public abstract boolean estUnGuichet();
   
   /**
    * Retourne une valeur booléen
    *
    * @return True si l'étape est une sortie, False si l'étape n'en est pas une
    */
   public abstract boolean estUneSortie();
   
   /**
    * Retourne le code C de l'étape
    *
    * @return String contenant le code C pour le fichier client.c
    */
   public abstract String toC();
   
   /**
    * Retourne le temps de l'étape (retourne 0 si est appelé sur un guichet)
    *
    * @return String contenant le temps de l'étape
    */
   public abstract String getTemps();
   
   /**
    * Retourne l'écart temps de l'étape (retourne 0 si est appelé sur un guichet)
    *
    * @return String contenant l'écart temps de l'étape
    */
   public abstract String getEcartTemps();
   
   /**
    * Retourne l'écart temps de l'étape (retourne 0 si est appelé sur un guichet)
    *
    * @return Entier correspondant au n
    */
   public abstract int getSemaphore();
   
   /**
    * Retourne le nombre de jetons de l'étape (retourne 0 si est appelé sur une activité)
    *
    * @return Entier correspondant au nombre de jetons
    */
   public abstract int getJetons();
   
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
      Etape etape = (Etape) o;
      return num == etape.num && Objects.equals(nom, etape.nom);
   }
   
   @Override
   public int hashCode()
   {
      return Objects.hash(num, nom);
   }
}
