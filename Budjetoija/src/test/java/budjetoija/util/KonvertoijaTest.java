/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package budjetoija.util;

import budjetoija.logiikka.Paivamaara;
import budjetoija.logiikka.Summa;
import budjetoija.logiikka.Tili;
import budjetoija.logiikka.YksittainenTilitapahtuma;
import budjetoija.logiikka.ToistuvaTilitapahtuma;
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
public class KonvertoijaTest {
    Konvertoija konvertoija;
    Tili tili;
    YksittainenTilitapahtuma tapahtuma1;
    YksittainenTilitapahtuma tapahtuma2;
    ToistuvaTilitapahtuma toistuvaTapahtuma1;
    ToistuvaTilitapahtuma toistuvaTapahtuma2;
    
    public KonvertoijaTest() {
        konvertoija = new Konvertoija();
        tili = new Tili("tili");
        
        tapahtuma1 = new YksittainenTilitapahtuma("Tapahtuma 1", new Summa("1,23"), new Paivamaara(2012,1,1));
        tapahtuma2 = new YksittainenTilitapahtuma("Tapahtuma 2", new Summa("12,34"), new Paivamaara(2012,1,2));
        
        toistuvaTapahtuma1 = new ToistuvaTilitapahtuma("Toistuva tapahtuma 1", new Summa("123,45"), new Paivamaara(2012,1,1), new Paivamaara(2012,11,31));
        toistuvaTapahtuma2 = new ToistuvaTilitapahtuma("Toistuva tapahtuma 2", new Summa("1234,56"), new Paivamaara(2014,1,1), new Paivamaara(2014,11,31));
        
        tili.lisaaTilitapahtuma(tapahtuma1);
        tili.lisaaTilitapahtuma(tapahtuma2);
        tili.lisaaToistuvaTilitapahtuma(toistuvaTapahtuma1);
        tili.lisaaToistuvaTilitapahtuma(toistuvaTapahtuma2);
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
    public void tilitapahtuma2csvToimii(){
        String csv = konvertoija.tilitapahtuma2csv(tapahtuma1);
        assertTrue(csv.equals("Tapahtuma 1;1,23;2012;1;1;"));
    }
    
    @Test
    public void toistuvaTilitapahtuma2csvToimii(){
        String csv = konvertoija.toistuvaTilitapahtuma2csv(toistuvaTapahtuma1);
        assertTrue(csv.equals("Toistuva tapahtuma 1;123,45;2012;1;1;2012;11;31;"));
    }
    
    @Test
    public void tili2csvTulostaaOikeanMaaranRiveja(){
        ArrayList<String> csv = konvertoija.tili2csv(tili);
        assertTrue(csv.size() == 6);
    }
    
    @Test
    public void tili2csvTulostaaNimenOikein(){
        ArrayList<String> csv = konvertoija.tili2csv(tili);
        assertTrue(csv.get(0).equals("tili"));
    }
    
    @Test
    public void csv2tilitapahtumaToimii(){
        YksittainenTilitapahtuma tapahtuma = konvertoija.csv2tilitapahtuma("Tapahtuma 1;1,23;2012;1;1;");
        assertTrue(tapahtuma.getKuvaus().equals(tapahtuma1.getKuvaus()));
        assertTrue(tapahtuma.getSumma().getSummaInt() == tapahtuma1.getSumma().getSummaInt());
        assertTrue(tapahtuma.getAikaleima().get(Calendar.YEAR) == tapahtuma1.getAikaleima().get(Calendar.YEAR));
        assertTrue(tapahtuma.getAikaleima().get(Calendar.MONTH) == tapahtuma1.getAikaleima().get(Calendar.MONTH));
        assertTrue(tapahtuma.getAikaleima().get(Calendar.DAY_OF_MONTH) == tapahtuma1.getAikaleima().get(Calendar.DAY_OF_MONTH));
    }
    
    @Test
    public void csv2tiliPalauttaaNullJosEpavalidiSyote(){
        assertTrue(konvertoija.csv2tilitapahtuma("asd") == null);
    }
    
    @Test
    public void csv2toistuvaTilitapahtumaToimii(){
        ToistuvaTilitapahtuma tapahtuma = konvertoija.csv2toistuvaTilitapahtuma("Toistuva tapahtuma 1;123,45;2012;1;1;2012;11;31;");
        assertTrue(tapahtuma.getKuvaus().equals(toistuvaTapahtuma1.getKuvaus()));
        assertTrue(tapahtuma.getSumma().getSummaInt() == toistuvaTapahtuma1.getSumma().getSummaInt());
        assertTrue(tapahtuma.getAlkupvm().get(Calendar.YEAR) == toistuvaTapahtuma1.getAlkupvm().get(Calendar.YEAR));
        assertTrue(tapahtuma.getAlkupvm().get(Calendar.MONTH) == toistuvaTapahtuma1.getAlkupvm().get(Calendar.MONTH));
        assertTrue(tapahtuma.getAlkupvm().get(Calendar.DAY_OF_MONTH) == toistuvaTapahtuma1.getAlkupvm().get(Calendar.DAY_OF_MONTH));
        assertTrue(tapahtuma.getLoppupvm().get(Calendar.YEAR) == toistuvaTapahtuma1.getLoppupvm().get(Calendar.YEAR));
        assertTrue(tapahtuma.getLoppupvm().get(Calendar.MONTH) == toistuvaTapahtuma1.getLoppupvm().get(Calendar.MONTH));
        assertTrue(tapahtuma.getLoppupvm().get(Calendar.DAY_OF_MONTH) == toistuvaTapahtuma1.getLoppupvm().get(Calendar.DAY_OF_MONTH));
    }
    
    @Test
    public void csv2ToistuvatilitapahtumaPalauttaaNullJosEpavalidiSyote(){
        assertTrue(konvertoija.csv2toistuvaTilitapahtuma("asd") == null);
    }
    
    @Test
    public void csv2tiliToimii(){
        ArrayList<String> csv = new ArrayList();
        csv.add("tili");
        csv.add("Toistuva tapahtuma 1;123,45;2013;0;1;2013;11;31;");
        csv.add("-");
        csv.add("Tapahtuma 1;1,23;2013;0;1;");
        
        Tili t = konvertoija.csv2tili(csv);
        assertTrue(t.getNimi().equals("tili"));
        assertTrue(t.getTilitapahtumat().size() == 1);
        assertTrue(t.getToistuvatTilitapahtumat().size() == 1);
    }
    
    @Test
    public void csv2tiliEiLisaaEpavalidiaTilitapahtumaa(){
        ArrayList<String> csv = new ArrayList();
        csv.add("tili");
        csv.add("Toistuva tapahtuma 1;123,45;2013;0;1;2013;11;31;");
        csv.add("-");
        csv.add("asd");
        
        Tili t = konvertoija.csv2tili(csv);
        assertTrue(t.getNimi().equals("tili"));
        assertTrue(t.getTilitapahtumat().isEmpty());
        assertTrue(t.getToistuvatTilitapahtumat().size() == 1);
    }
    
    @Test
    public void csv2tiliEiLisaaEpavalidiaToistuvaaTilitapahtumaa(){
        ArrayList<String> csv = new ArrayList();
        csv.add("tili");
        csv.add("asd");
        csv.add("-");
        csv.add("Tapahtuma 1;1,23;2013;0;1;");
        
        Tili t = konvertoija.csv2tili(csv);
        assertTrue(t.getNimi().equals("tili"));
        assertTrue(t.getTilitapahtumat().size() == 1);
        assertTrue(t.getToistuvatTilitapahtumat().isEmpty());
    }
}
