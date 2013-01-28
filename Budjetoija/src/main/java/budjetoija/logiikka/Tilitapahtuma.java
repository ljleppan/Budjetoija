package budjetoija.logiikka;

import java.util.Calendar;

public class Tilitapahtuma {
    private String kuvaus;
    private Summa summa;
    private Calendar aikaleima;
    
    public Tilitapahtuma(String kuvaus, Summa summa, Calendar aikaleima){
        this.kuvaus = kuvaus;
        this.summa = summa;
        this.aikaleima = aikaleima;
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
    }
    
    @Override
    public String toString(){
        return String.format("%-25s   %-10s   %s.%s.%s", this.kuvaus, this.summa, this.aikaleima.get(Calendar.YEAR), this.aikaleima.get(Calendar.MONTH)+1, this.aikaleima.get(Calendar.DAY_OF_MONTH));
    }
    
    

}
