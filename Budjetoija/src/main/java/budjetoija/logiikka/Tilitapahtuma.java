package budjetoija.logiikka;

import java.util.Calendar;
import java.util.Collections;

public class Tilitapahtuma {
    private String kuvaus;
    private Summa summa;
    private Calendar aikaleima;
    
    public Tilitapahtuma(String kuvaus, Summa summa, Calendar aikaleima){
        this.kuvaus = kuvaus;
        this.summa = summa;
        this.aikaleima = aikaleima;
        siistiAikaleima();
    }
    
    public String getKuvaus(){
        return this.kuvaus;
    }
    
    public void setKuvaus(String kuvaus){
        this.kuvaus = kuvaus.replaceAll(";", "");
    }
    
    public Summa getSumma(){
        return this.summa;
    }
    
    public void setSumma(Summa summa){
        this.summa = summa;
    }
    
    public Calendar getAikaleima(){
        return this.aikaleima;
    }
    
    public void setAikaleima(Calendar aikaleima){
        this.aikaleima = aikaleima;
        siistiAikaleima();
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

    private void siistiAikaleima() {
        this.aikaleima.clear(Calendar.HOUR);
        this.aikaleima.clear(Calendar.MINUTE);
        this.aikaleima.clear(Calendar.SECOND);
        this.aikaleima.clear(Calendar.MILLISECOND);
    }
    
    

}
