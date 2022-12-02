package IG.mondeIG;

import IG.exceptions.MondeException;
import IG.exceptions.TwiskException;
import IG.outilsIG.FabriqueIdentifiant;
import IG.outilsIG.MondeEnregistre;
import IG.outilsIG.TailleComposants;
import IG.outilsIG.ThreadsManager;
import IG.vues.Observateur;
import com.google.gson.Gson;
import javafx.concurrent.Task;
import twisk.ClientTwisk;
import twisk.monde.*;
import twisk.outils.ClassLoaderPerso;
import twisk.outils.FabriqueNumero;
import twisk.simulation.Client;
import twisk.simulation.GestionnaireClients;

import java.awt.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class MondeIG extends SujetObservateurs implements Observateur
{
   protected Point[] pts = new Point[2];
   protected PointDeControleIG[] pdc = new PointDeControleIG[2];
   
  
   protected ArrayList<ArcIG> arcs = new ArrayList<>(), select_arcs = new ArrayList<>();
   protected HashMap<String, EtapeIG> etapes = new HashMap<>();
   protected ArrayList<EtapeIG> select_etape = new ArrayList<>();
   
   protected String identifiant = FabriqueIdentifiant.getInstance().getIdentifiantEtape();
   
   protected Object contructeurSimulation;
   protected Class<?> classSimulation;
   
   protected CorrespondanceEtapes correspondanceEtapes;
   
   protected int fin_arc = 0, size_pdc = 0, size_pts = 0, nbClient = 5;

   protected boolean estLancee = false;
   
   protected String loi = "Uniforme";
   
   /**
    * Instantiates a new Monde ig.
    */
   public MondeIG()
   {
      this.ajouter("Activité");
      MondeEnregistre.getInstance().setMonde(this);
   }
   
   /**
    * Ajouter.
    *
    * @param type the type
    */
   public void ajouter(String type)
   {
      if(Objects.equals(type, "Activité"))
      {
         this.etapes.put(this.identifiant, new ActiviteIG(type + "_" + this.identifiant, this.identifiant, TailleComposants.getInstance().getLargeur(), TailleComposants.getInstance().getHauteur()));
      }
      else
      {
         this.etapes.put(this.identifiant, new GuichetIG(type + "_" + this.identifiant, this.identifiant, TailleComposants.getInstance().getLargeur(), TailleComposants.getInstance().getHauteur()));
      }
      
      this.identifiant = FabriqueIdentifiant.getInstance().getIdentifiantEtape();
      this.notifierObservateurs();
   }

   private void ajouter() throws TwiskException
   {
      if(this.fin_arc != 2)
      {
         this.reset();
         throw new TwiskException("le dernier clic n'était pas un point de contrôle.\nVeuillez recommencer du début.");
      }
      else if(this.size_pts == 1)
      {
         this.reset();
         throw new TwiskException("il n'y a qu'un point sélectionné.\nVeuillez recommencer du début.");
      }
      else if(this.etapes.get(this.pdc[0].getId()).estAccessibleDepuis(this.etapes.get(this.pdc[1].getId())))
      {
         this.reset();
         throw new TwiskException("le monde ne doit pas comporter de circuit.");
      }
      
      boolean valide = Objects.equals(this.pdc[0].getId(), this.pdc[1].getId());
      if(valide)
      {
         this.reset();
         throw new TwiskException("les deux points sont sur la même étape.\nVeuillez recommencer du début.");
      }
      else
      {
         for(ArcIG a : this.arcs)
         {
            valide = (a.getDeb().getId().equals(this.pdc[0].getId())) || (a.getDeb().getId().equals(this.pdc[1].getId()));
            valide = valide && ((a.getFin().getId().equals(this.pdc[0].getId())) || (a.getFin().getId().equals(this.pdc[1].getId())));
            if(valide)
            {
               break;
            }
         }
   
         if(valide)
         {
            this.reset();
            throw new TwiskException("un arc existe déjà entre ces deux étapes.\nVeuillez recommencer du début.");
         }
         else
         {
            if(this.etapes.get(this.pdc[1].getId()).estUnGuichet())
            {
               for(int i = 0;i < 4;++i)
               {
                  if(Objects.equals(this.etapes.get(this.pdc[1].getId()).points[i], this.pdc[1]))
                  {
                     if(i % 2 == 0)
                     {
                        this.etapes.get(this.pdc[1].getId()).setSens(1);
                     }
                     else
                     {
                        this.etapes.get(this.pdc[1].getId()).setSens(-1);
                     }
                  }
               }
            }
            
            if(this.size_pts == 0)
            {
               this.arcs.add(new LigneDroiteIG(this.pdc[0], this.pdc[1]));
            }
            else
            {
               this.arcs.add(new CourbeIG(this.pdc[0], this.pdc[1], this.pts[0], this.pts[1]));
            }
            this.ajouterSuccess(this.pdc[0],this.pdc[1] );
            this.reset();
            this.notifierObservateurs();
         }
      }
   }
   
   /**
    * Iterator collection.
    *
    * @return the collection
    */
   public Collection<EtapeIG> iterator() { return this.etapes.values(); }
   
   /**
    * Iterator arc iterator.
    *
    * @return the iterator
    */
   public Iterator<ArcIG> iteratorArc() { return this.arcs.iterator(); }
   
   /**
    * Iterator client iterator.
    *
    * @return the iterator
    */
   public Iterator<Client> iteratorClient()
   {
      if(this.contructeurSimulation == null)
      {
         return null;
      }
      
      GestionnaireClients temp = null;
      try
      {
         Method getGestCl = this.classSimulation.getMethod("getGestionnaireClients");
         temp = (GestionnaireClients) getGestCl.invoke(this.contructeurSimulation);
      }
      catch(NoSuchMethodException | InvocationTargetException | IllegalAccessException e)
      {
         e.printStackTrace();
      }
   
      assert temp != null;
      return temp.iterator();
   }
   
   private void Fin_arc() { this.fin_arc++; }
   
   /**
    * Gets select etape size.
    *
    * @return the select etape size
    */
   public int getSelectEtapeSize() { return this.select_etape.size(); }
   
   /**
    * Add rm select etape.
    *
    * @param e the e
    */
   public void add_rm_SelectEtape(EtapeIG e)
   {
      if(e.getSelect())
      {
         this.select_etape.add(e);
      }
      else
      {
         this.select_etape.remove(e);
      }
   }
   
   /**
    * Gets select arc size.
    *
    * @return the select arc size
    */
   public int getSelectArcSize() { return this.select_arcs.size(); }
   
   /**
    * Add rm select arc.
    *
    * @param a the a
    */
   public void add_rm_SelectArc(ArcIG a)
   {
      if(a.getSelect())
      {
         this.select_arcs.add(a);
      }
      else
      {
         this.select_arcs.remove(a);
      }
   }
   
   /**
    * Add pt.
    *
    * @param x the x
    * @param y the y
    * @throws TwiskException the twisk exception
    */
   public void addPt(double x, double y) throws TwiskException
   {
      boolean pdc = false, etape = false, arc = false;
      
      first: for(EtapeIG e : this.etapes.values())
      {
         if(e.getPressed())
         {
            etape = true;
            e.setPressed();
            break;
         }

         for(PointDeControleIG p : e)
         {
            if(p.getPressed())
            {
               pdc = true;
               this.pdc[this.size_pdc++] = p;
               this.Fin_arc();
               p.setPressed();
               break first;
            }
         }
      }

      for(ArcIG a : this.arcs)
      {
         if(a.getPressed())
         {
            arc = true;
            a.setPressed();
            break;
         }
      }

      if(!etape && !arc)
      {
         if (!pdc)
         {
            if(this.size_pts == 2)
            {
               throw new TwiskException("trois points autre que des\nPointsDeControle ont été sélectionné.");
            }
            this.pts[this.size_pts++] = new Point((int) x, (int) y);

         }
         else if (this.fin_arc == 2)
         {
            this.ajouter();
         }
      }
   }

   private void reset()
   {
      this.pdc = new PointDeControleIG[2];
      this.size_pdc = 0;

      this.pts = new Point[2];
      this.size_pts = 0;

      this.fin_arc = 0;
   }
   
   /**
    * Supprimer select.
    */
   public void supprimer_select()
   {
      this.reset();
      for(EtapeIG e : this.select_etape)
      {
         this.etapes.remove(e.getId());
         this.arcs.removeIf(a -> Objects.equals(a.getDeb().getId(), e.getId()) || Objects.equals(a.getFin().getId(), e.getId()));
      }
      for(ArcIG a : this.select_arcs)
      {
         this.arcs.remove(a);
         etapes.get(a.getDeb().getId()).supprimerSuccess();
      }
      this.deselectionner();
   }
   
   /**
    * Renommer select.
    *
    * @param n the n
    */
   public void renommer_select(String n)
   {
      this.select_etape.get(0).setNom(n);
      this.select_etape.get(0).setSelect();
      this.select_etape.remove(0);
   }
   
   /**
    * Deselectionner.
    */
   public void deselectionner()
   {
       for(EtapeIG e : this.select_etape)
       {
           e.setSelect();
       }
       this.select_etape.clear();

       for(ArcIG a : this.select_arcs)
       {
           a.setSelect();
       }
       this.select_arcs.clear();

       this.notifierObservateurs();
   }
   
   /**
    * Entree.
    */
   public void entree()
   {
      for(EtapeIG e : this.select_etape)
      {
         e.setEntree();
      }
      this.deselectionner();
   }
   
   /**
    * Sortie.
    */
   public void sortie()
   {
      for(EtapeIG e : this.select_etape)
      {
         e.setSortie();
      }
      this.deselectionner();
   }
   
   /**
    * Changer delaitemps.
    *
    * @param t  the t
    * @param et the et
    */
   public void changer_delaitemps(int t, int et)
   {
      this.select_etape.get(0).setTemps(t);
      this.select_etape.get(0).setEcart_temps(et);
      this.select_etape.get(0).setSelect();
      this.select_etape.remove(0);
   }
   
   /**
    * Changer jetons.
    *
    * @param j the j
    */
   public void changer_jetons(int j)
   {
      this.select_etape.get(0).setJetons(j);
      this.select_etape.get(0).setSelect();
      this.select_etape.remove(0);
   }
   
   /**
    * Select est un guichet boolean.
    *
    * @return the boolean
    */
   public boolean selectEstUnGuichet() { return this.select_etape.get(0).estUnGuichet(); }
   
   /**
    * Ajouter success.
    *
    * @param pdc1 the pdc 1
    * @param pdc2 the pdc 2
    */
   public void ajouterSuccess(PointDeControleIG pdc1, PointDeControleIG pdc2) { this.etapes.get(pdc1.getId()).ajoutSuccess(this.etapes.get(pdc2.getId())); }
   
   /**
    * Simuler.
    *
    * @throws MondeException the monde exception
    */
   public void simuler() throws MondeException
   {
      this.verifierMondeIG();
      
      this.estLancee = true;
      this.notifierObservateurs();
   
      Task<Void> commencerSimulation = new Task<>()
      {
         @Override
         protected Void call()
         {
            try
            {
               ClassLoaderPerso classLoaderPerso = new ClassLoaderPerso(ClientTwisk.class.getClassLoader());
               classSimulation = classLoaderPerso.loadClass("twisk.simulation.Simulation");
               Method[] m = classSimulation.getMethods();
               contructeurSimulation = classSimulation.getConstructor().newInstance();
   
               int simuler = 0;
               for(int i = 0; i < m.length; i++)
               {
                  switch(m[i].getName())
                  {
                     case "setNbClients":
                        m[i].invoke(contructeurSimulation, nbClient);
                        break;
                     case "simuler":
                        simuler = i;
                        break;
                  }
               }

               ajouterDansSimulation();
               m[simuler].invoke(contructeurSimulation, creerMonde());
            }
            catch(NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException | ClassNotFoundException e)
            {
               throw new RuntimeException(e);
            }
   
            estLancee = false;
            return null;
         }
      };
   
      commencerSimulation.setOnSucceeded(event -> {
         this.killProc();
         this.notifierObservateurs();
      });
      
      ThreadsManager.getInstance().lancer(commencerSimulation);
   }
   
   private void verifierMondeIG() throws MondeException
   {
      int nb_enter = 0, nb_exit = 0;
      String enter = "";
      
      for(EtapeIG e : this.etapes.values())
      {
         if(e.getEntree())
         {
            nb_enter++;
            enter = e.getId();
         }
         if(e.getSortie())
         {
            nb_exit++;
         }
         
         if(e.estUnGuichet())
         {
            if(e.getSuccess(0).estUneActivite() && !e.getSuccess(0).estUneActiciteRestreinte())
            {
               e.getSuccess(0).setActRestreinte();
            }
         }
      }
      
      if(nb_enter == 0 && nb_exit == 0)
      {
         throw new MondeException("il n'y a ni d'entrée ni de sortie.");
      }
      else if(nb_enter == 0)
      {
         throw new MondeException("il n'y a pas d'entrée.");
      }
      else if(nb_exit == 0)
      {
         throw new MondeException("il n'y a pas de sortie.");
      }
      else if(nb_enter > 1)
      {
         throw new MondeException("il y a plus d'une entrée.");
      }
      else if(!cheminEntreeVersSortie(this.etapes.get(enter)))
      {
         throw new MondeException("il n'y a pas de chemin vers la sortie.");
      }
      else if(culDeSac(this.etapes.get(enter)))
      {
         throw new MondeException("il y a un de cul de sac dans le monde.");
      }
   }
   
   private boolean cheminEntreeVersSortie(EtapeIG entree)
   {
      boolean chemin = false;
      for(EtapeIG e : entree.getLesSuccess())
      {
         if(e.getSortie())
         {
            return true;
         }
         else
         {
            chemin = chemin || this.cheminEntreeVersSortie(e);
         }
      }
      return chemin;
   }
   
   private boolean culDeSac(EtapeIG entree)
   {
      boolean chemin = false;
      for(EtapeIG e : entree.getLesSuccess())
      {
         if(!e.getSortie() && !e.aUnSuccess())
         {
            return true;
         }
         else
         {
            chemin = chemin || this.culDeSac(e);
         }
      }
      return chemin;
   }
   
   /**
    * Creer monde monde.
    *
    * @return the monde
    */
   public Monde creerMonde()
   {
      this.correspondanceEtapes = new CorrespondanceEtapes();
      Monde monde = new Monde();
      monde.setLoi(this.loi);
      
      for(EtapeIG e : this.etapes.values())
      {
         Etape temp;
         
         if(e.estUneActivite())
         {
            temp = new Activite(e.getNom(), e.getTemps(), e.getEcart_temps());
         }
         else if(e.estUnGuichet())
         {
            temp = new Guichet(e.getNom(), e.getJetons());
         }
         else
         {
            temp = new ActiviteRestreinte(e.getNom(), e.getTemps(), e.getEcart_temps());
         }
         
         if(e.getEntree())
         {
            monde.aCommeEntree(temp);
         }
         
         if(e.getSortie())
         {
            monde.aCommeSortie(temp);
         }
         
         monde.ajouter(temp);
         this.correspondanceEtapes.ajouter(e, temp);
      }
      
      for(EtapeIG e : this.etapes.values())
      {
         if(e.aUnSuccess())
         {
            for(EtapeIG s : e.getLesSuccess())
            {
               this.correspondanceEtapes.getEtape(e).ajouterSuccesseur(this.correspondanceEtapes.getEtape(s));
            }
         }
      }
      
      return monde;
   }
   
   /**
    * Gets correspondance etapes.
    *
    * @return the correspondance etapes
    */
   public CorrespondanceEtapes getCorrespondanceEtapes() { return this.correspondanceEtapes; }
   
   /**
    * Gets est lancee.
    *
    * @return the est lancee
    */
   public boolean getEstLancee() { return this.estLancee; }
   
   private void ajouterDansSimulation()
   {
      try
      {
         Method m = this.classSimulation.getMethod("ajouterObservateur", Observateur.class);
         m.invoke(this.contructeurSimulation, this);
      }
      catch(InvocationTargetException | IllegalAccessException | NoSuchMethodException e)
      {
         throw new RuntimeException(e);
      }
   }
   
   /**
    * Kill proc.
    */
   public void killProc()
   {
      if(this.contructeurSimulation != null)
      {
         ThreadsManager.getInstance().detruireTout();
         try
         {
            Method m = classSimulation.getMethod("killProc");
            m.invoke(this.contructeurSimulation);
         }
         catch(NoSuchMethodException | InvocationTargetException | IllegalAccessException e)
         {
            throw new RuntimeException(e);
         }
      }
   }
   
   /**
    * Sets contructeur simulation.
    */
   public void setContructeurSimulation() { this.contructeurSimulation = null; }
   
   /**
    * Nouveau.
    */
   public void nouveau()
   {
      this.etapes.clear();
      this.arcs.clear();
      
      this.supprimer_select();
      this.reset();
      
      FabriqueIdentifiant.getInstance().setIdentifiantEtape(-1);
      FabriqueNumero.getInstance().reset();
      
      this.identifiant = FabriqueIdentifiant.getInstance().getIdentifiantEtape();
   }
   
   /**
    * Sets nb client.
    *
    * @param n the n
    */
   public void setNbClient(int n) { this.nbClient = n; }
   
   @Override
   public void reagir() { this.notifierObservateurs(); }
   
   /**
    * Ouvrir.
    *
    * @param f the f
    */
   public void ouvrir(File f)
   {
      this.nouveau();
      Gson gson = new Gson();
      try
      {
         FileReader fileReader = new FileReader(f);
         BufferedReader lire = new BufferedReader(fileReader);
         
         this.identifiant = gson.fromJson(lire.readLine(), String.class);
         FabriqueIdentifiant.getInstance().setIdentifiantEtape(Integer.parseInt(this.identifiant));
         
         int size = gson.fromJson(lire.readLine(), int.class);
         for(int i = 0;i < size;i++)
         {
            char typeEtape = gson.fromJson(lire.readLine(), char.class);
            String nomEtape = gson.fromJson(lire.readLine(), String.class);
            String idEtape = gson.fromJson(lire.readLine(), String.class);
            
            EtapeIG temp;
            if(typeEtape == 'g')
            {
               int j = gson.fromJson(lire.readLine(), int.class);
               int s = gson.fromJson(lire.readLine(), int.class);
               temp = new GuichetIG(nomEtape, idEtape, TailleComposants.getInstance().getLargeur(), TailleComposants.getInstance().getHauteur());
               temp.setJetons(j);
               temp.setSens(s);
            }
            else
            {
               int t = gson.fromJson(lire.readLine(), int.class);
               int e = gson.fromJson(lire.readLine(), int.class);
   
               temp = new ActiviteIG(nomEtape, idEtape, TailleComposants.getInstance().getLargeur(), TailleComposants.getInstance().getHauteur());
               temp.setTemps(t);
               temp.setEcart_temps(e);
            }
            temp.setPoints(gson.fromJson(lire.readLine(), double.class), gson.fromJson(lire.readLine(), double.class));
            
            if(gson.fromJson(lire.readLine(), boolean.class))
            {
               temp.setEntree();
            }
            if(gson.fromJson(lire.readLine(), boolean.class))
            {
               temp.setSortie();
            }
            
            this.etapes.put(idEtape, temp);
         }
         
         size = gson.fromJson(lire.readLine(), int.class);
         for(int i = 0;i < size;i++)
         {
            char typeArc = gson.fromJson(lire.readLine(), char.class);
            String pdcID = gson.fromJson(lire.readLine(), String.class);
            double pdcX = gson.fromJson(lire.readLine(), double.class);
            double pdcY = gson.fromJson(lire.readLine(), double.class);
            
            PointDeControleIG temp1 = null;
            for(PointDeControleIG p : this.etapes.get(pdcID))
            {
               if(p.getPosX() == pdcX && p.getPosY() == pdcY)
               {
                  temp1 = p;
               }
            }
   
            pdcID = gson.fromJson(lire.readLine(), String.class);
            pdcX = gson.fromJson(lire.readLine(), double.class);
            pdcY = gson.fromJson(lire.readLine(), double.class);
            
            PointDeControleIG temp2 = null;
            for(PointDeControleIG p : this.etapes.get(pdcID))
            {
               if(p.getPosX() == pdcX && p.getPosY() == pdcY)
               {
                  temp2 = p;
               }
            }
      
            ArcIG temp;
            if(typeArc == 'c')
            {
               Point p1 = new Point(gson.fromJson(lire.readLine(), Point.class));
               Point p2 = new Point(gson.fromJson(lire.readLine(), Point.class));
               temp = new CourbeIG(temp1, temp2, p1, p2);
            }
            else
            {
               temp = new LigneDroiteIG(temp1, temp2);
            }
   
            assert temp1 != null;
            assert temp2 != null;
            this.ajouterSuccess(temp1, temp2);
            this.arcs.add(temp);
         }
         
         lire.close();
      }
      catch(IOException e)
      {
         throw new RuntimeException(e);
      }
      this.notifierObservateurs();
   }
   
   /**
    * Enregistrer.
    *
    * @param f the f
    */
   public void enregistrer(File f)
   {
      Gson gson = new Gson();
      try
      {
         FileWriter fileWriter = new FileWriter(f);
         BufferedWriter ecrire = new BufferedWriter(fileWriter);
         
         ecrire.write(gson.toJson(this.identifiant));
         ecrire.newLine();
         
         ecrire.write(gson.toJson(this.etapes.size()));
         ecrire.newLine();
         for(EtapeIG e : this.etapes.values())
         {
            if(e.estUnGuichet())
            {
               ecrire.write(gson.toJson('g'));
               ecrire.newLine();
               ecrire.write(gson.toJson(e.getNom()));
               ecrire.newLine();
               ecrire.write(gson.toJson(e.getId()));
               ecrire.newLine();
               ecrire.write(gson.toJson(e.getJetons()));
               ecrire.newLine();
               ecrire.write(gson.toJson(e.getSens()));
               ecrire.newLine();
            }
            else
            {
               ecrire.write(gson.toJson('a'));
               ecrire.newLine();
               ecrire.write(gson.toJson(e.getNom()));
               ecrire.newLine();
               ecrire.write(gson.toJson(e.getId()));
               ecrire.newLine();
               ecrire.write(gson.toJson(e.getTemps()));
               ecrire.newLine();
               ecrire.write(gson.toJson(e.getEcart_temps()));
               ecrire.newLine();
            }
   
            ecrire.write(gson.toJson(e.getPosX()));
            ecrire.newLine();
            ecrire.write(gson.toJson(e.getPosY()));
            ecrire.newLine();
   
            ecrire.write(gson.toJson(e.getEntree()));
            ecrire.newLine();
            ecrire.write(gson.toJson(e.getSortie()));
            ecrire.newLine();
         }
         
         ecrire.write(gson.toJson(this.arcs.size()));
         ecrire.newLine();
         for(ArcIG a : this.arcs)
         {
            if(a.estUneCourbe())
            {
               ecrire.write(gson.toJson('c'));
               ecrire.newLine();
               ecrire.write(gson.toJson(a.getDeb().getId()));
               ecrire.newLine();
               ecrire.write(gson.toJson(a.getDeb().getPosX()));
               ecrire.newLine();
               ecrire.write(gson.toJson(a.getDeb().getPosY()));
               ecrire.newLine();
               ecrire.write(gson.toJson(a.getFin().getId()));
               ecrire.newLine();
               ecrire.write(gson.toJson(a.getFin().getPosX()));
               ecrire.newLine();
               ecrire.write(gson.toJson(a.getFin().getPosY()));
               ecrire.newLine();
               ecrire.write(gson.toJson(a.getMilieu_1()));
               ecrire.newLine();
               ecrire.write(gson.toJson(a.getMilieu_2()));
               ecrire.newLine();
            }
            else
            {
               ecrire.write(gson.toJson('l'));
               ecrire.newLine();
               ecrire.write(a.getDeb().getId());
               ecrire.newLine();
               ecrire.write(gson.toJson(a.getDeb().getPosX()));
               ecrire.newLine();
               ecrire.write(gson.toJson(a.getDeb().getPosY()));
               ecrire.newLine();
               ecrire.write(a.getFin().getId());
               ecrire.newLine();
               ecrire.write(gson.toJson(a.getFin().getPosX()));
               ecrire.newLine();
               ecrire.write(gson.toJson(a.getFin().getPosY()));
               ecrire.newLine();
            }
         }
         
         ecrire.close();
      }
      catch(IOException e)
      {
         throw new RuntimeException(e);
      }
   }
   
   /**
    * Est un integer.
    *
    * @param text_result the text result
    * @throws TwiskException the twisk exception
    */
   public void estUnInteger(String text_result) throws TwiskException
   {
      if (text_result == null)
      {
         throw new TwiskException("rien n'a été écrit.");
      }
      try
      {
         int i = Integer.parseInt(text_result);
         
         if(i == 0)
         {
            throw new TwiskException("0 n'est pas une valeur acceptable.");
         }
         else if(i < 0)
         {
            throw new TwiskException("\nles valeurs négatives ne sont pas acceptées.");
         }
         else if(i > 10)
         {
            throw new TwiskException("\nles valeurs strictement supérieurs à 10\nne sont pas acceptées.");
         }
      }
      catch(NumberFormatException e)
      {
         throw new TwiskException("cette entrée n'est pas un entier.");
      }
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
   
   /**
    * Sets est lancee.
    *
    * @param b the b
    */
   public void setEstLancee(boolean b)
   {
      this.estLancee = b;
   }
}