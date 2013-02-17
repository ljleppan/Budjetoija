package budjetoija.logiikka;

import budjetoija.kayttoliittyma.GUI;

/**
 * Tilitapahtumaa kuvaava abstrakti luokka.
 * YksittäinenTilitapahtuma ja ToistuvaTilitapahtuma toteuttavat tämän luokan.
 */
public abstract class Tilitapahtuma {
    /** Tilitapahtuman kuvaus. */
    String kuvaus;
    
    /** Tilitapahtuman summa. */
    Summa summa;
    
    public String getKuvaus(){
        return kuvaus;
    }
    
    /**
     * Asettaa tilitapahtumalle uuden kuvauksen.
     * Poistaa kuvauksesta puolipisteet mahdollista csv-konversiota varten.
     * Kuvaus myös lyhennetään siten, ettei se ole GUI.SUURIN_KUVAUKSEN_PITUUS merkkiä pidempi.
     * @param kuvaus Tilitapahtuman uusi kuvaus.
     */
    public void setKuvaus(String kuvaus){
        this.kuvaus = kuvaus.replaceAll(";", "");
        if (this.kuvaus.length() > GUI.SUURIN_KUVAUKSEN_PITUUS){
            this.kuvaus = this.kuvaus.substring(0, GUI.SUURIN_KUVAUKSEN_PITUUS);
        }
    }
    
    public Summa getSumma(){
        return summa;
    }
    
    public void setSumma(Summa summa){
        this.summa = summa;
    }
}
