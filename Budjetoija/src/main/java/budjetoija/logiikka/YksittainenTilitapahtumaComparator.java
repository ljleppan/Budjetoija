package budjetoija.logiikka;

import java.util.Comparator;

/**
 * Toteuttaa Comparator -luokan YksittainenTilitapahtuma-luokan ilmentymille.
 */
public class YksittainenTilitapahtumaComparator implements Comparator<YksittainenTilitapahtuma> {

    @Override
    public int compare(YksittainenTilitapahtuma o1, YksittainenTilitapahtuma o2) {
        return o1.getAikaleima().compareTo(o2.getAikaleima());
    }
    
}
