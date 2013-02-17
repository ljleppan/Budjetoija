package budjetoija.util;

import budjetoija.logiikka.Paivamaara;
import budjetoija.logiikka.Summa;
import budjetoija.logiikka.Tili;
import budjetoija.logiikka.ToistuvaTilitapahtuma;
import budjetoija.logiikka.YksittainenTilitapahtuma;
import java.util.ArrayList;
import java.util.Calendar;
/**
* Konvertoi tili-muotoista dataa csv-muotoiseksi ja toisin päin.
*/
public class Konvertoija {
    
    public Konvertoija(){
    }
    
    /**
     * Muuntaa annetun tilin csv-muotoon.
     * Muunnossa tili sekä sen kaikki yksittäiset ja toistuvat tilitapahtumat muunnetaan csv-muotoisiksi stringeiksi, jotka palautetaan ArrayList-muodossa.
     * @param tili Muunnettava tili.
     * @return ArrayList-muotoinen csv-rivien kokoelma.
     */
    public ArrayList<String> tili2csv(Tili tili){
        ArrayList<String> csv = new ArrayList();
        csv.add(tili.getNimi());        
        for (ToistuvaTilitapahtuma tt : tili.getToistuvatTilitapahtumat()){
            csv.add(toistuvaTilitapahtuma2csv(tt));
        }
        csv.add("-");
        for (YksittainenTilitapahtuma t : tili.getTilitapahtumat()){
            csv.add(tilitapahtuma2csv(t));            
        }
        return csv;
    }
    
    /**
     * Muuntaa annetun toistuvan tilitapahtuman csv-muotoon.
     * @param tapahtuma Muunnettava toistuva tilitapahtuma.
     * @return csv-muotoinen string.
     */
    public String toistuvaTilitapahtuma2csv(ToistuvaTilitapahtuma tapahtuma){
        String csv = "";
        csv = csv.concat(tapahtuma.getKuvaus()+";");
        csv = csv.concat(tapahtuma.getSumma()+";");
        csv = csv.concat(tapahtuma.getAlkupvm().get(Calendar.YEAR)+";");
        csv = csv.concat(tapahtuma.getAlkupvm().get(Calendar.MONTH)+";");
        csv = csv.concat(tapahtuma.getAlkupvm().get(Calendar.DAY_OF_MONTH)+";");
        csv = csv.concat(tapahtuma.getLoppupvm().get(Calendar.YEAR)+";");
        csv = csv.concat(tapahtuma.getLoppupvm().get(Calendar.MONTH)+";");
        csv = csv.concat(tapahtuma.getLoppupvm().get(Calendar.DAY_OF_MONTH)+";");
        return csv;
    }
    
    /**
     * Muuntaa annetun tilitapahtuman csv-muotoon.
     * @param tapahtuma Muunnettava tilitapahtuma.
     * @return csv-muotoinen string.
     */
    public String tilitapahtuma2csv(YksittainenTilitapahtuma tapahtuma){
        String csv = "";
        csv = csv.concat(tapahtuma.getKuvaus()+";");
        csv = csv.concat(tapahtuma.getSumma()+";");
        csv = csv.concat(tapahtuma.getAikaleima().get(Calendar.YEAR)+";");
        csv = csv.concat(tapahtuma.getAikaleima().get(Calendar.MONTH)+";");
        csv = csv.concat(tapahtuma.getAikaleima().get(Calendar.DAY_OF_MONTH)+";");
        return csv;
    }
    
    /**
     * Muuntaa syötteenä saadun csv-datan tiliksi.
     * Muunnossa luodaan csv-muotoisen datan pohjalta tili sekä sen yksittäisen ja toistuvat tilitapahtumat.
     * @param csv ArrayList-muotoinen listaus csv-muotoisista stringeistä.
     * @return Palautettu tili.
     */
    public Tili csv2tili(ArrayList<String> csv){        
        Tili tili = new Tili(csv.get(0));
        
        boolean toistuvatKesken = true;
        
        for (String rivi : csv){
            if (toistuvatKesken){
                if(!rivi.equals("-")){
                    ToistuvaTilitapahtuma tt = csv2toistuvaTilitapahtuma(rivi);
                    if (tt != null){
                        tili.lisaaToistuvaTilitapahtuma(tt);
                    }
                } else {
                    toistuvatKesken = false;
                }
            } else {
                YksittainenTilitapahtuma t = csv2tilitapahtuma(rivi);
                if (t != null){
                    tili.lisaaTilitapahtuma(t);
                }
            }
        }
        return tili;
    }
    
    /**
     * Muuntaa csv-muotoisen stringin toistuvaksi tilitapahtumaksi.
     * @param rivi  Muunnettava csv-muotoinen string.
     * @return  Muunnettu toistuva tilitapahtuma.
     */
    public ToistuvaTilitapahtuma csv2toistuvaTilitapahtuma(String rivi){
        String[] riviPalasina = rivi.split(";");
        
        if (riviPalasina.length != 8){
            return null;
        }
                
        String kuvaus = riviPalasina[0];
        Summa summa = new Summa(riviPalasina[1]);

        Paivamaara alkupvm = new Paivamaara();
        alkupvm.set(Calendar.YEAR, Integer.parseInt(riviPalasina[2]));
        alkupvm.set(Calendar.MONTH, Integer.parseInt(riviPalasina[3]));
        alkupvm.set(Calendar.DAY_OF_MONTH, Integer.parseInt(riviPalasina[4]));

        Paivamaara loppupvm = new Paivamaara();
        loppupvm.set(Calendar.YEAR, Integer.parseInt(riviPalasina[5]));
        loppupvm.set(Calendar.MONTH, Integer.parseInt(riviPalasina[6]));
        loppupvm.set(Calendar.DAY_OF_MONTH, Integer.parseInt(riviPalasina[7]));


        return new ToistuvaTilitapahtuma(kuvaus, summa, alkupvm, loppupvm);
    }
    
    /**
     * Muuntaa csv-muotoisen stringin tilitapahtumaksi.
     * @param rivi Muunnettava csv-muotoinen string.
     * @return Muunnettu tilitapahtuma.
     */
    public YksittainenTilitapahtuma csv2tilitapahtuma(String rivi){
        String[] riviPalasina = rivi.split(";");
        
        if (riviPalasina.length != 5){
            return null;
        }
                
        String kuvaus = riviPalasina[0];
        Summa summa = new Summa(riviPalasina[1]);

        Paivamaara aikaleima = new Paivamaara();
        aikaleima.set(Calendar.YEAR, Integer.parseInt(riviPalasina[2]));
        aikaleima.set(Calendar.MONTH, Integer.parseInt(riviPalasina[3]));
        aikaleima.set(Calendar.DAY_OF_MONTH, Integer.parseInt(riviPalasina[4]));
        
        return new YksittainenTilitapahtuma(kuvaus, summa, aikaleima);
    }
}
