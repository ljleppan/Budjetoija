package budjetoija.util;

import budjetoija.logiikka.Tili;
import java.io.File;

/**
 * Tallentaa ja lataa tilitietoja tiedostoon ja takaisin.
 */
public class TallentajaLataaja {
    /** CSV muotoon ja takaisin konvertoinnista vastaava olio. */
    private Konvertoija konvertoija;
    
    /** Tiedostojen käsittelystä vastaava olio. */
    private Tiedostonkasittelija kasittelija;
    
    /**
     * Luokan konstruktori.
     * @param konvertoija CSV-konversioista vastaava olio.
     * @param kasittelija Tiedostojen käsittelystä vastaava olio.
     */
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
     * Tallentaa tilin tiedostoon.
     * @param tili Tallennettava tili.
     * @param tiedosto Tiedosto johon tili tallennetaan.
     * @return Onnistumista kuvaava boolean.
     */
    public boolean tallennaTili(Tili tili, File tiedosto){
        kasittelija.setTiedosto(tiedosto);
        return kasittelija.tallenna(konvertoija.tili2csv(tili));
    }
    
    /**
     * Lataa tilin tiedostosta.
     * @param tiedosto Tiedosto josta tili ladataan.
     * @return Ladattu tili.
     */
    public Tili lataaTili(File tiedosto){
        kasittelija.setTiedosto(tiedosto);
        return konvertoija.csv2tili(kasittelija.lue());
    }
}
