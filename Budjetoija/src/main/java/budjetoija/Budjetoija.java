package budjetoija;

import budjetoija.kayttoliittyma.GUI;
import budjetoija.logiikka.Summa;
import budjetoija.logiikka.Tili;
import budjetoija.logiikka.Tilitapahtuma;
import budjetoija.logiikka.ToistuvaTilitapahtuma;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Budjetoijan main-luokka, josta ohjelma itsessään käynnistyy.
 * 
 */

public class Budjetoija {

    public static void main(String[] args) {
        Tili tili = new Tili("Tili");
        
        tili.lisaaTilitapahtuma(new Tilitapahtuma("t1", new Summa(100), new GregorianCalendar(2013, 0, 1)));
        tili.lisaaTilitapahtuma(new Tilitapahtuma("t2", new Summa(200), new GregorianCalendar(2013, 0, 2)));
        tili.lisaaTilitapahtuma(new Tilitapahtuma("t3", new Summa(300), new GregorianCalendar(2013, 0, 3)));
        tili.lisaaTilitapahtuma(new Tilitapahtuma("t4", new Summa(300), new GregorianCalendar(2013, 1, 1)));
        tili.lisaaTilitapahtuma(new Tilitapahtuma("t5", new Summa(300), new GregorianCalendar(2013, 2, 1)));
        tili.lisaaTilitapahtuma(new Tilitapahtuma("t3", new Summa(300), new GregorianCalendar(2014, 0, 1)));
        tili.lisaaTilitapahtuma(new Tilitapahtuma("t5", new Summa(300), new GregorianCalendar(2014, 1, 1)));
        
        tili.lisaaToistuvaTilitapahtuma(new ToistuvaTilitapahtuma("tt1", new Summa(100), new GregorianCalendar(2013,0,1), new GregorianCalendar(2014,0,1)));
        
//        Calendar alkuPvm = new GregorianCalendar(2013, 0, 1);
//        alkuPvm.clear(Calendar.HOUR);
//        alkuPvm.clear(Calendar.MINUTE);
//        alkuPvm.clear(Calendar.SECOND);
//        alkuPvm.clear(Calendar.MILLISECOND);
//        
//        Calendar loppuPvm = new GregorianCalendar(2013, 2, 1);
//        loppuPvm.set(Calendar.DAY_OF_MONTH, loppuPvm.getActualMaximum(Calendar.DAY_OF_MONTH));
//        loppuPvm.clear(Calendar.HOUR);
//        loppuPvm.clear(Calendar.MINUTE);
//        loppuPvm.clear(Calendar.SECOND);
//        loppuPvm.clear(Calendar.MILLISECOND);
//        
//        System.out.println(tili.getTilitapahtumatAjalta(alkuPvm, loppuPvm).size());
//        for (Tilitapahtuma t : tili.getTilitapahtumatAjalta(alkuPvm, loppuPvm)){
//            System.out.println(t);
//        }
        
        System.out.println(new Tilitapahtuma("kuvaus", new Summa(1000), new GregorianCalendar(2013,0,15)));
        System.out.println(new ToistuvaTilitapahtuma("kuvaus", new Summa(1000), new GregorianCalendar(2013,0,15), new GregorianCalendar(2014,0,15)));
        
        GUI ui = new GUI(tili);
        java.awt.EventQueue.invokeLater(ui);
    }
}
