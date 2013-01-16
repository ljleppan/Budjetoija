package budjetoija.logiikka;

import budjetoija.logiikka.Tilitapahtuma;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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
        t = new Tilitapahtuma("kuvaus", 1000, new GregorianCalendar(2013,0,15));
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void TilitapahtumaAlustuuOikein(){
        assertTrue(t.getKuvaus().equals("kuvaus"));
        assertTrue(t.getSumma() == 1000);
        assertTrue(t.getAikaleima().equals(new GregorianCalendar(2013,0,15)));
    }
    
    @Test
    public void TilitapahtumaSetKuvausToimii(){
        t.setKuvaus("testi");
        assertTrue(t.getKuvaus().equals("testi"));
    }
    
    @Test
    public void TilitapahtumaSetSummaToimii(){
        t.setSumma(100);
        assertTrue(t.getSumma() == 100);
    }
    
    @Test
    public void TilitapahtumaSetAikaleimaToimii(){
        t.setAikaleima(new GregorianCalendar (2000,0,1));
        assertTrue(t.getAikaleima().equals(new GregorianCalendar (2000,0,1)));
    }
    
    @Test
    public void TilitapahtumaTulostuuOikein(){
        assertTrue(t.toString().equals("kuvaus                      1000,00      2013.1.15"));
    }
    
    
}
