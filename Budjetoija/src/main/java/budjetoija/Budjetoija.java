package budjetoija;

import budjetoija.kayttoliittyma.GUI;
import budjetoija.logiikka.Summa;
import budjetoija.logiikka.Tili;
import budjetoija.logiikka.Tilitapahtuma;
import java.util.GregorianCalendar;

public class Budjetoija {

    public static void main(String[] args) {
        Tili tili = new Tili("Tili");
        
        tili.lisaaTilitapahtuma(new Tilitapahtuma("t1", new Summa(100), new GregorianCalendar()));
        tili.lisaaTilitapahtuma(new Tilitapahtuma("t2", new Summa(200), new GregorianCalendar()));
        tili.lisaaTilitapahtuma(new Tilitapahtuma("t3", new Summa(300), new GregorianCalendar()));
        
        GUI ui = new GUI(tili);
        java.awt.EventQueue.invokeLater(ui);
    }
}
