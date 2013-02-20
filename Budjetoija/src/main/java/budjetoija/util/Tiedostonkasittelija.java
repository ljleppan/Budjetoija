package budjetoija.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
* Tallentaa ja lataa tekstimuotoista tietoa käyttäjän spesifioimasta tiedostosta.
*/
public class Tiedostonkasittelija {
    /** Käsiteltävä tiedosto. */
    private File tiedosto;

    /**
     * Luokan konstruktori.
     * @param tiedosto Käsiteltävä tiedosto.
     */
    public Tiedostonkasittelija(File tiedosto){
        this.tiedosto = tiedosto;
    }
    
    public File getTiedosto(){
        return this.tiedosto;
    }
    
    public void setTiedosto(File tiedosto){
        this.tiedosto = tiedosto;
    }
    
    /**
     * Lukee tiedoston rivit ArrayList-muotoiseen listaan.
     * @return ArrayList-muotoinen listaus tiedoston riveistä.
     */
    public ArrayList<String> lue(){
        ArrayList<String> data = new ArrayList();
        
        if(!tiedostoOlemassa()){
            return data;
        }
        try{
            BufferedReader lukija = new BufferedReader(new FileReader(this.tiedosto));
            String rivi;
            while ((rivi = lukija.readLine()) != null){
                data.add(rivi);
            }
            lukija.close();
        }
        catch(IOException e){
            System.out.println("Virhe luettaessa tiedostoa:" + e);
            return data;
        }
        return data;
    }

    /**
     * Tallentaa annetut rivit.
     * Tallennuskohde on tiedostonkäsittelijälle toisaalla määritelty tiedostopolku.
     * @param data Tallennettavat rivit ArrayList-muotoisessa listauksessa.
     * @return Onnistumista kuvaava boolean.
     */
    public boolean tallenna(ArrayList<String> data){
        if (!tiedostoOlemassa()){
            luoTiedosto();
        }
        
        try{
            BufferedWriter kirjoittaja = new BufferedWriter(new FileWriter(this.tiedosto));
            for (String rivi : data){
                kirjoittaja.write(rivi);
                kirjoittaja.newLine();
            }
            kirjoittaja.close();
            return true;
        }
        catch(IOException e){
            System.out.println("Virhe kirjoittaessa tiedostoa:" +e);
            return false;
        }
    }

    /**
     * Luo tiedoston.
     * @return Onnistumista kuvaava boolean.
     */
    public boolean luoTiedosto() {
        try{
            tiedosto.createNewFile();
            return true;
        }
        catch(IOException e){
            System.out.println("Virhe alustaessa tiedostoa:" + e);
            return false;
        }
    }

    /**
     * Tarkistaa onko tiedosto olemassa.
     * @return Tiedoston olemassaoloa kuvaavan booleanin.
     */
    public boolean tiedostoOlemassa() {
        return tiedosto.exists();
    }
}
