package budjetoija.logiikka;

import java.util.Comparator;

/**
 *
 * Toteuttaa Comparator -luokan Tilitapahtuma-luokan ilmentymille.
 */
public class TilitapahtumaComparator implements Comparator<Tilitapahtuma> {

    @Override
    public int compare(Tilitapahtuma o1, Tilitapahtuma o2) {
        return o1.getAikaleima().compareTo(o2.getAikaleima());
    }
    
}
