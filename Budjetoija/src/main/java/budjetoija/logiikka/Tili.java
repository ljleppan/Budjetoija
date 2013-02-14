package budjetoija.logiikka;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

/**
 * Kuvaa tiliä, jonka muodostavat erilaiset toistuvat ja yksittäiset tilitapahtumat.
 * 
 */

public class Tili {
    private String nimi;
    private ArrayList<Tilitapahtuma> tapahtumat;
    private ArrayList<ToistuvaTilitapahtuma> toistuvatTapahtumat;
    
    public Tili(String nimi){
        this.nimi = nimi;
        this.tapahtumat = new ArrayList();
        this.toistuvatTapahtumat = new ArrayList();
    }
    
    public String getNimi(){
        return this.nimi;
    }
    
    public void setNimi(String nimi){
        this.nimi = nimi;
    }
    
    /**
     * Palauttaa tiliin tallennetun tilitapahtuman joka on funktionaalisesti identtinen syötteen kanssa.
     * 
     * @param tilitapahtuma Tilitapahtuma jonka kanssa funktionaalisesti identtistä tilitapahtumaa etsitään.
     * 
     * @return Viittaus löydettyyn tilitapahtumaan tai null, mikäli vastaavaa tilitapahtumaa ei löytynyt.
     */
    public Tilitapahtuma getTilitapahtuma(Tilitapahtuma tilitapahtuma){
        for (Tilitapahtuma t : this.tapahtumat){
            if (t.equals(tilitapahtuma)){
                return t;
            }
        }
        return null;
    }
    
    /**
     * Järjestelee yksittäiset tilitapahtumat aikaleiman mukaan ja palauttaa viittauksen niiden listaukseen.
     * 
     * @return ArrayList -muotoinen listaus tilitapahtumista aikaleiman mukaan järjestettynä.
     */
    public ArrayList<Tilitapahtuma> getTilitapahtumat(){
        Collections.sort(tapahtumat, new TilitapahtumaComparator());
        return this.tapahtumat;
    }
    
    /**
     * Järjestelee toistuvat tilitapahtumat alkuajankohdan mukaan ja palauttaa viittauksen niiden listaukseen.
     * 
     * @return ArrayList -muotoinen listaust toistuvista tilitapahtumista aikaleiman mukaan järjestettynä.
     */
    public ArrayList<ToistuvaTilitapahtuma> getToistuvatTilitapahtumat(){
        Collections.sort(toistuvatTapahtumat, new ToistuvaTilitapahtumaComparator());
        return this.toistuvatTapahtumat;
    }
    
    /**
     * Palauttaa listauksen tiettyjen päivämäärien välille sijoittuvista tilitapahtumista.
     * 
     * @param alkupvm   Alhaisin hyväksyttävä päivämäärä.
     * @param loppupvm  Viimeisin hyväksyttävä päivämäärä.
     * 
     * @return ArrayList -muotoinen listaus tilitapahtumista.
     */
    public ArrayList<Tilitapahtuma> getTilitapahtumatAjalta(Paivamaara alkupvm, Paivamaara loppupvm){
        ArrayList<Tilitapahtuma> palautettava = new ArrayList();     
        alkupvm.add(Calendar.DAY_OF_MONTH, -1);
        for (Tilitapahtuma t : tapahtumat){
            if (t.getAikaleima().after(alkupvm) && !(t.getAikaleima().after(loppupvm))){
                palautettava.add(t);
            }
        }        
        Collections.sort(palautettava, new TilitapahtumaComparator());
        return palautettava;
    }
    
   /**
    * Palauttaa päivämäärien sisään jäävien toistuvien tilitapahtumien tapahtumakerrat yksittäisiksi tilitapahtumiksi muutettuina.
    * 
    * Järjestää palautettavan listan tapahtumat aikajärjestykseen.
    * 
    * @param alkupvm   Aikaisin hyväksyttävä päivämäärä.
    * @param loppupvm  Viimeisin hyväksyttävä päivämäärä.
    * 
    * @return ArrayList -muotoinen listaus, jossa jokainen toistuvan tilitapahtuman toistumakerta yksittäisenä tilitapahtumana.
    */
    public ArrayList<Tilitapahtuma> getToistuvatTilitapahtumatTilitapahtuminaAjalta(Paivamaara alkupvm, Paivamaara loppupvm){
        ArrayList<Tilitapahtuma> palautettava = new ArrayList();
        alkupvm.add(Calendar.DAY_OF_MONTH, -1);
        for (ToistuvaTilitapahtuma tt : this.toistuvatTapahtumat){
            int maksukerrat = tt.maksukerratAikavalilla(alkupvm, loppupvm);
            for (int i = 0; i < maksukerrat; i++){
                Paivamaara aikaleima = new Paivamaara(
                        tt.getAlkupvm().get(Calendar.YEAR), 
                        tt.getAlkupvm().get(Calendar.MONTH) + i, 
                        tt.getAlkupvm().get(Calendar.DAY_OF_MONTH));
                palautettava.add(new Tilitapahtuma(tt.getKuvaus(), tt.getSumma(), aikaleima));
            }
        }
        Collections.sort(palautettava, new TilitapahtumaComparator());
        return palautettava;
    }
    
    /**
     * Palauttaa edes osittain annetun päivämäärävälin sisään jäävät toistuvat tilitapahtumat listana.
     * 
     * Järjestää palautettavan listan toistuvien tilitapahtumien alkupäivämäärien mukaan.
     * 
     * @param   alkupvm     Ensimmäinen hyväksyttävä päivämäärä.
     * @param   loppupvm    Viimeinen hyväksyttävä päivämäärä.
     * 
     * @return ArrayList -muotoinen listaus tilitapahtumista.
     */
    public ArrayList<ToistuvaTilitapahtuma> getToistuvatTilitapahtumatAjalta(Calendar alkupvm, Calendar loppupvm){
        ArrayList<ToistuvaTilitapahtuma> palautettava = new ArrayList();
        alkupvm.add(Calendar.DAY_OF_MONTH, -1);
        for (ToistuvaTilitapahtuma tt : this.toistuvatTapahtumat){
            if ((tt.getAlkupvm().after(alkupvm) && tt.getAlkupvm().before(loppupvm))
                    || (tt.getLoppupvm().after(alkupvm) && tt.getLoppupvm().before(loppupvm))
                    || (tt.getAlkupvm().before(alkupvm) && tt.getLoppupvm().after(loppupvm))){
                palautettava.add(tt);
            }
        }
        Collections.sort(palautettava, new ToistuvaTilitapahtumaComparator());
        return palautettava;
    }
    
    /**
     * Palauttaa kaikkien tiettyjen päivämäärien välillä tapahtuvien tilitapahtumien listauksen.
     * 
     * Listaus sisältää kaikki aikavälin yksittäiset tilitapahtumat sekä toistuvien tilitapahtumien aikavälin sisään jäävät kerrat yksittäisinä tilitapahtumina.
     * Listaus on järjestetty tapahtumien päivämäärien mukaan.
     * 
     * @param   alkupvm     Ensimmäinen hyväksyttävä päivämäärä.
     * @param   loppupvm    Viimeinen hyväksyttävä päivämäärä.
     * 
     * @return  ArrayList -muotoinen listaus tilitapahtumista.
     */
    public ArrayList<Tilitapahtuma> getKaikkiTilitapahtumatAjalta(Paivamaara alkupvm, Paivamaara loppupvm){
        ArrayList<Tilitapahtuma> palautettava = new ArrayList();
        palautettava.addAll(getTilitapahtumatAjalta(alkupvm, loppupvm));
        palautettava.addAll(getToistuvatTilitapahtumatTilitapahtuminaAjalta(alkupvm, loppupvm));
        Collections.sort(palautettava, new TilitapahtumaComparator());
        return palautettava;
    }
    
    /**
     * Lisää tilille syötteenä annetun tilitapahtuman
     * 
     * @param tapahtuma tilille lisättävä tilitapahtuma.
     */
    public void lisaaTilitapahtuma(Tilitapahtuma tapahtuma){
        this.tapahtumat.add(tapahtuma);
    }
    
    /**
     * Poistaa tililtä syötteenä annettua tapahtumaa vastaavan tapahtuman.
     * 
     * @param   tapahtuma   poistettava tilitapahtuma.
     * 
     * @return  Suorituksen onnistumista kuvaava boolean.
     */    
    public boolean poistaTilitapahtuma(Tilitapahtuma tapahtuma){
        if (this.tapahtumat.contains(tapahtuma)){
            this.tapahtumat.remove(tapahtuma);
            return true;
        }
        return false;
    }
    
   /**
    * Lisää tilille toistuvan tilitapahtuman.
    * 
    * @param tapahtuma tilille lisättävä toistuva tilitapahtuma.
    */   
    public void lisaaToistuvaTilitapahtuma(ToistuvaTilitapahtuma tapahtuma){
        this.toistuvatTapahtumat.add(tapahtuma);
    }
    
    /**
     * Muuntaa tilitapahtumiksi ja poistaa toistuvan tilitapahtuman.
     * 
     * Muuntaa syötteenä annetun toistuvan tilitapahtuman ennen loppupäivämäärä 
     * sijoittuvat tapahtumakerrat yksittäisiksi tilitapahtumiksi ja poistaa
     * toistuvan tilitapahtuman.
     * 
     * @param   tapahtuma   Muunnettava ja poistettava toistuva tilitapahtuma.
     * @param   loppupvm    Päivämäärä jonka jälkeisiä toistuvan tilitapahtuman tapahtumakertoja ei kovenvertoida.
     * 
     * @return  Suorituksen onnistumista kuvaava boolean.
     */
    public boolean konvertoiJaPoistaToistuvaTilitapahtuma(ToistuvaTilitapahtuma tapahtuma, Paivamaara alkupvm, Paivamaara loppupvm){
        if (!poistaToistuvaTilitapahtuma(tapahtuma)){
            return false;
        }
        ArrayList<Tilitapahtuma> konvertoitu = tapahtuma.konvertoiYksittaisiksiTapahtumiksi(alkupvm, loppupvm);
        this.tapahtumat.addAll(konvertoitu);       
        return true;
    }

    /**
     * Poistaa toistuvan tilitapahtuman tililtä.
     * 
     * @param   tapahtuma   Poistettava tapahtuma.
     * 
     * @return Suorituksen onnistumista kuvaava boolean.
     */
    public boolean poistaToistuvaTilitapahtuma(ToistuvaTilitapahtuma tapahtuma){
        if (this.toistuvatTapahtumat.contains(tapahtuma)){
            this.toistuvatTapahtumat.remove(tapahtuma);
            return true;
        }
        return false;
    }
}
