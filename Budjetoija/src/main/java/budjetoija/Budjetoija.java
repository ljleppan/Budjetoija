package budjetoija;

import budjetoija.kayttoliittyma.GUI;
import budjetoija.logiikka.Paivamaara;
import budjetoija.logiikka.Summa;
import budjetoija.logiikka.Tili;
import budjetoija.logiikka.YksittainenTilitapahtuma;
import budjetoija.logiikka.ToistuvaTilitapahtuma;
import budjetoija.util.Konvertoija;
import budjetoija.util.TallentajaLataaja;
import budjetoija.util.Tiedostonkasittelija;
import java.io.File;

/**
 * Budjetoijan main-luokka, josta ohjelma itsessään käynnistyy.
 * 
 */

public class Budjetoija {

    public static void main(String[] args) {
        Tili tili = new Tili("");        
        TallentajaLataaja io = new TallentajaLataaja(new Konvertoija(), new Tiedostonkasittelija(new File("tili.tili")));
        GUI ui = new GUI(tili, io);
        java.awt.EventQueue.invokeLater(ui);
    }
}
