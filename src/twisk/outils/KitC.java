package twisk.outils;

import twisk.simulation.Client;
import twisk.simulation.GestionnaireClients;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class KitC
{
   /**
    * Crée le répertoire twisk dans /tmp et y copie les fichiers du dossier resources
    */
   public void creerEnvironnement()
   {
      try {
         // création du répertoire twisk sous /tmp. Ne déclenche pas d’erreur si le répertoire existe déjà.
         Path directories = Files.createDirectories(Paths.get("/tmp/twisk"));
         // copie des fichiers depuis le projet sous /tmp/twisk
         String[] liste = {"programmeC.o", "def.h", "codeNatif.o", "loi.o", "loi.h"};
         for (String nom : liste) {
            InputStream source = Objects.requireNonNull(getClass().getResource("/codeC/" + nom)).openStream() ;
            File destination = new File("/tmp/twisk/" + nom) ;
            copier(source, destination);
            // Path source = Paths.get(getClass().getResource("/codeC/" + nom).getPath());
            // Path newdir = Paths.get("/tmp/twisk/");
            // Files.copy(source, newdir.resolve(source.getFileName()), REPLACE_EXISTING);
         }
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   /**
    * Fonction qui copie un fichier donné en paramètre vers une destination en paramètre
    * @param source Fichier à copier
    * @param dest Destination de la copie
    */
   private void copier(InputStream source, File dest) throws IOException {
      OutputStream destinationFile = new FileOutputStream(dest) ;
      // Lecture par segment de 0.5Mo
      byte[] buffer = new byte[512 * 1024];
      int nbLecture;
      while ((nbLecture = source.read(buffer)) != -1){
         destinationFile.write(buffer, 0, nbLecture);
      }
      destinationFile.close();
      source.close();
   }
   
   /**
    * Crée le fichier client.c
    *
    * @param codeC Code C qui sera dans le fichier client.c
    */
   public void creerFichier(String codeC) {
      
      codeC = codeC.replaceAll("([éèà_êâùûîôïöëä])", "");
      
      FileWriter flot;
      BufferedWriter floFiltre;
      try
      {
         flot = new FileWriter("/tmp/twisk/client.c");
         floFiltre = new BufferedWriter(flot);
         floFiltre.write(codeC);
         floFiltre.close();
      }
      catch(IOException e)
      {
         System.out.println(e.getMessage());
      }
   }
   
   /**
    * Fonction servant à compiler client.c en client.o
    */
   public void compiler()
   {
      Runtime runtime = Runtime.getRuntime();
      StringBuilder commande = new StringBuilder("gcc -Wall -fPIC -c /tmp/twisk/client.c -o /tmp/twisk/client.o");
   
      try {
         Process p = runtime.exec(commande.toString());
         // récupération des messages sur la sortie standard et la sortie d’erreur de la commande exécutée
         // à reprendre éventuellement et à adapter à votre code
         BufferedReader output = new BufferedReader(new InputStreamReader(p.getInputStream()));
         BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream()));
         String ligne ;
         while ((ligne = output.readLine()) != null) {
            System.out.println(ligne);
         }while ((ligne = error.readLine()) != null) {
            System.out.println(ligne);
         }
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   
   /**
    * Construit la librairie partagée
    */
   public void construireLaLibrairie()
   {
      Runtime runtime = Runtime.getRuntime();
      StringBuilder commande = new StringBuilder("gcc -shared /tmp/twisk/programmeC.o /tmp/twisk/codeNatif.o /tmp/twisk/client.o /tmp/twisk/loi.o -o /tmp/twisk/libTwisk" + FabriqueNumero.getInstance().getNumeroLib() + ".so");
   
      try {
         Process p = runtime.exec(commande.toString());
         p.waitFor();
         // récupération des messages sur la sortie standard et la sortie d’erreur de la commande exécutée
         // à reprendre éventuellement et à adapter à votre code
         BufferedReader output = new BufferedReader(new InputStreamReader(p.getInputStream()));
         BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream()));
         String ligne;
         while ((ligne = output.readLine()) != null)
         {
            System.out.println(ligne);
         }
         while ((ligne = error.readLine()) != null)
         {
            System.out.println(ligne);
         }
      }
      catch(IOException | InterruptedException e)
      {
         e.printStackTrace();
      }
   }
   
   /**
    * Kill.
    *
    * @param clients the clients
    */
   public void kill(GestionnaireClients clients)
   {
      Runtime runtime = Runtime.getRuntime();
      for(Client c : clients)
      {
         try
         {
            Process p = runtime.exec("kill -9 " + c.getNumeroClient());
            p.waitFor();
         }
         catch(IOException | InterruptedException e)
         {
            throw new RuntimeException(e);
         }
      }
   }
}
