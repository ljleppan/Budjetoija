package budjetoija.util;

import budjetoija.logiikka.Summa;
import budjetoija.logiikka.Tili;
import budjetoija.logiikka.Tilitapahtuma;
import budjetoija.logiikka.ToistuvaTilitapahtuma;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Konvertoija {
    
    public Konvertoija(){
    }
    
    public ArrayList<String> tili2csv(Tili tili){
        ArrayList<String> csv = new ArrayList();
        csv.add(tili.getNimi());        
        for (ToistuvaTilitapahtuma tt : tili.getToistuvatTilitapahtumat()){
            csv.add(toistuvaTilitapahtuma2csv(tt));
        }
        csv.add("-");
        for (Tilitapahtuma t : tili.getTilitapahtumat()){
            csv.add(tilitapahtuma2csv(t));            
        }
        return csv;
    }
    
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
    
    public String tilitapahtuma2csv(Tilitapahtuma tapahtuma){
        String csv = "";
        csv = csv.concat(tapahtuma.getKuvaus()+";");
        csv = csv.concat(tapahtuma.getSumma()+";");
        csv = csv.concat(tapahtuma.getAikaleima().get(Calendar.YEAR)+";");
        csv = csv.concat(tapahtuma.getAikaleima().get(Calendar.MONTH)+";");
        csv = csv.concat(tapahtuma.getAikaleima().get(Calendar.DAY_OF_MONTH)+";");
        return csv;
    }
    
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
                Tilitapahtuma t = csv2tilitapahtuma(rivi);
                if (t != null){
                    tili.lisaaTilitapahtuma(t);
                }
            }
        }
        return tili;
    }
    
    public ToistuvaTilitapahtuma csv2toistuvaTilitapahtuma(String rivi){
        String[] riviPalasina = rivi.split(";");
        
        if (riviPalasina.length != 8){
            return null;
        }
                
        String kuvaus = riviPalasina[0];
        Summa summa = new Summa(riviPalasina[1]);

        Calendar alkupvm = new GregorianCalendar();
        alkupvm.set(Calendar.YEAR, Integer.parseInt(riviPalasina[2]));
        alkupvm.set(Calendar.MONTH, Integer.parseInt(riviPalasina[3]));
        alkupvm.set(Calendar.DAY_OF_MONTH, Integer.parseInt(riviPalasina[4]));

        Calendar loppupvm = new GregorianCalendar();
        loppupvm.set(Calendar.YEAR, Integer.parseInt(riviPalasina[5]));
        loppupvm.set(Calendar.MONTH, Integer.parseInt(riviPalasina[6]));
        loppupvm.set(Calendar.DAY_OF_MONTH, Integer.parseInt(riviPalasina[7]));


        return new ToistuvaTilitapahtuma(kuvaus, summa, alkupvm, loppupvm);
    }
    
    public Tilitapahtuma csv2tilitapahtuma(String rivi){
        String[] riviPalasina = rivi.split(";");
        
        if (riviPalasina.length != 5){
            return null;
        }
                
        String kuvaus = riviPalasina[0];
        Summa summa = new Summa(riviPalasina[1]);

        Calendar aikaleima = new GregorianCalendar();
        aikaleima.set(Calendar.YEAR, Integer.parseInt(riviPalasina[2]));
        aikaleima.set(Calendar.MONTH, Integer.parseInt(riviPalasina[3]));
        aikaleima.set(Calendar.DAY_OF_MONTH, Integer.parseInt(riviPalasina[4]));
        
        return new Tilitapahtuma(kuvaus, summa, aikaleima);
    }
}
