/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package budjetoija.logiikka;

import java.util.Calendar;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author ljleppan
 */
public class PaivamaaraTest {
    Paivamaara pvm;
    
    public PaivamaaraTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void paivamaaraAlustuuOikeinKunKonstruktorilleSyotettyTiettyPaivamaara(){
        pvm = new Paivamaara(2013, 0, 1);
        assertEquals(2013, pvm.get(Calendar.YEAR));
        assertEquals(0, pvm.get(Calendar.MONTH));
        assertEquals(1, pvm.get(Calendar.DAY_OF_MONTH));
    }
    
    @Test
    public void paivamaaraOnIndenttinenToisen10msMyohemminTehdynPaivamaaranKanssa() throws InterruptedException{
        pvm = new Paivamaara();
        Thread.sleep(10);
        Paivamaara pvm2 = new Paivamaara();
        assertTrue(pvm.equals(pvm2));
    }    
}
