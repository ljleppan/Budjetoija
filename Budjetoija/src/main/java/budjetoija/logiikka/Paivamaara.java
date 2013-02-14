package budjetoija.logiikka;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Kuvaa päivämäärää.
 * Toimii kuten GregorianCalendar, mutta ei sisällä tietoa päivää pienemmistä aikayksiköistä. 
 */
public class Paivamaara extends GregorianCalendar{
    
    public Paivamaara(){
        super();
        super.clear(Calendar.HOUR);
        super.clear(Calendar.MINUTE);
        super.clear(Calendar.SECOND);
        super.clear(Calendar.MILLISECOND);
    }
    
    public Paivamaara(int vuosi, int kuukausi, int paiva){
        super(vuosi, kuukausi, paiva);
        super.clear(Calendar.HOUR);
        super.clear(Calendar.MINUTE);
        super.clear(Calendar.SECOND);
        super.clear(Calendar.MILLISECOND);
    }
    
    @Override
    public String toString(){
        String palaute = String.format("%td.%tm.%tY", this, this, this);
        return palaute;
    }
}
