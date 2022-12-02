package IG.outilsIG;

import IG.mondeIG.MondeIG;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class MondeEnregistre
{
   private static final MondeEnregistre instance = new MondeEnregistre();
   
   /**
    * Gets instance.
    *
    * @return the instance
    */
   public static MondeEnregistre getInstance() { return instance; }
   
   protected MondeIG monde;
   protected ArrayList<File> mondeEnregistre = new ArrayList<>();
   protected MenuItem supprimer = new MenuItem("Supprimer"), ouvrir = new MenuItem("Ouvrir");
   protected ListView<Label> lesFichiersEnregistres = new ListView<>();
   protected ContextMenu contextMenuLabel = new ContextMenu();
   protected Stage stage;
   
   private MondeEnregistre()
   {
      this.contextMenuLabel.getItems().add(this.ouvrir);
      this.contextMenuLabel.getItems().add(new SeparatorMenuItem());
      this.contextMenuLabel.getItems().add(this.supprimer);
      
      this.supprimer.setOnAction(event -> this.supprimerFichier());
      this.ouvrir.setOnAction(event -> this.ouvrirFichier());
      
      this.lesFichiersEnregistres.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
      this.lesFichiersEnregistres.setFixedCellSize(70);
      this.lesFichiersEnregistres.setPrefSize(225, 350);
      
      File f = new File("mondePreexistant");
      if(f.listFiles() != null)
      {
         for(File monde : Objects.requireNonNull(f.listFiles()))
         {
            this.ajouterFichier(monde);
         }
      }
   }
   
   /**
    * Sets monde.
    *
    * @param m the m
    */
   public void setMonde(MondeIG m) { this.monde = m; }
   
   /**
    * Ajouter fichier.
    *
    * @param file the file
    */
   public void ajouterFichier(File file)
   {
      this.mondeEnregistre.add(file);
      
      this.lesFichiersEnregistres.getItems().clear();
   
      for(File f : this.mondeEnregistre)
      {
         Label fichier = new Label(f.getName());
         
         fichier.setPrefHeight(70);
         fichier.setOnContextMenuRequested(event -> this.contextMenuLabel.show(fichier, event.getScreenX(), event.getScreenY()));
         
         this.lesFichiersEnregistres.getItems().add(fichier);
      }
   }
   
   private void supprimerFichier()
   {
      int i = this.lesFichiersEnregistres.getSelectionModel().getSelectedIndex();
      this.mondeEnregistre.get(i).delete();
      this.mondeEnregistre.remove(i);
      this.lesFichiersEnregistres.getItems().remove(i);
   }
   
   private void ouvrirFichier()
   {
      int i = this.lesFichiersEnregistres.getSelectionModel().getSelectedIndex();
      this.monde.ouvrir(this.mondeEnregistre.get(i));
      this.stage.close();
   }
   
   /**
    * Afficher list view.
    */
   public void afficherListView()
   {
      AnchorPane pane = new AnchorPane(this.lesFichiersEnregistres);
   
      this.stage = new Stage();
      this.stage.setScene(new Scene(pane, 225, 350));
      this.stage.show();
   }
   
   /**
    * Close.
    */
   public void close()
   {
      if(this.stage != null)
      {
         this.stage.close();
      }
   }
}
