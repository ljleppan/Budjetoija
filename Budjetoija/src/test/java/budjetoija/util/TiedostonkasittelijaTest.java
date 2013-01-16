package budjetoija.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

public class TiedostonkasittelijaTest {
    Tiedostonkasittelija kasittelija;
    
    public TiedostonkasittelijaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        kasittelija = new Tiedostonkasittelija("/tiedosto.txt");
    }
    
    @After
    public void tearDown() {
    }
    
    @Rule
    public TemporaryFolder kansio = new TemporaryFolder();
    
    @Test
    public void tiedostonkasittelijaAlustuuOikein(){
        assertTrue(kasittelija.getTiedostopolku().equals("/tiedosto.txt"));
    }
    
    @Test
    public void tiedostonkasittelijaSetTiedostopolkuToimii(){
        kasittelija.setTiedostopolku("testi");
        assertTrue(kasittelija.getTiedostopolku().equals("testi"));
    }
    
    @Test
    public void tiedostoOnOlemassaLoytaaOlemassaOlevanTiedoston() throws IOException{
        File tiedosto = kansio.newFile("testi.txt");
        String polku = kansio.getRoot() + "/testi.txt";
        assertTrue(kasittelija.tiedostoOnOlemassa(polku));
    }
    
    @Test
    public void tiedostoOnOlemassaEiLoydaOlematontaTiedostoa(){
        assertFalse(kasittelija.tiedostoOnOlemassa("testi.txt"));
    }
    
    @Test
    public void luoTiedostoLuoKasketynTiedoston() throws IOException{
        String polku = kansio.getRoot() + "/testi.txt";
        assertTrue(kasittelija.luoTiedosto(polku));
        assertTrue(kasittelija.tiedostoOnOlemassa(polku));
    }
    
//    @Test
//    public void luoTiedostoPalauttaaFalseJosTiedostonLuontiEiOnnistu() throws IOException{
//
//    }
    
    @Test
    public void lueTiedostoPalauttaaTyhjanJosTiedostoaEiOleOlemassa(){
        assertTrue(kasittelija.lueTiedosto("testi.txt").isEmpty());
    }
    
    @Test
    public void lueTiedostoPalauttaaTiedostonSisallonValidistaTiedostosta(){
        String polku = kansio.getRoot() + "/testi.txt";
        assertTrue(kasittelija.tallennaTiedosto(polku, "testi"));
        assertTrue(kasittelija.lueTiedosto(polku).get(0).equals("testi"));
    }
    
//    @Test
//    public void lueTiedostoPalauttaaNullJosLuettaessaTapahtuuVirhe(){
//        
//    }
    
    @Test
    public void tallennaTiedostoLuoTiedostonJosSeEiOleJoOlemassa(){
        String polku = kansio.getRoot() + "/testi.txt";
        assertFalse(kasittelija.tiedostoOnOlemassa(polku));
        assertTrue(kasittelija.tallennaTiedosto(polku, "testi"));
        assertTrue(kasittelija.tiedostoOnOlemassa(polku));
    }
    
    @Test
    public void tallennaTiedostoKirjoittaaDatanOikeinTyhjaanTiedostoon(){
        String polku = kansio.getRoot() + "/testi.txt";
        assertTrue(kasittelija.tallennaTiedosto(polku, "testi"));
        assertTrue(kasittelija.lueTiedosto(polku).get(0).equals("testi"));
    }
    
    @Test
    public void tallennaTiedostoYlikirjoittaaOlemassaOlevanTiedoston(){
        String polku = kansio.getRoot() + "/testi.txt";
        kasittelija.tallennaTiedosto(polku, "testi1");
        kasittelija.tallennaTiedosto(polku, "testi2");
        ArrayList<String> rivit = kasittelija.lueTiedosto(polku);
        assertTrue(rivit.size() == 1);
        assertTrue(rivit.get(0).equals("testi2"));
    }
    
//    @Test
//    public void tallennaTiedostoPalauttaaFalseJosKirjoittaessaTapahtuuVirhe(){
//        
//    }
}
