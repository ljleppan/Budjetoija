/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import budjetoija.logiikka.ToistuvaTilitapahtuma;
import java.util.GregorianCalendar;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ljleppan
 */
public class ToistuvaTilitapahtumaTest {
    
    ToistuvaTilitapahtuma t;
    
    public ToistuvaTilitapahtumaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        t = new ToistuvaTilitapahtuma("kuvaus", 1000, new GregorianCalendar(2013,0,15), new GregorianCalendar(2014,0,15));
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void ToistuvaTilitapahtumaAlustuuOikein(){
        assertTrue(t.getKuvaus().equals("kuvaus"));
        assertTrue(t.getSumma() == 1000);
        assertTrue(t.getAlkupvm().equals(new GregorianCalendar(2013,0,15)));
        assertTrue(t.getLoppupvm().equals(new GregorianCalendar(2014,0,15)));
    }
    
//    @Test
//    public void ToistuvaTilitapahtumaTulostuuOikein(){
//        assertTrue()
//    }
    
    @Test
    public void ToistuvaTilitapahtumaMaksukerratOikeinKunAlkuJaLoppuSamassaKuussa(){
        assertTrue(t.maksukerratAikavalilla(new GregorianCalendar(2013,0,15), new GregorianCalendar(2013,0,15)) == 1);
    }
    
    @Test
    public void ToistuvaTilitapahtumaMaksukerratOikeinKunAlkuJaLoppuSamanaVuonna(){
        assertTrue(t.maksukerratAikavalilla(new GregorianCalendar(2013,0,15), new GregorianCalendar(2013,5,15)) == 6);
    }
    
    @Test
    public void ToistuvaTilitapahtumaMaksukerratOikeinKunAlkuJaLoppuEriVuosina(){
        assertTrue(t.maksukerratAikavalilla(new GregorianCalendar(2013,0,15), new GregorianCalendar(2014,0,15)) == 13);
    }
    
    @Test
    public void ToistuvaTilitapahtumaMaksukertojaEiOleJosTapahtumaAikarajojenUlkopuolella(){
        assertTrue(t.maksukerratAikavalilla(new GregorianCalendar(2002,0,15), new GregorianCalendar(2003,0,15)) == 0);
    }
}
