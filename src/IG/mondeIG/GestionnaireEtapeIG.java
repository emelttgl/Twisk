package IG.mondeIG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class GestionnaireEtapeIG implements Iterable <EtapeIG>{
   
   protected ArrayList<EtapeIG> etapeIG = new ArrayList<>();
   
   /**
    * Ajouter.
    *
    * @param etapeIG the etape ig
    */
   public void ajouter(EtapeIG... etapeIG)
    {
        this.etapeIG.addAll(Arrays.asList(etapeIG));
    }
   
   /**
    * Gets success.
    *
    * @param i the
    * @return the success
    */
   public EtapeIG getSuccess(int i)
    {
       return this.etapeIG.get(i);
    }
   
   /**
    * Supprimer.
    */
   public void supprimer()
    {
        this.etapeIG.clear();
    }
   
   /**
    * A un success boolean.
    *
    * @return the boolean
    */
   public boolean aUnSuccess()
    {
       return !this.etapeIG.isEmpty();
    }

    @Override
    public Iterator<EtapeIG> iterator() {
        return etapeIG.iterator();
    }
}
