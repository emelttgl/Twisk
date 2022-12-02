package twisk;

import twisk.monde.Activite;
import twisk.monde.ActiviteRestreinte;
import twisk.monde.Guichet;
import twisk.monde.Monde;
import twisk.outils.ClassLoaderPerso;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClientTwisk
{
   public static void lancementSimulation(Monde monde) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException
   {
      ClassLoaderPerso classLoaderPerso = new ClassLoaderPerso(ClientTwisk.class.getClassLoader());
      Class<?> c = classLoaderPerso.loadClass("twisk.simulation.Simulation");
      Method[] m = c.getMethods();
      Object o = c.getConstructor().newInstance();
      int simuler = 0;
      for(int i = 0;i < m.length;i++)
      {
         if(m[i].getName().equals("setNbClients"))
         {
            m[i].invoke(o, 5);
         }
         else if(m[i].getName().equals("simuler"))
         {
            simuler = i;
         }
      }
      
      m[simuler].invoke(o, monde);
   }
   
   public static Monde premier_Monde() throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException
   {
      Monde monde = new Monde();
      Activite[] act = new Activite[3];

      act[0] = new Activite("Act_1");
      Guichet gui = new Guichet("Gui");
      act[1] = new ActiviteRestreinte("Act_Res");
      act[2] = new Activite("Act_2");
      
      monde.aCommeEntree(act[0]);
      
      act[0].ajouterSuccesseur(gui);
      gui.ajouterSuccesseur(act[1]);
      act[1].ajouterSuccesseur(act[2]);
      
      monde.ajouter(gui, act[0], act[1], act[2]);
      
      monde.aCommeSortie(act[2]);
      
      return monde;
   }
   
   public static Monde deuxieme_Monde() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException
   {
      Monde monde = new Monde();
      Activite act = new ActiviteRestreinte("Act_Res");
      Guichet gui = new Guichet("Gui");
   
      monde.ajouter(gui);
      monde.ajouter(act);
      
      monde.aCommeEntree(gui);
      gui.ajouterSuccesseur(act);
      monde.aCommeSortie(act);
      
      return monde;
   }
   
   public static void main(String[] args) throws ClassNotFoundException
   {
      try
      {
         lancementSimulation(premier_Monde());
         lancementSimulation(deuxieme_Monde());
      }
      catch(NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException ignored)
      {
      }
   }
}