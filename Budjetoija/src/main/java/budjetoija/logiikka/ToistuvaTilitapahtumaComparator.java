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
class ToistuvaTilitapahtumaComparator implements Comparator<ToistuvaTilitapahtuma>{

    public ToistuvaTilitapahtumaComparator() {
    }

    @Override
    public int compare(ToistuvaTilitapahtuma o1, ToistuvaTilitapahtuma o2) {
        return o1.getAlkupvm().compareTo(o2.getAlkupvm());
    }
    
}
