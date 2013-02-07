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
    File tiedosto;
    
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
        this.tiedosto = new File(kansio.getRoot()+"/testi.txt");
        this.tiedostonkasittelija = new Tiedostonkasittelija(tiedosto);
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
        Tiedostonkasittelija t = new Tiedostonkasittelija(new File("t.tili"));
        io.setTiedostonkasittelija(t);
        assertTrue(io.getTiedostonkasittelija() == t);
    }
    
    @Test
    public void tallennaTiliPalauttaaTrueJosTallennusOnnistuu(){
        String polku = kansio.getRoot() + "/testi.txt";
        assertTrue(io.tallennaTili(new Tili("tili"), tiedosto));
        assertTrue(new Tiedostonkasittelija(tiedosto).tiedostoOlemassa());
    }
    
    @Test
    public void tallennaTiliAsettaaTiedostonOikein(){
        String polku = kansio.getRoot() + "/testi2.txt";
        io.tallennaTili(new Tili("tili"), new File(polku));
        assertTrue(io.getTiedostonkasittelija().getTiedosto().equals(new File(polku)));
    }
    
    @Test
    public void lataaTiliPalauttaaTrueJosLatausOnnistuu(){
        String polku = kansio.getRoot() + "/testi.txt";
        Tili tili = new Tili("testi");
        io.tallennaTili(tili, new File(polku));
        Tili tili2 = io.lataaTili(new File(polku));
        
        assertTrue(tili2.getNimi().equals(tili.getNimi()));
    }
    
    @Test
    public void lataaTiliAsettaaTiedostonOikein(){
        String polku = kansio.getRoot() + "/testi2.txt";
        io.tallennaTili(new Tili("tili"), new File(polku));
        io.lataaTili(new File(polku));
        assertTrue(io.getTiedostonkasittelija().getTiedosto().equals(new File(polku)));
    }
}