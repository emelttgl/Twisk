package IG.vues;

import IG.ecouteurs.EcouteurParamAct;
import IG.ecouteurs.EcouteurParamGui;
import IG.ecouteurs.EcouteurRenommer;
import IG.ecouteurs.EcouteurSuppr;
import IG.exceptions.TwiskException;
import IG.mondeIG.MondeIG;
import IG.outilsIG.MondeEnregistre;
import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class VueMenu extends MenuBar implements Observateur
{
   protected MondeIG mondeIG;
   
   protected Menu monde = new Menu("Monde"), parametre = new Menu("Paramètres");
   protected Menu loi = new Menu("Loi d'arrivée"), edition = new Menu("Edition");
   protected Menu fichier = new Menu("Fichier");
   
   protected MenuItem quitter = new MenuItem("Quitter"), supprimer = new MenuItem("Supprimer");
   protected MenuItem renommer = new MenuItem("Renommer la sélection"), nouveau = new MenuItem("Nouveau");
   protected MenuItem ouvrir = new MenuItem("Ouvrir"), enregistrer = new MenuItem("Enregistrer");
   protected MenuItem deselection = new MenuItem("Effacer la sélection");
   
   protected CustomMenuItem entree = new CustomMenuItem(new Label("Entrée")), sortie = new CustomMenuItem(new Label("Sortie"));
   protected CustomMenuItem paramAct = new CustomMenuItem(new Label("Temps et écart-temps")), paramGui = new CustomMenuItem(new Label("Jetons"));
   protected CustomMenuItem uni = new CustomMenuItem(new Label("Loi Uniforme")), gauss = new CustomMenuItem(new Label("Loi Gaussienne"));
   protected CustomMenuItem poisson = new CustomMenuItem(new Label("Loi de Poisson")), nbClients = new CustomMenuItem(new Label("Nb Clients : 5"));
   protected CustomMenuItem preexistant = new CustomMenuItem(new Label("Monde préexistant"));
   
   /**
    * Instantiates a new Vue menu.
    *
    * @param m the m
    */
   public VueMenu(MondeIG m)
    {
        this.mondeIG = m;
        m.ajouterObservateur(this);

        this.fichier.getItems().addAll(this.ouvrir, this.enregistrer, this.quitter, this.preexistant);
        this.edition.getItems().addAll(this.supprimer, this.renommer, this.deselection, this.nouveau);
        this.monde.getItems().addAll(this.entree, this.sortie, this.nbClients);
        this.parametre.getItems().addAll(this.paramAct, this.paramGui, this.loi);
        this.loi.getItems().addAll(this.uni, this.gauss, this.poisson);

        this.quitter.setOnAction(event -> {
            Platform.exit();
            this.mondeIG.killProc();
            MondeEnregistre.getInstance().close();
        });
        this.quitter.setAccelerator(KeyCombination.keyCombination("CTRL+X"));
        
        this.supprimer.setOnAction(new EcouteurSuppr(this.mondeIG));
        this.renommer.setOnAction(new EcouteurRenommer(this.mondeIG));
        this.nouveau.setOnAction(event -> this.mondeIG.nouveau());
        
        this.ouvrir.setAccelerator(KeyCombination.keyCombination("CTRL+O"));
        this.ouvrir.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            File f = fileChooser.showOpenDialog(new Stage());
            if(f != null)
            {
                this.mondeIG.ouvrir(f);
            }
        });
    
        this.enregistrer.setAccelerator(KeyCombination.keyCombination("CTRL+S"));
        this.enregistrer.setOnAction(event -> {
            TextInputDialog enregistrerDansTwisk = new TextInputDialog();
            enregistrerDansTwisk.setTitle("Enregistrement");
            enregistrerDansTwisk.setHeaderText("Oui ou O pour accepter.\nN'importe quoi d'autres pour refuser.");
            enregistrerDansTwisk.setContentText("Voulez-vous enregistrer ce monde dans Twisk ?");
    
            enregistrerDansTwisk.showAndWait();
            
            if(Objects.equals(enregistrerDansTwisk.getResult().toLowerCase(), "oui") || Objects.equals(enregistrerDansTwisk.getResult().toLowerCase(), "o"))
            {
                enregistrerDansTwisk.setHeaderText("");
                enregistrerDansTwisk.setContentText("Nom du fichier :");
    
                enregistrerDansTwisk.showAndWait();
                
                if(enregistrerDansTwisk.getResult() != null && !Objects.equals(enregistrerDansTwisk.getResult(), " "))
                {
                    File f = new File("mondePreexistant/" + enregistrerDansTwisk.getResult() + ".json");
                    try
                    {
                        f.createNewFile();
                        this.mondeIG.enregistrer(f);
                        MondeEnregistre.getInstance().ajouterFichier(f);
                    }
                    catch(IOException e)
                    {
                        throw new RuntimeException(e);
                    }
                }
            }
            else
            {
                FileChooser fileChooser = new FileChooser();
                File f = fileChooser.showSaveDialog(new Stage());
                if(f != null)
                {
                    this.mondeIG.enregistrer(f);
                }
            }
        });
    
        this.deselection.setOnAction(event -> this.mondeIG.deselectionner());
    
        this.entree.setAccelerator(KeyCombination.keyCombination("ALT+E"));
        Tooltip tooltip = new Tooltip("L'étape sélectionnée deviendra une entrée.");
        this.entree.setOnAction(event -> this.mondeIG.entree());
        Tooltip.install(this.entree.getContent(), tooltip);
    
        this.sortie.setAccelerator(KeyCombination.keyCombination("ALT+S"));
        tooltip = new Tooltip("L'étape sélectionnée deviendra une sortie.");
        this.sortie.setOnAction(event -> this.mondeIG.sortie());
        Tooltip.install(this.sortie.getContent(), tooltip);
    
        tooltip = new Tooltip("Changer les paramètres d'une activité.");
        this.paramAct.setOnAction(new EcouteurParamAct(this.mondeIG));
        Tooltip.install(this.paramAct.getContent(), tooltip);
    
        tooltip = new Tooltip("Changer les paramètres d'un guichet.");
        this.paramGui.setOnAction(new EcouteurParamGui(this.mondeIG));
        Tooltip.install(this.paramGui.getContent(), tooltip);
    
        tooltip = new Tooltip("Permet de choisir le nombre de clients.");
        this.nbClients.setOnAction(event -> {
            TextInputDialog clients = new TextInputDialog();
            clients.setTitle("On change le nombre de clients !");
            clients.setHeaderText("Pas plus de 10 clients.");
            clients.setContentText("Entrer le nombre de clients :");
            
            clients.showAndWait();
            try
            {
                this.mondeIG.estUnInteger(clients.getResult());
                this.mondeIG.setNbClient(Integer.parseInt(clients.getResult()));
                this.nbClients.setText("Nb Clients : " + clients.getResult());
            }
            catch(TwiskException ignored) {}
        });
        Tooltip.install(this.nbClients.getContent(), tooltip);
    
        tooltip = new Tooltip("Permet de changer la loi d'arrivée en Loi Uniforme.");
        this.uni.setOnAction(event -> this.mondeIG.setLoi("Uniforme"));
        Tooltip.install(this.uni.getContent(), tooltip);
    
        tooltip = new Tooltip("Permet de changer la loi d'arrivée en Loi Gausienne.");
        this.gauss.setOnAction(event -> this.mondeIG.setLoi("Gauss"));
        Tooltip.install(this.gauss.getContent(), tooltip);
    
        tooltip = new Tooltip("Permet de changer la loi d'arrivée en Loi de Poisson.");
        this.poisson.setOnAction(event -> this.mondeIG.setLoi("Poisson"));
        Tooltip.install(this.poisson.getContent(), tooltip);
        
        tooltip = new Tooltip("Ouvre une ListView avec les fichiers préexistant.");
        this.preexistant.setOnAction(event -> MondeEnregistre.getInstance().afficherListView());
        Tooltip.install(this.preexistant.getContent(), tooltip);
        
        this.getMenus().addAll(this.fichier, this.edition, this.monde, this.parametre);
    }
    
    @Override
    public void reagir()
    {
        if(this.mondeIG.getEstLancee())
        {
            this.ouvrir.setDisable(true);
            this.enregistrer.setDisable(true);
            this.edition.setDisable(true);
            this.monde.setDisable(true);
            this.parametre.setDisable(true);
            this.preexistant.setDisable(true);
        }
        else
        {
            this.ouvrir.setDisable(false);
            this.enregistrer.setDisable(false);
            this.edition.setDisable(false);
            this.monde.setDisable(false);
            this.parametre.setDisable(false);
            this.preexistant.setDisable(false);
        }
    }
}
