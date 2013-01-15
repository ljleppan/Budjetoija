/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package budjetoija;

import budjetoija.logiikka.Tilitapahtuma;
import java.util.GregorianCalendar;

/**
 *
 * @author Loezi
 */
public class Budjetoija {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Tilitapahtuma a = new Tilitapahtuma("testi", 1000, new GregorianCalendar());
        System.out.println(a);
    }
}
