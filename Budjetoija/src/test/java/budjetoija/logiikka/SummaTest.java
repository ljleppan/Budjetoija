/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package budjetoija.logiikka;

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
public class SummaTest {
    
    public SummaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void summaAlustuuOikeinIntegerilla(){
        Summa summa = new Summa(123);
        assertTrue(summa.getSummaInt() == 123);
    }
    
    @Test
    public void summaAlustuuOikeinStringillaJossaVainEuroja(){
        Summa summa = new Summa("123");
        assertTrue("odotettiin 12300, saatiin " + summa.getSummaInt(), summa.getSummaInt() == 12300);
    }
    
    @Test
    public void summaAlustuuOikeinStringillaJossaEurojaJaPiste(){
        Summa summa = new Summa("123.");
        assertTrue("odotettiin 12300, saatiin " + summa.getSummaInt(),summa.getSummaInt() == 12300);
    }
    
    @Test
    public void summaAlustuuOikeinStringillaJossaYksiDesimaali(){
        Summa summa = new Summa("123.4");
        assertTrue("odotettiin 12340, saatiin " + summa.getSummaInt(),summa.getSummaInt() == 12340);
    }
    
    @Test
    public void summaAlustuuOikeinStringillaJossaKaksiDesimaalia(){
        Summa summa = new Summa("123.45");
        assertTrue("odotettiin 12345, saatiin " + summa.getSummaInt(),summa.getSummaInt() == 12345);
    }
    
    @Test
    public void summaAlustuuOikeinStringillaJossaSeparaattorinaPilkku(){
        Summa summa = new Summa("123,45");
        assertTrue("odotettiin 12345, saatiin " + summa.getSummaInt(),summa.getSummaInt() == 12345);
    }
    
    @Test
    public void summaAlustuuOikeinJosSyotteenaNull(){
        Summa summa = new Summa(null);
        assertTrue(summa.getSummaInt() == 0);
    }
    
    @Test
    public void summaAlustuuOikeinJosSyoteKirjaimia(){
        Summa summa = new Summa("asd");
        assertTrue(summa.getSummaInt() == 0);
    }
    
    @Test
    public void summaAlustuuOikeinJosSyotteessaKirjaimiaEnnenSummaa(){
        Summa summa = new Summa("a1,00");
        assertTrue(summa.getSummaInt() == 0);
    }
    
    @Test
    public void summaAlustuuOikeinJosSyotteessaKirjaimiaSummanJalkeen(){
        Summa summa = new Summa("1,00a");
        assertTrue(summa.getSummaInt() == 0);
    }
    
    @Test
    public void summaAlustuuOikeinJosSyotteessaMontaValimerkkia(){
        Summa summa = new Summa("123,4,5");
        assertTrue(summa.getSummaInt() == 0);
    }
    
    @Test
    public void summaSetSummaIntToimii(){
        Summa summa = new Summa("123,45");
        summa.setSummaInt(100);
        assertTrue(summa.getSummaInt() == 100);
    }
    
    @Test
    public void getSummaStringToimiiJosSummaYksiMerkki(){
        Summa summa = new Summa(1);
        assertTrue(summa.getSummaString().equals("0,01"));
    }
    
    @Test
    public void getSummaStringToimiiJosSummaKaksiMerkkia(){
        Summa summa = new Summa(12);
        assertTrue(summa.getSummaString().equals("0,12"));
    }
    
    @Test
    public void getSummaStringToimiiJosSummaKolmeMerkkia(){
        Summa summa = new Summa(123);
        assertTrue(summa.getSummaString().equals("1,23"));
    }
    
    @Test
    public void getSummaStringToimiiJosSummaNeljaMerkkia(){
        Summa summa = new Summa(1234);
        assertTrue(summa.getSummaString().equals("12,34"));
    }  
   
    /*Suurin osa setSummaString:n metodeista testattu jo konstruktorin yhteydessä. */
    
    @Test
    public void setsummaStringPalauttaaFalseJosSyoteNull(){
        Summa summa = new Summa(1234);
        assertFalse(summa.setSummaString(null));
    }
    
    @Test
    public void setSummaStringPalauttaaFalseJosSyoteOnEpavalidi(){
        Summa summa = new Summa(1234);
        assertFalse(summa.setSummaString("asd"));
    }
}
