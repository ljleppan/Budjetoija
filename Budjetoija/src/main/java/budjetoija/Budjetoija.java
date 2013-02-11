package budjetoija;

import budjetoija.kayttoliittyma.GUI;
import budjetoija.logiikka.Paivamaara;
import budjetoija.logiikka.Summa;
import budjetoija.logiikka.Tili;
import budjetoija.logiikka.Tilitapahtuma;
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
        Tili tili = new Tili("Tili");
        
        tili.lisaaTilitapahtuma(new Tilitapahtuma("t1", new Summa(100), new Paivamaara(2013, 0, 1)));
        tili.lisaaTilitapahtuma(new Tilitapahtuma("t2", new Summa(200), new Paivamaara(2013, 0, 2)));
        tili.lisaaTilitapahtuma(new Tilitapahtuma("t3", new Summa(300), new Paivamaara(2013, 0, 3)));
        tili.lisaaTilitapahtuma(new Tilitapahtuma("t4", new Summa(300), new Paivamaara(2013, 1, 1)));
        tili.lisaaTilitapahtuma(new Tilitapahtuma("t5", new Summa(300), new Paivamaara(2013, 2, 1)));
        tili.lisaaTilitapahtuma(new Tilitapahtuma("t6", new Summa(300), new Paivamaara(2014, 0, 1)));
        tili.lisaaTilitapahtuma(new Tilitapahtuma("t7", new Summa(300), new Paivamaara(2014, 1, 1)));
        
        tili.lisaaToistuvaTilitapahtuma(new ToistuvaTilitapahtuma("tt1", new Summa(100), new Paivamaara(2013,0,1), new Paivamaara(2014,0,1)));
        
        TallentajaLataaja io = new TallentajaLataaja(new Konvertoija(), new Tiedostonkasittelija(new File("testi.tili")));
        
        GUI ui = new GUI(tili, io);
        java.awt.EventQueue.invokeLater(ui);
    }
}
