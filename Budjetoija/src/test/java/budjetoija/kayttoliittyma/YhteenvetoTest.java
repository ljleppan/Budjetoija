package budjetoija.kayttoliittyma;

import budjetoija.logiikka.Paivamaara;
import budjetoija.logiikka.Summa;
import budjetoija.logiikka.Tili;
import budjetoija.logiikka.YksittainenTilitapahtuma;
import budjetoija.logiikka.ToistuvaTilitapahtuma;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class YhteenvetoTest {
    Tili tili;
    Yhteenveto yv;
    
    public YhteenvetoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.tili = new Tili("Tili");
        
        tili.lisaaTilitapahtuma(new YksittainenTilitapahtuma("t1", new Summa(100), new Paivamaara(2013, 0, 1)));
        tili.lisaaTilitapahtuma(new YksittainenTilitapahtuma("t2", new Summa(200), new Paivamaara(2013, 0, 2)));
        tili.lisaaTilitapahtuma(new YksittainenTilitapahtuma("t3", new Summa(300), new Paivamaara(2013, 0, 3)));
        tili.lisaaTilitapahtuma(new YksittainenTilitapahtuma("t4", new Summa(300), new Paivamaara(2013, 1, 1)));
        tili.lisaaTilitapahtuma(new YksittainenTilitapahtuma("t5", new Summa(300), new Paivamaara(2013, 2, 1)));
        tili.lisaaTilitapahtuma(new YksittainenTilitapahtuma("t6", new Summa(300), new Paivamaara(2014, 0, 1)));
        tili.lisaaTilitapahtuma(new YksittainenTilitapahtuma("t7", new Summa(300), new Paivamaara(2014, 1, 1)));
        
        this.yv = new Yhteenveto(this.tili);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void getTiliToimii(){
        assertTrue(yv.getTili().equals(this.tili));
    }
    
    @Test
    public void setTiliToimii(){
        Tili uusiTili = new Tili("uusitili");
        yv.setTili(uusiTili);
        assertTrue(yv.getTili().equals(uusiTili));
    }
    
    @Test
    public void laskeSaldoAikavalillaToimii(){
        Summa s = yv.laskeSaldo(new Paivamaara(2013,0,2), new Paivamaara(2013,0,3));
        assertEquals(500, s.getSummaInt());
    }
    
    @Test
    public void laskeSaldoAikavalillaToimiiUseallaKuukaudella(){
        Summa s = yv.laskeSaldo(new Paivamaara(2013,0,2), new Paivamaara(2013,1,1));
        assertEquals(800, s.getSummaInt());
    }
    
    @Test
    public void laskeSaldoAikavalillaToimiiUseallaVuodella(){
        Summa s = yv.laskeSaldo(new Paivamaara(2013,0,2), new Paivamaara(2014,0,1));
        assertEquals(1400, s.getSummaInt());
    }
    
    @Test
    public void laskeSaldoLopullaToimii(){
        Summa s = yv.laskeSaldo(new Paivamaara(2013,0,3));
        assertEquals(600, s.getSummaInt());
    }
    
    @Test
    public void laskeSaldoLopullaToimiiUseallaKuukaudella(){
        Summa s = yv.laskeSaldo(new Paivamaara(2013,1,3));
        assertEquals(900, s.getSummaInt());
    }
    
    @Test
    public void laskeSaldoLopullaToimiiUseallaVuodella(){
        Summa s = yv.laskeSaldo(new Paivamaara(2014,0,1));
        assertEquals(1500, s.getSummaInt());
    }
    
    @Test
    public void toistuvienTilitapahtumienSaldoToimiiSamassaKuussa(){
        tili.lisaaToistuvaTilitapahtuma(new ToistuvaTilitapahtuma("tt", new Summa(100), new Paivamaara(2013,0,1), new Paivamaara(2014,11,31)));
        int s = yv.toistuvienTilitapahtumienSaldo(new Paivamaara(2013,0,31));
        assertEquals(100, s);
    }
    
    @Test
    public void toistuvienTilitapahtumienSaldoToimiiTasanKuukaudessa(){
        tili.lisaaToistuvaTilitapahtuma(new ToistuvaTilitapahtuma("tt", new Summa(100), new Paivamaara(2013,0,1), new Paivamaara(2014,11,31)));
        int s = yv.toistuvienTilitapahtumienSaldo(new Paivamaara(2013,1,1));
        assertEquals(200, s);
    }
    
    @Test
    public void toistuvienTilitapahtumienSaldoToimiiSeuraavassaKuussa(){
        tili.lisaaToistuvaTilitapahtuma(new ToistuvaTilitapahtuma("tt", new Summa(100), new Paivamaara(2013,0,1), new Paivamaara(2014,11,31)));
        int s = yv.toistuvienTilitapahtumienSaldo(new Paivamaara(2013,1,28));
        assertEquals(200, s);
    }
    
    @Test
    public void toistuvienTilitapahtumienSaldoToimiiSamanaVuonna(){
        tili.lisaaToistuvaTilitapahtuma(new ToistuvaTilitapahtuma("tt", new Summa(100), new Paivamaara(2013,0,1), new Paivamaara(2014,11,31)));
        int s = yv.toistuvienTilitapahtumienSaldo(new Paivamaara(2013,5,28));
        assertEquals(600, s);
    }
    
    @Test
    public void toistuvienTilitapahtumienSaldoToimiiToisenaVuonna(){
        tili.lisaaToistuvaTilitapahtuma(new ToistuvaTilitapahtuma("tt", new Summa(100), new Paivamaara(2013,0,1), new Paivamaara(2014,11,31)));
        int s = yv.toistuvienTilitapahtumienSaldo(new Paivamaara(2014,11,31));
        assertEquals(2400, s);
    }
    
//    @Test
//    public void yhteenvetoTulostuuOikein(){
//        String tuloste = yv.yhteenvetoAikavalilta(new Paivamaara(2013,0,1), new Paivamaara(2013,0,15));
//        System.out.println(tuloste);
//        assertEquals("Tili                   Nykyinen jakso            Edellinen jakso\n" +
//                     "              31.12.2012 - 15.01.2013    17.12.2012 - 31.12.2012\n" +
//                     "Alkusaldo:                       0,00                       0,00\n" +
//                     "Loppusaldo:                      6,00                       0,00\n" +
//                     "Muutos:                          6,00                       0,00", 
//           tuloste);
//    }
}
