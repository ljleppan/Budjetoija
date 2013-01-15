package budjetoija.logiikka;

import java.util.Calendar;

public class ToistuvaTilitapahtuma{
    private String kuvaus;
    private double summa;
    private Calendar alkupvm;
    private Calendar loppupvm;
    
    public ToistuvaTilitapahtuma(String kuvaus, double summa, Calendar alkupvm, Calendar loppupvm){
        this.kuvaus = kuvaus;
        this.summa = summa;
        this.alkupvm = alkupvm;
        this.loppupvm = loppupvm;
    }
    
    public String getKuvaus(){
        return this.kuvaus;
    }
    
    public void setKuvaus(String kuvaus){
        this.kuvaus = kuvaus;
    }
    
    public double getSumma(){
        return this.summa;
    }
    
    public void setSumma(double summa){
        this.summa = summa;
    }
    
    public Calendar getAlkupvm(){
        return this.alkupvm;
    }
    
    public void setAlkupvm(Calendar alkupvm){
        this.alkupvm = alkupvm;
    }
    
    public Calendar getLoppupvm(){
        return this.loppupvm;
    }

    public void setLoppupvm(Calendar loppupvm){
        this.loppupvm = loppupvm;
    }
    
    public int maksukerratAikavalilla(Calendar alaraja, Calendar ylaraja){
        if (this.alkupvm.after(ylaraja) || this.loppupvm.before(alaraja)){
            return 0;
        }
        
        Calendar alku;
        Calendar loppu;
        
        if (alaraja.before(this.alkupvm)){
            alku = this.alkupvm;
        } else {
            alku = alaraja;
        }
        
        if (ylaraja.after(this.loppupvm)){
            loppu = loppupvm;
        } else {
            loppu = ylaraja;
        }

        int taysiaVuosia = loppu.get(Calendar.YEAR) - alku.get(Calendar.YEAR);
        
        if (taysiaVuosia == 0){
            // Kuukausien indeksi alkaa nollasta.
            return loppu.get(Calendar.MONTH) - alku.get(Calendar.MONTH) + 1;
        } else {
            return (taysiaVuosia * 12) + (alku.get(Calendar.MONTH) + 1);
        }
        
    }
}
