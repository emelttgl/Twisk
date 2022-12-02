package twisk.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.monde.Activite;
import twisk.monde.Etape;
import twisk.monde.Guichet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * The type Etape test.
 */
class EtapeTest
{
    private Etape test;
    private Activite [] tabAct;
    private Guichet [] tabGui;
   
   /**
    * Sets up.
    */
   @BeforeEach
    void setUp() {
        this.tabAct = new Activite[3];
        this.tabGui = new Guichet[3];
        for(int i = 0;i < 3;i++)
        {
            this.tabAct[i] = new Activite("Act_" + i);
            this.tabGui[i] = new Guichet("Gui_" + i);
        }
        this.test = new Activite("Activite");
    }
   
   /**
    * Semaphore.
    */
   @Test
    void semaphore()
    {
        assertEquals(1, this.tabGui[0].getSemaphore());
        assertEquals(2, this.tabGui[1].getSemaphore());
        assertEquals(3, this.tabGui[2].getSemaphore());
    }
   
   /**
    * Ajouter successeur.
    */
   @Test
    void ajouterSuccesseur() {
        this.test.ajouterSuccesseur(this.tabAct);
        this.test.ajouterSuccesseur(this.tabGui);
        assertEquals(6,this.test.nbSuccesseurs());
    }
   
   /**
    * Numero.
    */
   @Test
    void numero()
    {
        assertEquals(0, this.tabAct[0].getNum());
        assertEquals(1, this.tabGui[0].getNum());
        assertEquals(2, this.tabAct[1].getNum());
        assertEquals(3, this.tabGui[1].getNum());
        assertEquals(4, this.tabAct[2].getNum());
        assertEquals(5, this.tabGui[2].getNum());
        assertEquals(6, this.test.getNum());
    }
   
   /**
    * To c.
    */
   @Test
    void toC()
    {
        for(Activite a : this.tabAct)
        {
            assertNotNull(a.toC());
        }
        for(Guichet g : this.tabGui)
        {
            assertNotNull(g.toC());
        }
    }
}