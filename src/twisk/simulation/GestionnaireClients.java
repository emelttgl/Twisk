package twisk.simulation;

import twisk.monde.Etape;

import java.util.HashMap;
import java.util.Iterator;

public class GestionnaireClients implements Iterable<Client>
{
   protected HashMap<Integer, Client> clients = new HashMap<>();
   protected int nbClients;
   
   /**
    * Instantiates a new Gestionnaire clients.
    */
   public GestionnaireClients() {}
   
   /**
    * Instantiates a new Gestionnaire clients.
    *
    * @param nbClients the nb clients
    */
   public GestionnaireClients(int nbClients) { this.setNbClients(nbClients); }
   
   /**
    * Sets clients.
    *
    * @param tabClients the tab clients
    */
   public void setClients(int... tabClients)
    {
        for(int i : tabClients)
        {
            this.clients.put(i, new Client(i));
        }
    }
   
   /**
    * Sets nb clients.
    *
    * @param nbClients the nb clients
    */
   public void setNbClients(int nbClients) { this.nbClients = nbClients; }
   
   /**
    * Gets client.
    *
    * @param numeroClient the numero client
    * @return the client
    */
   public Client getClient(int numeroClient) { return this.clients.get(numeroClient); }
   
   /**
    * Aller a.
    *
    * @param numeroClient the numero client
    * @param e            the e
    * @param rang         the rang
    */
   public void allerA(int numeroClient, Etape e, int rang) { this.clients.get(numeroClient).allerA(e, rang); }
   
   /**
    * Nettoyer.
    */
   public void nettoyer() { this.clients.clear(); }

    @Override
    public Iterator<Client> iterator() { return this.clients.values().iterator(); }
}
