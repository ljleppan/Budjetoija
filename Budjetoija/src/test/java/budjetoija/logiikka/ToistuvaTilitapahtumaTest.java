package budjetoija.logiikka;

import java.util.Calendar;
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
        t = new ToistuvaTilitapahtuma("kuvaus", new Summa(1000), new Paivamaara(2013,0,15), new Paivamaara(2014,0,15));
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void ToistuvaTilitapahtumaAlustuuOikein(){
        assertTrue(t.getKuvaus().equals("kuvaus"));
        assertTrue(t.getSumma().getSummaInt() == 1000);
        assertTrue(t.getAlkupvm().equals(new Paivamaara(2013,0,15)));
        assertTrue(t.getLoppupvm().equals(new Paivamaara(2014,0,15)));
    }
    
    @Test
    public void ToistuvaTilitapahtumaKorjaaVaarinOlevanAikaleiman(){
        ToistuvaTilitapahtuma v = new ToistuvaTilitapahtuma("virhe", new Summa(100), new Paivamaara(2014,0,1), new Paivamaara(2013,0,1));
        assertFalse(v.getAlkupvm().after(v.getLoppupvm()));
    }   
    
    @Test
    public void ToistuvaTilitapahtumaSetKuvausToimii(){
        t.setKuvaus("testi");
        assertTrue(t.getKuvaus().equals("testi"));
    }
    
    @Test
    public void ToistuvaTilitapahtumaSetSummaToimii(){
        t.setSumma(new Summa(1001));
        assertTrue(t.getSumma().getSummaInt() == 1001);
    }
    
    @Test
    public void ToistuvaTilitapahtumaSetAlkupvmToimiiValidillaAikaleimalla(){
        assertTrue(t.setAlkupvm(new Paivamaara(2013,1,1)));
        assertTrue(t.getAlkupvm().equals(new Paivamaara(2013,1,1)));
    }
    
    @Test
    public void ToistuvaTilitapahtumaSetAlkupvmHylkaaEpavalidinAikaleiman(){
        assertFalse(t.setAlkupvm(new Paivamaara(3000,0,1)));
        assertTrue(t.getAlkupvm().equals(new Paivamaara(2013,0,15)));
    }
    
    @Test
    public void ToistuvaTilitapahtumaSetLoppupvmToimiiValidillaAikaleimalla(){
        assertTrue(t.setLoppupvm(new Paivamaara(2014,1,1)));
        assertTrue(t.getLoppupvm().equals(new Paivamaara(2014,1,1)));
    }
    
    @Test
    public void ToistuvatilitapahtumaSetLoppupvmHylkaaEpavalidinAikaleiman(){
        assertFalse(t.setLoppupvm(new Paivamaara(1,0,1)));
        assertTrue(t.getLoppupvm().equals(new Paivamaara(2014,0,15)));
    }
    
    @Test
    public void ToistuvaTilitapahtumaMaksukerratOikeinKunAlkuJaLoppuSamassaKuussa(){
        assertTrue(t.maksukerratAikavalilla(new Paivamaara(2013,0,15), new Paivamaara(2013,0,15)) == 1);
    }
    
    @Test
    public void ToistuvaTilitapahtumaMaksukerratOikeinKunAlkuJaLoppuSamanaVuonna(){
        assertTrue(t.maksukerratAikavalilla(new Paivamaara(2013,3,15), new Paivamaara(2013,6,15)) == 4);
    }
    
    @Test
    public void ToistuvaTilitapahtumaMaksukerratOikeinKunAlkuJaLoppuEriVuosina(){
        assertTrue(t.maksukerratAikavalilla(new Paivamaara(2013,0,15), new Paivamaara(2014,0,15)) == 13);
    }
    
    @Test
    public void ToistuvaTilitapahtumaMaksukertojaEiOleJosTapahtumaEnnenAikaAluetta(){
        assertTrue(t.maksukerratAikavalilla(new Paivamaara(2002,0,15), new Paivamaara(2003,0,15)) == 0);
    }
    
    @Test
    public void ToistuvaTilitapahtumaMaksukertojaEiOleJosTapahtumaAikaAlueenJalkeen(){
        assertTrue(t.maksukerratAikavalilla(new Paivamaara(2020,0,15), new Paivamaara(2021,0,15)) == 0);
    }
    
    @Test
    public void ToistuvaTilitapahtumaTulostuuOikein(){
        assertTrue(t.toString().equals("kuvaus                                10,00             15.01.2013 - 15.01.2014"));
    }
    
    @Test
    public void ToistuvaTilitapahtumaKonvertoituuOikeaksiMaaraksiYksittaisiaTilitapahtumia(){
        assertTrue(t.konvertoiYksittaisiksiTapahtumiksi(new Paivamaara(2010,0,1), new Paivamaara(2012,0,1)).isEmpty());
        assertTrue(t.konvertoiYksittaisiksiTapahtumiksi(new Paivamaara(2010,0,1), new Paivamaara(2013,0,15)).size() == 1);
        assertTrue(t.konvertoiYksittaisiksiTapahtumiksi(new Paivamaara(2010,0,1), new Paivamaara(2013,1,15)).size() == 2);
        assertTrue(t.konvertoiYksittaisiksiTapahtumiksi(new Paivamaara(2010,0,1), new Paivamaara(2014,0,15)).size() == 13);
        assertTrue(t.konvertoiYksittaisiksiTapahtumiksi(new Paivamaara(2010,0,1), new Paivamaara(2015,1,15)).size() == 13);
    }
    
    @Test
    public void ToistuvaTilitapahtumaKonvertoiOikeinAikaleimoin(){
        assertTrue(t.konvertoiYksittaisiksiTapahtumiksi(new Paivamaara(2010,0,1), new Paivamaara(2013,2,15)).get(2).getAikaleima().get(Calendar.MONTH) == 2);
    }
    
        @Test
    public void equalsPalauttaaTrueJosObjektitOvatSamat(){
        assertTrue(t.equals(t));
    }
    
    @Test
    public void equalsPalauttaaFalseJosSyoteEiOleTyyppiaTilitapahtuma(){
        assertFalse(t.equals("asd"));
    }
    
    @Test
    public void equalsTarkistaaKaikkiVertailtavatOminaisuudet(){
        assertFalse(t.equals(new ToistuvaTilitapahtuma("väärä kuvaus", new Summa(1000), new Paivamaara(2013,0,15), new Paivamaara(2014,0,15))));
        assertFalse(t.equals(new ToistuvaTilitapahtuma("kuvaus", new Summa(1001), new Paivamaara(2013,0,15), new Paivamaara(2014,0,15))));
        assertFalse(t.equals(new ToistuvaTilitapahtuma("kuvaus", new Summa(1000), new Paivamaara(2013,1,15), new Paivamaara(2014,0,15))));
        assertFalse(t.equals(new ToistuvaTilitapahtuma("kuvaus", new Summa(1000), new Paivamaara(2013,0,15), new Paivamaara(2014,1,15))));
    }
    
    @Test
    public void equalsHyvaksyyEriObjektinJollaSamatOminaisuudet(){
        assertTrue(t.equals(new ToistuvaTilitapahtuma("kuvaus", new Summa(1000), new Paivamaara(2013,0,15), new Paivamaara(2014,0,15))));
    }
}
