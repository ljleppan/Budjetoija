/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package budjetoija.logiikka;

import java.util.Comparator;

/**
 *
 * @author ljleppan
 */
public class TilitapahtumaComparator implements Comparator<Tilitapahtuma> {

    @Override
    public int compare(Tilitapahtuma o1, Tilitapahtuma o2) {
        return o1.getAikaleima().compareTo(o2.getAikaleima());
    }
    
}
