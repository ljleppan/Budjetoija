package budjetoija.budjetoija;

import budjetoija.logiikka.ToistuvaTilitapahtuma;
import java.util.GregorianCalendar;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
    
    @Test
    public void ToistuvaTilitapahtumaKorjaaVaarinOlevanAikaleiman(){
        ToistuvaTilitapahtuma v = new ToistuvaTilitapahtuma("virhe", 100, new GregorianCalendar(2014,0,1), new GregorianCalendar(2013,0,1));
        assertTrue(!v.getAlkupvm().after(v.getLoppupvm()));
    }   
    
    @Test
    public void ToistuvaTilitapahtumaSetKuvausToimii(){
        t.setKuvaus("testi");
        assertTrue(t.getKuvaus().equals("testi"));
    }
    
    @Test
    public void ToistuvaTilitapahtumaSetSummaToimii(){
        t.setSumma(1001);
        assertTrue(t.getSumma() == 1001);
    }
    
    @Test
    public void ToistuvaTilitapahtumaSetAlkupvmToimiiValidillaAikaleimalla(){
        t.setAlkupvm(new GregorianCalendar(2013,1,1));
        assertTrue(t.getAlkupvm().equals(new GregorianCalendar(2013,1,1)));
    }
    
    @Test
    public void ToistuvaTilitapahtumaSetAlkupvmHylkaaEpavalidinAikaleiman(){
        assertTrue(!t.setAlkupvm(new GregorianCalendar(3000,0,1)));
        assertTrue(t.getAlkupvm().equals(new GregorianCalendar(2013,0,15)));
    }
    
    @Test
    public void ToistuvaTilitapahtumaSetLoppupvmToimiiValidillaAikaleimalla(){
        t.setLoppupvm(new GregorianCalendar(2014,1,1));
        assertTrue(t.getLoppupvm().equals(new GregorianCalendar(2014,1,1)));
    }
    
    @Test
    public void ToistuvatilitapahtumaSetLoppupvmHylkaaEpavalidinAikaleiman(){
        assertTrue(!t.setLoppupvm(new GregorianCalendar(1,0,1)));
        assertTrue(t.getLoppupvm().equals(new GregorianCalendar(2014,0,15)));
    }
    
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
    
    @Test
    public void ToistuvaTilitapahtumaTulostuuOikein(){
        assertTrue(t.toString().equals("kuvaus                      1000,00      2013.1.15 - 2014.1.15"));
    }
    
    @Test
    public void ToistuvaTilitapahtumaKonvertoituuOikeaksiMaaraksiYksittaisiaTilitapahtumia(){
        assertTrue(t.konvertoiYksittaisiksiTapahtumiksi(new GregorianCalendar(2012,0,1)).isEmpty());
        assertTrue(t.konvertoiYksittaisiksiTapahtumiksi(new GregorianCalendar(2013,0,15)).size() == 1);
        assertTrue(t.konvertoiYksittaisiksiTapahtumiksi(new GregorianCalendar(2013,1,15)).size() == 2);
        assertTrue(t.konvertoiYksittaisiksiTapahtumiksi(new GregorianCalendar(2014,0,15)).size() == 13);
        assertTrue(t.konvertoiYksittaisiksiTapahtumiksi(new GregorianCalendar(2015,1,15)).size() == 13);
    }
}
