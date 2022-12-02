package twisk.simulation;

import IG.mondeIG.SujetObservateurs;
import twisk.monde.Etape;
import twisk.monde.Monde;
import twisk.outils.FabriqueNumero;
import twisk.outils.KitC;

import java.util.Objects;

public class Simulation extends SujetObservateurs
{
   protected int nbClients;
   protected KitC kitc = new KitC();
   protected GestionnaireClients gestionnaireClients;
   
   /**
    * Construit la class Simulation en créant le dossier twisk dans /tmp
    */
   public Simulation()
   {
      this.kitc.creerEnvironnement();
   }
   
   /**
    * Donne une valeur à l'attribut correspondant au nombre de client
    *
    * @param n Nombre de clients
    */
   public void setNbClients(int n)
   {
      this.gestionnaireClients = new GestionnaireClients(n);
      this.nbClients = n;
   }
   
   /**
    * Simule le monde en créant tout d'abord tout ce qu'il a besoin pour fonctionner
    *
    * @param monde Monde qui va être simulé
    */
   public void simuler(Monde monde)
   {
      this.kitc.creerFichier(monde.toC());
      this.kitc.compiler();
      this.kitc.construireLaLibrairie();
      System.load("/tmp/twisk/libTwisk" + FabriqueNumero.getInstance().getNumeroLib() + ".so");
   
      
      int[] jetons = new int[monde.nbGuichets()];
      for(Etape e : monde)
      {
         if(e.estUnGuichet())
         {
            jetons[e.getSemaphore() - 1] = e.getJetons();
         }
      }
   
      int[] resultat = start_simulation(monde.nbEtapes(), monde.nbGuichets(), this.nbClients, jetons);
      this.gestionnaireClients.setClients(resultat);
   
      int indiceSortie = 0;
      Etape[] lesEtapes = new Etape[monde.nbEtapes()];
      for(Etape e : monde)
      {
         lesEtapes[e.getNum()] = e;
         if(Objects.equals(e.getNom(), "Sortie"))
         {
            indiceSortie = e.getNum();
         }
      }
   
      int cpt = 0;
      while(cpt != this.nbClients)
      {
         int[] ouClient = ou_sont_les_clients(monde.nbEtapes(), this.nbClients);
         cpt = ouClient[(indiceSortie) * (this.nbClients + 1)];
   
         int i = 0;
         for(Etape ignored : monde)
         {
            int etape_nb_clients = i * (this.nbClients + 1);
   
            int rang = 0;
            for(int j = etape_nb_clients + 1; j < (etape_nb_clients + 1 + this.nbClients) - (this.nbClients - ouClient[etape_nb_clients]); j++)
            {
               this.gestionnaireClients.allerA(ouClient[j], lesEtapes[i], ++rang);
            }
            
            i++;
         }
   
         try
         {
            this.notifierObservateurs();
            Thread.sleep(1500);
         }
         catch(InterruptedException ignored)
         {
         }
      }
      nettoyage();
      FabriqueNumero.getInstance().newLib();
   }
   
   /**
    * Lance la simulation du monde
    *
    * @param nbEtapes  Nombre d'étape du monde
    * @param nbGuichet Nombre de guichet du monde
    * @param nbClients Nombre de client du monde
    * @param jetons    Nombre de jetons de chaque guichets
    * @return retourne un tableau d'entier correspondant au monde
    */
   public native int[] start_simulation(int nbEtapes, int nbGuichet, int nbClients, int[] jetons);
   
   /**
    * Permet de savoir où se trouve les clients
    *
    * @param nbEtapes  Nombre d'étape du monde
    * @param nbClients Nombre de client du monde
    * @return retourne un tableau d'entier permettant de savoir où se trouve les clients
    */
   public native int[] ou_sont_les_clients(int nbEtapes, int nbClients);
   
   /**
    * Fonction qui nettoie les malloc fait en C
    */
   public native void nettoyage();
   
   /**
    * Gets gestionnaire clients.
    *
    * @return the gestionnaire clients
    */
   public GestionnaireClients getGestionnaireClients() { return this.gestionnaireClients; }
   
   /**
    * Kill proc.
    */
   public void killProc()
   {
      this.kitc.kill(this.gestionnaireClients);
      this.gestionnaireClients.nettoyer();
   }
}
