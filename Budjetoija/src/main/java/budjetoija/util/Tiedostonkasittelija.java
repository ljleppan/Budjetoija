package budjetoija.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

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
    
    public ArrayList<String> lueTiedosto(String tiedostopolku){
        ArrayList<String> data = new ArrayList();
        
        if(!tiedostoOnOlemassa(tiedostopolku)){
            return data;
        }
        try{
            BufferedReader lukija = new BufferedReader(new FileReader(tiedostopolku));
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

    public boolean tallennaTiedosto(String tiedostopolku, String data){
        if(!tiedostoOnOlemassa(tiedostopolku)){
            luoTiedosto(tiedostopolku);
        }
        try{
            BufferedWriter kirjoittaja = new BufferedWriter(new FileWriter(tiedostopolku));
            kirjoittaja.write(data);
            kirjoittaja.close();
            return true;
        }
        catch(IOException e){
            System.out.println("Virhe kirjoittaessa tiedostoa:" +e);
            return false;
        }
    }

    public boolean luoTiedosto(String tiedostopolku) {
        try{
            File tiedosto = new File(tiedostopolku);
            tiedosto.createNewFile();
            return true;
        }
        catch(IOException e){
            System.out.println("Virhe alustaessa tiedostoa:" + e);
            return false;
        }
    }

    public boolean tiedostoOnOlemassa(String tiedostopolku) {
        File tiedosto = new File(tiedostopolku);
        return tiedosto.exists();
    }
}
