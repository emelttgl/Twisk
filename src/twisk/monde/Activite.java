package twisk.monde;

public class Activite extends Etape
{
   protected int temps;
   protected int ecartTemps;
   
   /**
    * Construit une activité à partir d'un nom
    *
    * @param nom Nom de l'acitivité
    */
   public Activite(String nom)
   {
      super(nom);
      this.temps = 5;
      this.ecartTemps = 2;
   }
   
   /**
    * Construit une activité à partir d'un nom, d'un temp et d'un écart temps
    *
    * @param nom Nom de l'activité
    * @param t   Temps pour les delai en C
    * @param e   Ecart temps pour les delai en C
    */
   public Activite(String nom, int t, int e)
   {
      super(nom);
      this.temps = t;
      this.ecartTemps = e;
   }

   @Override
   public String getTemps()
   {
      return this.temps + "";
   }

   @Override
   public String getEcartTemps()
   {
      return this.ecartTemps + "";
   }

   @Override
   public String toString()
   {
      return this.nom + " = numéro : " + this.num + ", temps : " + this.temps + ", ecartTemps : " + this.ecartTemps + ", successeur - " + this.lesSucces.noms_etapes();
   }

   @Override
   public boolean estUnGuichet()
   {
      return false;
   }

   @Override
   public boolean estUneSortie()
   {
      return false;
   }

   @Override
   public String toC()
   {
      StringBuilder str_b = new StringBuilder();
      
      if(this.lesSucces.nbEtapes() == 1)
      {
         if(this.lesSucces.estUneSortie(0) && !this.lesSucces.etapes.get(0).estUnGuichet())
         {
            str_b.append("delai(").append(this.lesSucces.getTemps(0)).append(", ").append(this.lesSucces.getEcartTemps(0)).append(");\n");
         }
   
         return "transfert(" + this.nom + ", " + this.lesSucces.getNom(0) + ");\n" + str_b + "\n" + this.lesSucces.successeur_toC(0);
      }
      else
      {
         str_b.append("int nb = rand() % ").append(this.lesSucces.nbEtapes()).append(";\nswitch(nb){\n");
         
         for(int i = 0;i < this.lesSucces.nbEtapes();i++)
         {
            str_b.append("case ").append(i).append(" : {\n");
            StringBuilder temp = new StringBuilder();
            if(this.lesSucces.estUneSortie(i) && !this.lesSucces.etapes.get(i).estUnGuichet())
            {
               temp.append("delai(").append(this.lesSucces.getTemps(i)).append(", ").append(this.lesSucces.getEcartTemps(i)).append(");\n");
            }
            
            str_b.append("transfert(").append(this.nom).append(", ").append(this.lesSucces.getNom(i)).append(");\n").append(temp).append(this.lesSucces.successeur_toC(i)).append("break;\n}\n");
         }
         
         return str_b + "}\n";
      }
   }
   
   @Override
   public int getSemaphore()
   {
      return 0;
   }

   @Override
   public int getJetons()
   {
      return 0;
   }
}
