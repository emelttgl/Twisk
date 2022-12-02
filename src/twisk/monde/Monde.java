package twisk.monde;

import java.util.Iterator;
import java.util.Objects;

public class Monde implements Iterable<Etape>
{
   protected SasSortie sortie = null;
   protected SasEntree entree = new SasEntree();
   protected GestionnaireEtapes lesEtapes = new GestionnaireEtapes();
   protected String loi;
   
   /**
    * Construit le Monde en ajoutant au gestionnaire d'étapes le sas d'entrée
    */
   public Monde()
   {
      this.lesEtapes.ajouter(this.entree);
   }
   
   /**
    * Ajoute un ou plusieurs successeurs au sas d'entrée
    *
    * @param etapes Les étapes à ajouter
    */
   public void aCommeEntree(Etape... etapes)
   {
      this.entree.ajouterSuccesseur(etapes);
   }
   
   /**
    * Crée le sas sortie et l'ajoute en tant que successeur aux étapes en paramètres
    *
    * @param etapes Les étapes qui auront le sas sortie en successeur
    */
   public void aCommeSortie(Etape... etapes)
   {
      if(this.sortie == null)
      {
         this.sortie =  new SasSortie();
         this.lesEtapes.ajouter(this.sortie);
      }
      
      for(Etape e : etapes)
      {
         e.ajouterSuccesseur(this.sortie);
      }
   }
   
   /**
    * Ajoute au gestionnaire d'étapes la ou les étapes en paramètres
    *
    * @param etapes Les étapes à ajouter au gestionnaire
    */
   public void ajouter(Etape... etapes)
   {
      this.lesEtapes.ajouter(etapes);
   }
   
   /**
    * Retourne le nombre d'étape
    *
    * @return Entier correspondant au nombre d'étape
    */
   public int nbEtapes()
   {
      return this.lesEtapes.nbEtapes();
   }
   
   /**
    * Retourne le nombre de guichet
    *
    * @return Entier correspondant au nombre de guichet
    */
   public int nbGuichets()
   {
      int nbG = 0;
      for(Etape e : this.lesEtapes)
      {
         if(e.estUnGuichet())
         {
            nbG++;
         }
      }
      
      return nbG;
   }
   
   /**
    * Retourne le sas d'entrée
    *
    * @return Le sas d'entrée
    */
   public SasEntree getEntree()
   {
      return this.entree;
   }
   
   @Override
   public Iterator<Etape> iterator()
   {
      return this.lesEtapes.iterator();
   }
   
   @Override
   public String toString()
   {
      return "Monde =\n" + this.lesEtapes.toString();
   }
   
   /**
    * Retourne le code correspondant au define du fichier client.c
    *
    * @return String contenant les define du fichier client.c
    */
   public String define()
   {
      StringBuilder code_c = new StringBuilder("#include \"def.h\"\n#include \"loi.h\"\n#include <time.h>\n");
      for(Etape e : this.lesEtapes)
      {
         code_c.append("#define ").append(e.getNom()).append(" ").append(e.getNum()).append("\n");
         if(e.estUnGuichet())
         {
            code_c.append("#define sem_").append(e.getNom()).append(" ").append(e.getSemaphore()).append("\n");
         }
      }
      
      return code_c.toString();
   }
   
   /**
    * Retourne le code correspondant au début du fichier client.c
    *
    * @return String contenant le début du fichier client.c
    */
   public String toC()
   {
      StringBuilder str_b = new StringBuilder();
      if(Objects.equals(this.loi, "Uniforme"))
      {
         str_b.append("delai(").append(this.entree.getTemps()).append(", ").append(this.entree.getEcartTemps()).append(");\n");
      }
      else if(Objects.equals(this.loi, "Gauss"))
      {
         str_b.append("delaiGauss(10, 4);\n");
      }
      else if(Objects.equals(this.loi, "Poisson"))
      {
         str_b.append("delaiExponentiel(0.1);\n");
      }
      
      return this.define() + "void simulation(int ids){\n" + "entrer(" + this.entree.getNom() + ");\n" + str_b + "\n" + this.entree.toC() + "}\n";
   }
   
   /**
    * Sets loi.
    *
    * @param l the l
    */
   public void setLoi(String l)
   {
      this.loi = l;
   }
}