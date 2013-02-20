/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package budjetoija.logiikka;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Loezi
 */
public class TilitapahtumaTest {
    Tilitapahtuma t;
    
    public TilitapahtumaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        t = new YksittainenTilitapahtuma("kuvaus", new Summa(1000), new Paivamaara(2013,0,15));
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void TilitapahtumaSetKuvausToimii(){
        t.setKuvaus("testi");
        assertTrue(t.getKuvaus().equals("testi"));
    }
    
    @Test
    public void TilitapahtumaSetKuvausLyhentaaLiianPitkanKuvauksen(){
        t.setKuvaus("1234567890123456789012345678901234567890");
        assertEquals("12345678901234567890123456789012345", t.getKuvaus());
    }
    
    @Test
    public void TilitapahtumaSetSummaToimii(){
        t.setSumma(new Summa(100));
        assertTrue(t.getSumma().getSummaInt() == 100);
    }
}
