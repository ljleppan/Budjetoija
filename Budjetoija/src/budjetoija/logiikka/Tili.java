package budjetoija.logiikka;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Tili {
    private String nimi;
    private ArrayList<Tilitapahtuma> tapahtumat;
    private ArrayList<ToistuvaTilitapahtuma> toistuvatTapahtumat;
    
    public Tili(String nimi){
        this.nimi = nimi;
        this.tapahtumat = new ArrayList<>();
        this.toistuvatTapahtumat = new ArrayList<>();
    }
    
    public String getNimi(){
        return this.nimi;
    }
    
    public void setNimi(String nimi){
        this.nimi = nimi;
    }
    
    public ArrayList<Tilitapahtuma> getTapahtumat(){
        return this.tapahtumat;
    }
    
    public ArrayList<ToistuvaTilitapahtuma> getToistuvatTapahtumat(){
        return this.toistuvatTapahtumat;
    }
    
    public ArrayList<Tilitapahtuma> getTapahtumatAjalta(Calendar alkupvm, Calendar loppupvm){
        ArrayList<Tilitapahtuma> palautettava = new ArrayList<>();
        for (Tilitapahtuma t : tapahtumat){
            //Negaation avulla alkupvm:n ja loppupvm:n tapahtumat mukaan
            if (!t.getAikaleima().after(loppupvm) && !t.getAikaleima().before(alkupvm)){
                palautettava.add(t);
            }
        }        
        return palautettava;
    }
    
    public ArrayList<Tilitapahtuma> getToistuvatTilitapahtumatAjalta(Calendar alkupvm, Calendar loppupvm){
        ArrayList<Tilitapahtuma> palautettava = new ArrayList<>();
        for (ToistuvaTilitapahtuma tt : this.toistuvatTapahtumat){
            int maksukerrat = tt.maksukerratAikavalilla(alkupvm, loppupvm);
            for (int i = 0; i < maksukerrat; i++){
                Calendar aikaleima = new GregorianCalendar(tt.getAlkupvm().get(Calendar.YEAR), tt.getAlkupvm().get(Calendar.MONTH) + i, tt.getAlkupvm().get(Calendar.DAY_OF_MONTH));
                palautettava.add(new Tilitapahtuma(tt.getKuvaus(), tt.getSumma(), aikaleima));
            }
        } 
        return palautettava;
    }
    
    public ArrayList<Tilitapahtuma> getKaikkiTapahtumatAjalta(Calendar alkupvm, Calendar loppupvm){
        ArrayList<Tilitapahtuma> palautettava = getTapahtumatAjalta(alkupvm, loppupvm);
        palautettava.addAll(getToistuvatTilitapahtumatAjalta(alkupvm, loppupvm));
        return palautettava;
    }
    
    public void lisaaTapahtuma(Tilitapahtuma tapahtuma){
        this.tapahtumat.add(tapahtuma);
    }
    
    public boolean poistaTapahtuma(Tilitapahtuma tapahtuma){
        if (this.tapahtumat.contains(tapahtuma)){
            this.tapahtumat.remove(tapahtuma);
            return true;
        }
        return false;
    }
    
    public void lisaaToistuvaTapahtuma(ToistuvaTilitapahtuma tapahtuma){
        this.toistuvatTapahtumat.add(tapahtuma);
    }

}
