package budjetoija.logiikka;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ToistuvaTilitapahtuma{
    private String kuvaus;
    private Summa summa;
    private Calendar alkupvm;
    private Calendar loppupvm;
    
    public ToistuvaTilitapahtuma(String kuvaus, Summa summa, Calendar alkupvm, Calendar loppupvm){
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
    
    public Calendar getAlkupvm(){
        return this.alkupvm;
    }
    
    public boolean setAlkupvm(Calendar alkupvm){
        if (alkupvm.after(this.loppupvm)){
            return false;
        }
        this.alkupvm = alkupvm;
        return true;
    }
    
    public Calendar getLoppupvm(){
        return this.loppupvm;
    }

    public boolean setLoppupvm(Calendar loppupvm){
        if (loppupvm.before(this.alkupvm)){
            return false;
        }
        this.loppupvm = loppupvm;
        return true;
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
    
    public ArrayList<Tilitapahtuma> konvertoiYksittaisiksiTapahtumiksi(Calendar loppupvm){
        ArrayList<Tilitapahtuma> palautettava = new ArrayList();
        
        int maksukerrat = maksukerratAikavalilla(alkupvm, loppupvm);
            for (int i = 0; i < maksukerrat; i++){
                Calendar aikaleima = new GregorianCalendar(this.alkupvm.get(Calendar.YEAR), this.alkupvm.get(Calendar.MONTH) + i, this.alkupvm.get(Calendar.DAY_OF_MONTH));
                palautettava.add(new Tilitapahtuma(this.kuvaus, this.summa, aikaleima));
            }
        
        return palautettava;
    }
    
    @Override
    public String toString(){
        String tuloste = String.format("%-25s   %-10s   %s.%s.%s - %s.%s.%s", 
                this.kuvaus, 
                this.summa, 
                this.alkupvm.get(Calendar.YEAR), 
                this.alkupvm.get(Calendar.MONTH)+1, 
                this.alkupvm.get(Calendar.DAY_OF_MONTH),
                this.loppupvm.get(Calendar.YEAR), 
                this.loppupvm.get(Calendar.MONTH)+1, 
                this.loppupvm.get(Calendar.DAY_OF_MONTH)
                );
        return tuloste;
    }
}
