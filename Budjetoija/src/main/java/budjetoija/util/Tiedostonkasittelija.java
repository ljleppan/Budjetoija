package budjetoija.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
* Tallentaa ja lataa tekstimuotoista tietoa käyttäjän spesifioimasta tiedostosta.
*/

public class Tiedostonkasittelija {
    private String tiedostopolku;

    public Tiedostonkasittelija(String tiedostopolku){
        
        this.tiedostopolku = tiedostopolku;
    }
    
    public String getTiedostopolku(){
        return this.tiedostopolku;
    }
    
    public void setTiedostopolku(String tiedostopolku){
        this.tiedostopolku = tiedostopolku;
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
            BufferedReader lukija = new BufferedReader(new FileReader(this.tiedostopolku));
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
        try{
            BufferedWriter kirjoittaja = new BufferedWriter(new FileWriter(this.tiedostopolku));
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
     * Luo tiedoston, mikäli sitä ei ole olemassa.
     * @return Onnistumista kuvaava boolean.
     */
    public boolean luoTiedosto() {
        try{
            File tiedosto = new File(this.tiedostopolku);
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
        File tiedosto = new File(this.tiedostopolku);
        return tiedosto.exists();
    }
}
