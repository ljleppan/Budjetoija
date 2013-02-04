package budjetoija.logiikka;

import java.util.Comparator;

/**
 * Toimii Comparator -luokan ilmentymänä ToistuvaTilitapahtuma -luokkaa varten.
 */
public class ToistuvaTilitapahtumaComparator implements Comparator<ToistuvaTilitapahtuma>{

    public ToistuvaTilitapahtumaComparator() {
    }

    @Override
    public int compare(ToistuvaTilitapahtuma o1, ToistuvaTilitapahtuma o2) {
        return o1.getAlkupvm().compareTo(o2.getAlkupvm());
    }
    
}
