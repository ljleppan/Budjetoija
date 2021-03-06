package budjetoija.logiikka;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Kuvaa kerran kuussa toteutuvaa vakiosummaista tilitapahtumaa kuten vuokra tai palkka.
 */

public class ToistuvaTilitapahtuma extends Tilitapahtuma{
    /** Ensimmäisen tapahtumakerran päivämäärä. */
    private Paivamaara alkupvm;
    
    /** Päivämäärä johon asti tapahtuma toistuu kerran kuukaudessa. */
    private Paivamaara loppupvm;
    
    /**
     * Luokan konstruktori.
     * @param kuvaus Tapahtuman kuvaus.
     * @param summa Tapahtuman summa.
     * @param alkupvm Ensimmäisen tapahtuman päivämäärä.
     * @param loppupvm  Päivämäärä johon asti tapahtuma toistuu kerran kuukaudessa.
     */
    public ToistuvaTilitapahtuma(String kuvaus, Summa summa, Paivamaara alkupvm, Paivamaara loppupvm){
        this.kuvaus = kuvaus;
        this.summa = summa;
        if (!alkupvm.after(loppupvm)){
            this.alkupvm = alkupvm;
            this.loppupvm = loppupvm;
        } else {
            this.alkupvm = loppupvm;
            this.loppupvm = alkupvm;
        }
    }
    
    public Paivamaara getAlkupvm(){
        return this.alkupvm;
    }
    
    /**
     * Asettaa toistuvalle tilitapahtumalle uuden alkupäivämäärän.
     * Hyväksyy uudeksi alkupäivämääräksi vain päivämäärän joka on ennen loppupäivämäärää.
     * @param alkupvm   Uusi alkupäivämäärä.
     * @return Onnistumista kuvaava boolean.
     */
    public boolean setAlkupvm(Paivamaara alkupvm){
        if (alkupvm.after(this.loppupvm)){
            return false;
        }
        this.alkupvm = alkupvm;
        return true;
    }
    
    public Paivamaara getLoppupvm(){
        return this.loppupvm;
    }

    /**
     * Asettaa toistuvalle tilitapahtumalle uuden loppupäivämäärän.
     * Hyväksyttävän päivämäärän täytyy olla alkupäivämäärän jälkeen.
     * @param loppupvm  Uusi loppupäivämäärä.
     * @return Onnistumista kuvaava boolean.
     */
    public boolean setLoppupvm(Paivamaara loppupvm){
        if (loppupvm.before(this.alkupvm)){
            return false;
        }
        this.loppupvm = loppupvm;
        return true;
    }
    
    /**
     * Laskee toistuvan tilitapahtuman tietyn aikavälin maksukertojen määrän.
     * @param alaraja   Aikarajan alaraja.
     * @param ylaraja   Aikarajan yläraja.
     * @return Maksukertojen määrä.
     */
    public int maksukerratAikavalilla(Paivamaara alaraja, Paivamaara ylaraja){
        if (this.alkupvm.after(ylaraja) || this.loppupvm.before(alaraja)){
            return 0;
        }
        Paivamaara alku = alaraja.before(this.alkupvm) ? this.alkupvm : alaraja;
        Paivamaara loppu = ylaraja.after(this.loppupvm) ? this.loppupvm : ylaraja;

        int taysiaVuosia = loppu.get(Calendar.YEAR) - alku.get(Calendar.YEAR);
        
        if (taysiaVuosia == 0){
            // Kuukausien indeksi alkaa nollasta.
            return loppu.get(Calendar.MONTH) - alku.get(Calendar.MONTH) + 1;
        } else {
            return (taysiaVuosia * 12) + (loppu.get(Calendar.MONTH) - alku.get(Calendar.MONTH) + 1);
        }
        
    }
    
    /**
     * Muuntaa toistuvan tilitapahtuman yksittäisiksi tilitapahtumiksi syötetyn aikavälin osalta.
     * @param alkupvm   Aikavälin alkupäivämäärä.
     * @param loppupvm  Aikavälin loppupäivämäärä.
     * @return ArrayList-muotoinen listaus konvertoiduista tilitapahtumista.
     */
    public ArrayList<YksittainenTilitapahtuma> konvertoiYksittaisiksiTapahtumiksi(Paivamaara alkupvm, Paivamaara loppupvm){
        ArrayList<YksittainenTilitapahtuma> palautettava = new ArrayList();
        
        if(alkupvm == null){
            alkupvm = this.alkupvm;
        }
        if(loppupvm == null){
            loppupvm = this.loppupvm;
        }
        
        int maksukerrat = maksukerratAikavalilla(alkupvm, loppupvm);
            for (int i = 0; i < maksukerrat; i++){
                Paivamaara aikaleima = new Paivamaara(
                        this.alkupvm.get(Calendar.YEAR), 
                        this.alkupvm.get(Calendar.MONTH) + i, 
                        this.alkupvm.get(Calendar.DAY_OF_MONTH));
                palautettava.add(new YksittainenTilitapahtuma(this.kuvaus, this.summa, aikaleima));
            }
        
        return palautettava;
    }
    
    @Override
    public String toString(){
        String tuloste = String.format("%-35s   %-15s   %s - %s", 
                this.kuvaus, 
                this.summa, 
                this.alkupvm, 
                this.loppupvm);
        return tuloste;
    }
    
    @Override
    public boolean equals(Object o){
        if (this == o){ return true;}
        if (!(o instanceof ToistuvaTilitapahtuma)){ return false;}
        
        ToistuvaTilitapahtuma t = (ToistuvaTilitapahtuma)o;
        if (this.kuvaus.equals(t.kuvaus) &&
                this.summa.equals(t.summa) &&
                this.alkupvm.equals((t.alkupvm)) &&
                this.loppupvm.equals(t.loppupvm)){
            return true;
        }
        return false;
    }
}
