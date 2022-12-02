package IG.vues;

import IG.ecouteurs.EcouteurArc;
import IG.mondeIG.*;
import IG.outilsIG.TailleComposants;
import javafx.application.Platform;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import twisk.simulation.Client;

import java.util.Iterator;
import java.util.Objects;

public class VueMondeIG extends Pane implements Observateur
{
   protected MondeIG monde;
   
   /**
    * Instantiates a new Vue monde ig.
    *
    * @param m the m
    */
   public VueMondeIG(MondeIG m)
   {
      this.monde = m;
      this.monde.ajouterObservateur(this);

      this.setOnMouseClicked(new EcouteurArc(this.monde));
   
      this.reagir();
      this.setStyle("-fx-border-color : black;" + "-fx-border-width: 0 0 2 0;" + "-fx-background-color : lightgrey;");

      this.setOnDragOver(dragEvent -> {
         dragEvent.acceptTransferModes(TransferMode.MOVE);
         dragEvent.consume();
      });

      this.setOnDragDropped(dragEvent -> {
         boolean valide = false;
         try
         {
            VueEtapeIG e = (VueEtapeIG) dragEvent.getGestureSource();
            e.setVue(dragEvent.getX(), dragEvent.getY());
            valide = true;
         }
         catch(Exception e)
         {
            e.getStackTrace();
         }
         finally
         {
            this.monde.notifierObservateurs();
            dragEvent.setDropCompleted(valide);
            dragEvent.consume();
         }
      });
   }
   
   @Override
   public void reagir()
   {
      Runnable cmd = () ->
      {
         getChildren().clear();

         Iterator<ArcIG> it = monde.iteratorArc();
         while(it.hasNext())
         {
            ArcIG arcIG = it.next();
            VueArcIG temp_3;
            if(arcIG.estUneCourbe())
            {
               temp_3 = new VueCourbeIG(monde, (CourbeIG) arcIG);
            }
            else
            {
               temp_3 = new VueLigneDroiteIG(monde, (LigneDroiteIG) arcIG);
            }
            getChildren().add(temp_3);
         }

         for(EtapeIG e : monde.iterator())
         {
            e.reset();
   
            VueEtapeIG temp_1;
            if(e.estUnGuichet())
            {
               temp_1 = new VueGuichetIG(monde, (GuichetIG) e);
            }
            else
            {
               temp_1 = new VueActiviteIG(monde, (ActiviteIG) e);
            }
   
            temp_1.relocate(e.getPosX(), e.getPosY());
            
            if(!this.monde.getEstLancee())
            {
               for(PointDeControleIG p : e)
               {
                  VuePointDeControleIG temp_2 = new VuePointDeControleIG(monde, p);
                  getChildren().add(temp_2);
               }
            }
   
            if(this.monde.getEstLancee())
            {
               if(this.monde.iteratorClient() != null)
               {
                  System.out.println("clients");
                  if(!Objects.equals(e.getNom(), "Entrée") && !Objects.equals(e.getNom(), "Sortie"))
                  {
                     for(Iterator<Client> iter = monde.iteratorClient(); iter.hasNext(); )
                     {
                        Client c = iter.next();
                        System.out.println(c.getNumeroClient());
                        Circle client = new Circle(TailleComposants.getInstance().getRadius());
                        client.setFill(c.getColor());
                        
                        if(Objects.equals(c.getEtape_courante(), monde.getCorrespondanceEtapes().getEtape(e)))
                        {
                           temp_1.addClient(c);
                           temp_1.addToPane(client);
                           System.out.println("cercle");
                        }
                     }
                  }
               }
            }
            else
            {
               this.monde.setContructeurSimulation();
            }
   
            temp_1.setEntreeSortieColor();
            getChildren().addAll(temp_1, temp_1.getEntree_sortie());
         }
      };
      
      if(Platform.isFxApplicationThread())
      {
         cmd.run();
      }
      else
      {
         Platform.runLater(cmd);
      }
   }
}