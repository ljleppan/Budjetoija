package budjetoija.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class TiedostonkasittelijaTest {
    Tiedostonkasittelija kasittelija;
    ArrayList<String> testiArray;
    
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
        kasittelija = new Tiedostonkasittelija();
        kasittelija.setTiedosto(new File("/tiedosto.txt"));
        testiArray = new ArrayList();
        testiArray.add("testi");
    }
    
    @After
    public void tearDown() {
    }
    
    @Rule
    public TemporaryFolder kansio = new TemporaryFolder();
    
    @Test
    public void tiedostonkasittelijaAlustuuOikein(){
        assertTrue(kasittelija.getTiedosto().equals(new File("/tiedosto.txt")));
    }
    
    @Test
    public void tiedostonkasittelijaSetTiedostopolkuToimii(){
        kasittelija.setTiedosto(new File("/testi.txt"));
        assertTrue(kasittelija.getTiedosto().equals(new File("/testi.txt")));
    }
    
    @Test
    public void tiedostoOlemassaLoytaaOlemassaOlevanTiedoston() throws IOException{
        File tiedosto = kansio.newFile("testi.txt");
        kasittelija.setTiedosto(tiedosto);
        assertTrue(kasittelija.tiedostoOlemassa());
    }
    
    @Test
    public void tiedostoOlemassaEiLoydaOlematontaTiedostoa(){
        kasittelija.setTiedosto(new File("/tiedosto.txt"));
        assertFalse(kasittelija.tiedostoOlemassa());
    }
    
    @Test
    public void luoTiedostoLuoKasketynTiedoston() throws IOException{
        String polku = kansio.getRoot() + "/testi.txt";
        kasittelija.setTiedosto(new File(polku));
        assertTrue(kasittelija.luoTiedosto());
        assertTrue(kasittelija.tiedostoOlemassa());
    }
    
//    @Test
//    public void luoTiedostoPalauttaaFalseJosTiedostonLuontiEiOnnistu() throws IOException{
//
//    }
    
    @Test
    public void lueTiedostoPalauttaaTyhjanJosTiedostoaEiOleOlemassa(){
        kasittelija.setTiedosto(new File("testi.txt"));
        assertTrue(kasittelija.lue().isEmpty());
    }
    
    @Test
    public void lueTiedostoPalauttaaTiedostonSisallonValidistaTiedostosta(){
        String polku = kansio.getRoot() + "/testi.txt";
        kasittelija.setTiedosto(new File(polku));
        assertTrue(kasittelija.tallenna(testiArray));
        assertTrue(kasittelija.lue().get(0).equals("testi"));
    }
    
//    @Test
//    public void lueTiedostoPalauttaaNullJosLuettaessaTapahtuuVirhe(){
//        
//    }
    
    @Test
    public void tallennaTiedostoLuoTiedostonJosSeEiOleJoOlemassa(){
        String polku = kansio.getRoot() + "/testi.txt";
        kasittelija.setTiedosto(new File(polku));
        assertFalse(kasittelija.tiedostoOlemassa());
        assertTrue(kasittelija.tallenna(testiArray));
        assertTrue(kasittelija.tiedostoOlemassa());
    }
    
    @Test
    public void tallennaTiedostoKirjoittaaDatanOikeinTyhjaanTiedostoon(){
        String polku = kansio.getRoot() + "/testi.txt";
        kasittelija.setTiedosto(new File(polku));
        assertTrue(kasittelija.tallenna(testiArray));
        assertTrue(kasittelija.lue().get(0).equals("testi"));
    }
    
    @Test
    public void tallennaTiedostoYlikirjoittaaOlemassaOlevanTiedoston(){
        String polku = kansio.getRoot() + "/testi.txt";
        kasittelija.setTiedosto(new File(polku));
        kasittelija.tallenna(testiArray);
        testiArray.set(0, "testi2");
        kasittelija.tallenna(testiArray);
        ArrayList<String> rivit = kasittelija.lue();
        assertTrue(rivit.size() == 1);
        assertTrue(rivit.get(0).equals("testi2"));
    }
}
