package budjetoija.logiikka;

import budjetoija.kayttoliittyma.GUI;
import java.util.Calendar;

/**
 * Kuvaa yksittäistä, sellaisenaan toistumatonta tilitapahtumaa, kuten kauppakäyntiä.
 * 
 */

public class Tilitapahtuma {
    private String kuvaus;
    private Summa summa;
    private Paivamaara aikaleima;
    
    public Tilitapahtuma(String kuvaus, Summa summa, Paivamaara aikaleima){
        this.kuvaus = kuvaus;
        this.summa = summa;
        this.aikaleima = aikaleima;
    }
    
    public String getKuvaus(){
        return this.kuvaus;
    }
    
    /**
     * Asettaa tapahtumalle uuden kuvauksen muokaten tekstin sopivan pituiseksi.
     * Kuvauksen ei anneta olla SUURIN_KUVAUKSEN_PITUUS -merkkiä pidempi.
     * Syötteestä poistetaan myös kaikki puolipisteet, jotta mahdollinen csv-muunnos onnistuu.
     * 
     * @param   kuvaus  tapahtuman uusi kuvaus.
     */    
    public void setKuvaus(String kuvaus){
        this.kuvaus = kuvaus.replaceAll(";", "");
        if (this.kuvaus.length() > GUI.SUURIN_KUVAUKSEN_PITUUS){
            this.kuvaus = this.kuvaus.substring(0, GUI.SUURIN_KUVAUKSEN_PITUUS);
        }
    }
    
    public Summa getSumma(){
        return this.summa;
    }
    
    public void setSumma(Summa summa){
        this.summa = summa;
    }
    
    public Paivamaara getAikaleima(){
        return this.aikaleima;
    }

    /**
     * Asettaa tilitapahtumalle uuden aikaleiman.
     * 
     * @param   aikaleima   tapahtuman uusi aikaleima.
     */    
    public void setAikaleima(Paivamaara aikaleima){
        this.aikaleima = aikaleima;
    }
    
    @Override
    public String toString(){
        return String.format("%-35s   %-15s   %s.%s.%s", 
                this.kuvaus,
                this.summa,
                this.aikaleima.get(Calendar.DAY_OF_MONTH),
                this.aikaleima.get(Calendar.MONTH)+1,
                this.aikaleima.get(Calendar.YEAR));
    }
    
    @Override
    public boolean equals(Object o){
        if (this == o){ return true;}
        if (!(o instanceof Tilitapahtuma)){ return false;}
        
        Tilitapahtuma t = (Tilitapahtuma)o;
        if (this.kuvaus.equals(t.kuvaus) &&
                this.summa.equals(t.summa) &&
                this.aikaleima.equals(t.aikaleima)){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + (this.kuvaus != null ? this.kuvaus.hashCode() : 0);
        hash = 37 * hash + (this.summa != null ? this.summa.hashCode() : 0);
        hash = 37 * hash + (this.aikaleima != null ? this.aikaleima.hashCode() : 0);
        return hash;
    }
}
