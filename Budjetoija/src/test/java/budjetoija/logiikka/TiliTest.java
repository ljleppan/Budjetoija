package budjetoija.logiikka;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
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
public class TiliTest {
    Tili tili;
    YksittainenTilitapahtuma tapahtuma1;
    YksittainenTilitapahtuma tapahtuma2;
    ToistuvaTilitapahtuma toistuvaTapahtuma1;
    ToistuvaTilitapahtuma toistuvaTapahtuma2;
    
    public TiliTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        tili = new Tili("Testitili");
        tapahtuma1 = new YksittainenTilitapahtuma("Tilitapahtuma 1", new Summa(100), new Paivamaara(2013,0,15));
        tapahtuma2 = new YksittainenTilitapahtuma("Tilitapahtuma 2", new Summa(1000), new Paivamaara(2013,5,15));
        toistuvaTapahtuma1 = new ToistuvaTilitapahtuma("Toistuva tilitapahtuma 1", new Summa(100), new Paivamaara(2013,0,15), new Paivamaara(2013,11,31));
        toistuvaTapahtuma2 = new ToistuvaTilitapahtuma("Toistuva tilitapahtuma 2", new Summa(1000), new Paivamaara(2014,0,15), new Paivamaara(2014,11,31));
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void TiliAlustuuOikein(){
        assertTrue(tili.getNimi().equals("Testitili"));
    }
    
    @Test
    public void TiliOnAlustettaessaTyhja(){
        assertTrue(tili.getTilitapahtumat().isEmpty());
        assertTrue(tili.getToistuvatTilitapahtumat().isEmpty());
    }
    
    @Test
    public void TiliSetNimiToimii(){
        tili.setNimi("testi");
        assertTrue(tili.getNimi().equals("testi"));
    }
    
    @Test
    public void TiliSetNimiHylkaaLiaanPitkanNimen(){
        tili.setNimi("1234567890123456789012345678901234567890");
        assertEquals("12345678901234567890", tili.getNimi());
    }
    
    @Test
    public void TilitapahtumanLisaaminenToimii(){
        tili.lisaaTilitapahtuma(tapahtuma1);
        assertTrue(tili.getTilitapahtumat().size() == 1);
    }
    
    @Test
    public void TilitapahtumanPoistaminenToimiiValidillaTilitapahtumalla(){
        tili.lisaaTilitapahtuma(tapahtuma1);
        assertTrue(tili.poistaTilitapahtuma(tapahtuma1));
        assertTrue(tili.getTilitapahtumat().isEmpty());
    }
    
    @Test
    public void TilitapahtumanPoistaminenEiToimiValidillaTilitapahtumalla(){
        tili.lisaaTilitapahtuma(tapahtuma1);
        assertFalse(tili.poistaTilitapahtuma(tapahtuma2));
        assertTrue(tili.getTilitapahtumat().size() == 1);
    }
        
    @Test
    public void TilitapahtumienHakeminenAjallaPalauttaaAjanjaksonTapahtumat(){
        tili.lisaaTilitapahtuma(tapahtuma1);
        assertTrue(tili.getTilitapahtumatAjalta(new Paivamaara(2013,0,1), new Paivamaara(2013,0,31)).size() == 1);
    }
    
    @Test
    public void TilitapahtumienHakeminenAjallaEiPalautaAjanjaksonJalkeisiaTapahtumia(){
        tili.lisaaTilitapahtuma(tapahtuma1);
        tili.lisaaTilitapahtuma(tapahtuma2);
        assertTrue(tili.getTilitapahtumatAjalta(new Paivamaara(2013,0,1), new Paivamaara(2013,0,31)).size() == 1);
    }
    
    @Test
    public void TilitapahtumienHakeminenAjallaEiPalautaAjanjaksoaEdeltaviaTapahtumia(){
        tili.lisaaTilitapahtuma(tapahtuma1);
        tili.lisaaTilitapahtuma(tapahtuma2);
        assertTrue(tili.getTilitapahtumatAjalta(new Paivamaara(2013,2,1), new Paivamaara(2014,0,31)).size() == 1);
    }
    
    @Test
    public void TilitapahtumienHakeminenAjallaPalauttaaAloituspaivanTilitapahtumat(){
        tili.lisaaTilitapahtuma(tapahtuma1);
        assertTrue(tili.getTilitapahtumatAjalta(new Paivamaara(2013,0,15), new Paivamaara(2013,0,31)).size() == 1);
    }
    
    @Test
    public void TilitapahtumienHakeminenAjallaPalauttaaLopetuspaivanTilitapahtumat(){
        tili.lisaaTilitapahtuma(tapahtuma1);
        assertTrue(tili.getTilitapahtumatAjalta(new Paivamaara(2013,0,1), new Paivamaara(2013,0,15)).size() == 1);
    }
    
    @Test
    public void ToistuvienTilitapahtumienLisaaminenToimii(){
        tili.lisaaToistuvaTilitapahtuma(toistuvaTapahtuma1);
        assertTrue(tili.getToistuvatTilitapahtumat().size() == 1);
    }
    
    @Test
    public void ToistuvienTilitapahtuminenPoistaminenToimiiValidillaTapahtumalla(){
        tili.lisaaToistuvaTilitapahtuma(toistuvaTapahtuma1);
        assertTrue(tili.poistaToistuvaTilitapahtuma(toistuvaTapahtuma1));
        assertTrue(tili.getToistuvatTilitapahtumat().isEmpty());
    }
    
    @Test
    public void ToistuvienTilitapahtumienPoistaminenEiToimiEpavalidillaTapahtumalla(){
        tili.lisaaToistuvaTilitapahtuma(toistuvaTapahtuma1);
        assertFalse(tili.poistaToistuvaTilitapahtuma(toistuvaTapahtuma2));
        assertTrue(tili.getToistuvatTilitapahtumat().size() == 1);
    }
    
    @Test
    public void ToistuvienTilitapahtumienHakeminenTilitapahtuminaAjallaPalauttaaOikeanMaaranYksittaisiaTapahtumia(){
        tili.lisaaToistuvaTilitapahtuma(toistuvaTapahtuma1);
        assertTrue(tili.getToistuvatTilitapahtumatTilitapahtuminaAjalta(new Paivamaara(2013,0,1), new Paivamaara(2013,0,31)).size() == 1);
        assertTrue(tili.getToistuvatTilitapahtumatTilitapahtuminaAjalta(new Paivamaara(2012,0,1), new Paivamaara(2014,0,31)).size() == 12);
    }
    
    @Test
    public void ToistuvienTilitapahtuminenHakeminenAjallaPalauttaaAloituspaivanTapahtumat(){
        tili.lisaaToistuvaTilitapahtuma(toistuvaTapahtuma1);
        assertTrue(tili.getToistuvatTilitapahtumatTilitapahtuminaAjalta(new Paivamaara(2013,0,15), new Paivamaara(2013,0,31)).size() == 1);
    }
    
    @Test
    public void ToistuvienTilitapahtumienHakeminenAjallaPalauttaaLopetuspaivanTapahtumat(){
        tili.lisaaToistuvaTilitapahtuma(toistuvaTapahtuma1);
        assertTrue(tili.getToistuvatTilitapahtumatTilitapahtuminaAjalta(new Paivamaara(2013,0,1), new Paivamaara(2013,0,15)).size() == 1);
    }
    
    @Test
    public void ToistuvienTilitapahtumienHakeminenAjallaKonvertoiTilitapahtumilleOikeatAikaleimat(){
        tili.lisaaToistuvaTilitapahtuma(toistuvaTapahtuma1);
        ArrayList<YksittainenTilitapahtuma> t = tili.getToistuvatTilitapahtumatTilitapahtuminaAjalta(new Paivamaara(2013,0,15), new Paivamaara(2013,2,31));
        assertTrue(t.get(2).getAikaleima().get(Calendar.MONTH) == 2);
    }
    
    @Test
    public void ToistuvanTilitapahtumanKonvertointiJaPoistoPoistaaToistuvanTilitapahtuman(){
        tili.lisaaToistuvaTilitapahtuma(toistuvaTapahtuma1);
        assertTrue(tili.konvertoiJaPoistaToistuvaTilitapahtuma(toistuvaTapahtuma1, new Paivamaara(2010,0,1), new Paivamaara(2013,0,1)));
        assertTrue(tili.getToistuvatTilitapahtumat().isEmpty());
    }
    
    @Test
    public void ToistuvanTilitapahtumanKonvertointiJaPoistoEiPoistaOlematontaTilitapahtumaa(){
        tili.lisaaToistuvaTilitapahtuma(toistuvaTapahtuma1);
        assertFalse(tili.konvertoiJaPoistaToistuvaTilitapahtuma(toistuvaTapahtuma2, new Paivamaara(2010,0,1), new Paivamaara(2013,0,1)));
        assertTrue(tili.getToistuvatTilitapahtumat().size() == 1);
    }
    
    @Test
    public void ToistuvanTilitapahtumanKonvertointiJaPoistoToimiiKunYalarajaEnnenAlkupvm(){
        tili.lisaaToistuvaTilitapahtuma(toistuvaTapahtuma1);
        tili.konvertoiJaPoistaToistuvaTilitapahtuma(toistuvaTapahtuma1, new Paivamaara(2010,0,1), new Paivamaara(2013,0,1));
        assertTrue(tili.getTilitapahtumat().isEmpty());
    }
    
    @Test
    public void ToistuvanTilitapahtumanKonvertointiJaPoistoLuoYksittaisenTilitapahtumanJosRajanaOnToistuvanTapahtumanAlkupvm(){
        tili.lisaaToistuvaTilitapahtuma(toistuvaTapahtuma1);
        tili.konvertoiJaPoistaToistuvaTilitapahtuma(toistuvaTapahtuma1, new Paivamaara(2010,0,1), new Paivamaara(2013,0,15));
        assertTrue(tili.getTilitapahtumat().size() == 1);
    }
    
    @Test
    public void ToistuvanTilitapahtumanKonvertointiJaPoistoLuoYksittaisenTilitapahtumanJosRajanaOnToistuvanTapahtumanLoppupvm(){
        tili.lisaaToistuvaTilitapahtuma(toistuvaTapahtuma1);
        tili.konvertoiJaPoistaToistuvaTilitapahtuma(toistuvaTapahtuma1, new Paivamaara(2010,0,1), new Paivamaara(2013,11,31));
        assertTrue(tili.getTilitapahtumat().size() == 12);
    }
    
    @Test
    public void KaikkienTilitapahtumienHakeminenYhdistaaYksittaisetJaToistuvatTapahtumatOikein(){
        tili.lisaaTilitapahtuma(tapahtuma1);
        tili.lisaaToistuvaTilitapahtuma(toistuvaTapahtuma1);
        assertTrue(tili.getKaikkiTilitapahtumatAjalta(new Paivamaara(2013,0,1), new Paivamaara(2013,11,31)).size() == 13);
    }
    
    @Test
    public void getTilitapahtumaPalauttaaTilitapahtumanOikein(){
        tili.lisaaTilitapahtuma(tapahtuma1);
        assertTrue(tapahtuma1.equals(tili.getTilitapahtuma(new YksittainenTilitapahtuma("Tilitapahtuma 1", new Summa(100), new Paivamaara(2013,0,15)))));
    }
    
    @Test
    public void getTilitapahtumaPalauttaaNullJosTilillaEiYhtaanTapahtumaa(){
        assertTrue(tili.getTilitapahtuma(new YksittainenTilitapahtuma("Olematon Tapahtuma", new Summa(100), new Paivamaara(2013,0,15))) == null);
    }
    
    @Test
    public void getTilitapahtumaPalauttaaNullJosTilitapahtumaaEiLoydy(){
        tili.lisaaTilitapahtuma(tapahtuma1);
        assertTrue(tili.getTilitapahtuma(new YksittainenTilitapahtuma("Olematon Tapahtuma", new Summa(100), new Paivamaara(2013,0,15))) == null);
    }
    
    @Test
    public void getToistuvatTilitapahtumaAjaltaPalauttaaTapahtumatOikeinJosAlkuJaLoppuAikarajanSisalla(){
        tili.lisaaToistuvaTilitapahtuma(toistuvaTapahtuma1);
        tili.lisaaToistuvaTilitapahtuma(toistuvaTapahtuma2);
        ArrayList<ToistuvaTilitapahtuma> palaute = tili.getToistuvatTilitapahtumatAjalta(new Paivamaara(2013, 0, 1), new Paivamaara(2015, 0 ,1));
        assertTrue(palaute.size() == 2);
    }
    
    @Test
    public void getToistuvatTilitapahtumatAjaltaPalauttaaTapahtumatOikeinJosAlkuJaLoppuAikarajanAlkuJaLoppuPaivina(){
        tili.lisaaToistuvaTilitapahtuma(toistuvaTapahtuma1);
        tili.lisaaToistuvaTilitapahtuma(toistuvaTapahtuma2);
        ArrayList<ToistuvaTilitapahtuma> palaute = tili.getToistuvatTilitapahtumatAjalta(new Paivamaara(2013, 0, 15), new Paivamaara(2014, 11 ,31));
        assertTrue(palaute.size() == 2);
    }
    
    @Test
    public void getToistuvatTilitapahtumatAjaltaEiPalautaAikarajaaEnnenOleviaTapahtumia(){
        tili.lisaaToistuvaTilitapahtuma(toistuvaTapahtuma1);
        tili.lisaaToistuvaTilitapahtuma(toistuvaTapahtuma2);
        ArrayList<ToistuvaTilitapahtuma> palaute = tili.getToistuvatTilitapahtumatAjalta(new Paivamaara(2014, 0, 1), new Paivamaara(2014, 11 ,31));
        assertTrue(palaute.size() == 1);
    }
    
    @Test
    public void getToistuvatTilitapahtumatAjaltaEiPalautaAikarajanJalkeenOleviaTapahtumia(){
        tili.lisaaToistuvaTilitapahtuma(toistuvaTapahtuma1);
        tili.lisaaToistuvaTilitapahtuma(toistuvaTapahtuma2);
        ArrayList<ToistuvaTilitapahtuma> palaute = tili.getToistuvatTilitapahtumatAjalta(new Paivamaara(2013, 0, 1), new Paivamaara(2014, 0 ,1));
        assertTrue(palaute.size() == 1);
    }
    
    @Test
    public void getToistuvatTilitapahtumatAjaltaPalauttaaOsittainAikarajanJalkeenOlevanTapahtuman(){
        tili.lisaaToistuvaTilitapahtuma(toistuvaTapahtuma1);
        tili.lisaaToistuvaTilitapahtuma(toistuvaTapahtuma2);
        ArrayList<ToistuvaTilitapahtuma> palaute = tili.getToistuvatTilitapahtumatAjalta(new Paivamaara(2013, 0, 1), new Paivamaara(2013, 5 ,1));
        assertTrue(palaute.size() == 1);
    }
    
    @Test
    public void getToistuvatTilitapahtumatAjaltaPalauttaaOsittainAikarajaaEnnenOlevanTapahtuman(){
        tili.lisaaToistuvaTilitapahtuma(toistuvaTapahtuma1);
        tili.lisaaToistuvaTilitapahtuma(toistuvaTapahtuma2);
        ArrayList<ToistuvaTilitapahtuma> palaute = tili.getToistuvatTilitapahtumatAjalta(new Paivamaara(2013, 5, 1), new Paivamaara(2014, 0 ,1));
        assertTrue(palaute.size() == 1);
    }
}