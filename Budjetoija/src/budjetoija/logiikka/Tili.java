package budjetoija.logiikka;

import java.util.ArrayList;
import java.util.Calendar;

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
    
    public ArrayList getTapahtumat(){
        return this.tapahtumat;
    }
    
    public ArrayList getTapahtumatAjalta(Calendar alkupvm, Calendar loppupvm){
        ArrayList<Tilitapahtuma> palautettava = new ArrayList<>();
        for (Tilitapahtuma t : tapahtumat){
            if (t.getAikaleima().before(loppupvm) && t.getAikaleima().after(alkupvm)){
                palautettava.add(t);
            }
        }
        for (ToistuvaTilitapahtuma tt : toistuvatTapahtumat){
            // Toistuvien tilitapahtumien käsittely
        }
        
        return palautettava;
    }
    
    public boolean setTapahtumat(ArrayList<Tilitapahtuma> tapahtumat){
        this.tapahtumat = tapahtumat;
        return true;
    }
    
    public boolean lisaaTapahtuma(Tilitapahtuma tapahtuma){
        this.tapahtumat.add(tapahtuma);
        return true;
    }
    
    public boolean poistaTapahtuma(Tilitapahtuma tapahtuma){
        if (this.tapahtumat.contains(tapahtuma)){
            this.tapahtumat.remove(tapahtuma);
            return true;
        }
        return false;
    }
    

}
