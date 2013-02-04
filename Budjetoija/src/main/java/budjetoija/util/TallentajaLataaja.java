/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package budjetoija.util;

import budjetoija.logiikka.Tili;

/**
 * Tallentaa ja lataa tilitietoja tiedostoon ja takaisin.
 */
public class TallentajaLataaja {
    private Konvertoija konvertoija;
    private Tiedostonkasittelija kasittelija;
    
    public TallentajaLataaja(Konvertoija konvertoija, Tiedostonkasittelija kasittelija){
        this.konvertoija = konvertoija;
        this.kasittelija = kasittelija;
    }
    
    public Konvertoija getKonvertoija(){
        return this.konvertoija;
    }
    
    public void setKonvertoija(Konvertoija konvertoija){
        this.konvertoija = konvertoija;
    }
    
    public Tiedostonkasittelija getTiedostonkasittelija(){
        return this.kasittelija;
    }
    
    public void setTiedostonkasittelija(Tiedostonkasittelija kasittelija){
        this.kasittelija = kasittelija;
    }
    
    /**
     * Tallentaa tili tiedostoon.
     * @param tili Tallennettava tili.
     * @param tiedostopolku Tiedosto johon tili tallennetaan.
     * @return Onnistumista kuvaava boolean.
     */
    public boolean tallennaTili(Tili tili, String tiedostopolku){
        kasittelija.setTiedostopolku(tiedostopolku);
        return kasittelija.tallenna(konvertoija.tili2csv(tili));
    }
    
    /**
     * Lataa tilin tiedostosta.
     * @param tiedostopolku Tiedosto josta tili ladataan.
     * @return Ladattu tili.
     */
    public Tili lataaTili(String tiedostopolku){
        kasittelija.setTiedostopolku(tiedostopolku);
        return konvertoija.csv2tili(kasittelija.lue());
    }
}
