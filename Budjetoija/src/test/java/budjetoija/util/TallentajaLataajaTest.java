/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package budjetoija.util;

import budjetoija.logiikka.Tili;
import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

/**
 *
 * @author ljleppan
 */
public class TallentajaLataajaTest {
    TallentajaLataaja io;
    Konvertoija konvertoija;
    Tiedostonkasittelija tiedostonkasittelija;
    
    public TallentajaLataajaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.konvertoija = new Konvertoija();
        this.tiedostonkasittelija = new Tiedostonkasittelija(kansio.getRoot()+"/testi.txt");
        this.io = new TallentajaLataaja(konvertoija, tiedostonkasittelija);
    }
    
    @After
    public void tearDown() {
    }
    
    @Rule
    public TemporaryFolder kansio = new TemporaryFolder();
    
    @Test
    public void TallentajaLataajaAlustuuOikein(){
        assertTrue(io.getKonvertoija() == konvertoija);
        assertTrue(io.getTiedostonkasittelija() == tiedostonkasittelija);
    }
    
    @Test
    public void setKonvertoijaToimii(){
        Konvertoija k = new Konvertoija();
        io.setKonvertoija(k);
        assertTrue(io.getKonvertoija() == k);
    }
    
    @Test
    public void setTiedostonkasittelijaToimii(){
        Tiedostonkasittelija t = new Tiedostonkasittelija("./");
        io.setTiedostonkasittelija(t);
        assertTrue(io.getTiedostonkasittelija() == t);
    }
    
    @Test
    public void tallennaTiliPalauttaaTrueJosTallennusOnnistuu(){
        String polku = kansio.getRoot() + "/testi.txt";
        assertTrue(io.tallennaTili(new Tili("tili"), polku));
        assertTrue(new Tiedostonkasittelija(polku).tiedostoOlemassa());
    }
    
    @Test
    public void tallennaTiliAsettaaPolunOikein(){
        String polku = kansio.getRoot() + "/testi2.txt";
        io.tallennaTili(new Tili("tili"), polku);
        assertTrue(io.getTiedostonkasittelija().getTiedostopolku().equals(polku));
    }
    
    @Test
    public void lataaTiliPalauttaaTrueJosLatausOnnistuu(){
        String polku = kansio.getRoot() + "/testi.txt";
        Tili tili = new Tili("testi");
        io.tallennaTili(tili, polku);
        Tili tili2 = io.lataaTili(polku);
        
        assertTrue(tili2.getNimi().equals(tili.getNimi()));
    }
    
    @Test
    public void lataaTiliAsettaaTiedostopolunOikein(){
        String polku = kansio.getRoot() + "/testi2.txt";
        io.tallennaTili(new Tili("tili"), polku);
        io.lataaTili(polku);
        assertTrue(io.getTiedostonkasittelija().getTiedostopolku().equals(polku));
    }
}