package IG.outilsIG;

import javafx.concurrent.Task;

import java.util.ArrayList;

public class ThreadsManager
{
   protected ArrayList<Thread> lesThreads = new ArrayList<>();
   
   private static final ThreadsManager instance = new ThreadsManager();
   
   /**
    * Gets instance.
    *
    * @return the instance
    */
   public static ThreadsManager getInstance() { return instance; }
   
   /**
    * Lancer.
    *
    * @param task the task
    */
   public void lancer(Task<Void> task)
   {
      Thread temp = new Thread(task);
      this.lesThreads.add(temp);
      temp.start();
   }
   
   /**
    * Detruire tout.
    */
   public void detruireTout()
   {
      for(Thread t : this.lesThreads)
      {
         t.interrupt();
      }
      this.lesThreads.clear();
   }
}
