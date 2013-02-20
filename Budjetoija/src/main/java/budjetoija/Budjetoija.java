package budjetoija;

import budjetoija.kayttoliittyma.GUI;
import budjetoija.logiikka.Tili;
import budjetoija.util.TallentajaLataaja;

/**
 * Budjetoijan main-luokka, josta ohjelma itsessään käynnistyy.
 */

public class Budjetoija {

    public static void main(String[] args) {
        Tili tili = new Tili("");        
        TallentajaLataaja io = new TallentajaLataaja();
        GUI ui = new GUI(tili, io);
        java.awt.EventQueue.invokeLater(ui);
    }
}
