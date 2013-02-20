package budjetoija.logiikka;

/**
 * Kuvaa yksittäistä, sellaisenaan toistumatonta tilitapahtumaa, kuten kauppakäyntiä.
 * 
 */

public class YksittainenTilitapahtuma extends Tilitapahtuma {
    private Paivamaara aikaleima;
    
    public YksittainenTilitapahtuma(String kuvaus, Summa summa, Paivamaara aikaleima){
        this.kuvaus = kuvaus;
        this.summa = summa;
        this.aikaleima = aikaleima;
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
        return String.format("%-35s   %-15s   %s", 
                this.kuvaus,
                this.summa,
                this.aikaleima);
    }
    
    @Override
    public boolean equals(Object o){
        if (this == o){ return true;}
        if (!(o instanceof YksittainenTilitapahtuma)){ return false;}
        
        YksittainenTilitapahtuma t = (YksittainenTilitapahtuma)o;
        if (this.kuvaus.equals(t.kuvaus) &&
                this.summa.equals(t.summa) &&
                this.aikaleima.equals(t.aikaleima)){
            return true;
        }
        return false;
    }
}
