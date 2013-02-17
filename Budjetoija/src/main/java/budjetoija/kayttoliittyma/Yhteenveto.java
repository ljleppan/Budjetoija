package budjetoija.kayttoliittyma;

import budjetoija.logiikka.Paivamaara;
import budjetoija.logiikka.Summa;
import budjetoija.logiikka.Tili;
import budjetoija.logiikka.ToistuvaTilitapahtuma;
import budjetoija.logiikka.YksittainenTilitapahtuma;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Valmistaa tekstimuotoisia yhteenvetoja tilin tapahtumista.
 */

public class Yhteenveto {
    /** Tili josta yhteenveto tehdään. */
    Tili tili;
    
    /**
     * Luokan konstruktori.
     * @param tili tili josta yhteenveto tehdään.
     * @see Tili
     */
    public Yhteenveto(Tili tili){
        this.tili = tili;
    }
    
    public void setTili(Tili tili){
        this.tili = tili;
    }
    
    public Tili getTili(){
        return this.tili;
    }
    
    /**
     * Palauttaa String-muotoisen yhteenvedon tilin tapahtumista.
     * @param alku  Aikavälin alku.
     * @param loppu Aikavälin loppu.
     * @return  String-muotoinen yhteenveto.
     */
    public String yhteenvetoAikavalilta(Paivamaara alku, Paivamaara loppu){
        //Otetaan kloonit, jotta päivämäärien käsittely ei vaikuta metodin ulkopuolella.
        Paivamaara alkuPvm = (Paivamaara) alku.clone();
        Paivamaara loppuPvm = (Paivamaara) loppu.clone();
        
        Paivamaara edellisenJaksonLoppu = (Paivamaara) alku.clone();
        Paivamaara edellisenJaksonAlku = new Paivamaara();
        long jaksonPituus = loppuPvm.getTimeInMillis() - alkuPvm.getTimeInMillis();
        edellisenJaksonAlku.setTimeInMillis(alkuPvm.getTimeInMillis() - jaksonPituus);
        
        //Ei haluta alkupvm:ää sekä alku että loppu saldoihin.
        edellisenJaksonLoppu.add(Calendar.DAY_OF_MONTH, -1);
        edellisenJaksonAlku.add(Calendar.DAY_OF_MONTH, -1);
        
        Summa tamanSaldoAlussa = laskeSaldo(alkuPvm);
        alkuPvm.add(Calendar.DAY_OF_MONTH, -1);
        Summa tamanSaldoLopussa = laskeSaldo(loppuPvm);
        Summa tamanSaldonMuutos = new Summa(tamanSaldoLopussa.getSummaInt() - tamanSaldoAlussa.getSummaInt());
        
        Summa edellisenSaldoAlussa = laskeSaldo(edellisenJaksonAlku);
        edellisenJaksonAlku.add(Calendar.DAY_OF_MONTH, -1);
        Summa edellisenSaldoLopussa = laskeSaldo(edellisenJaksonLoppu);
        Summa edellisenSaldonMuutos = new Summa(edellisenSaldoLopussa.getSummaInt() - edellisenSaldoAlussa.getSummaInt());
        
        return String.format("%s                   %s            %s\n"
                + "              %s - %s    %s - %s\n"
                + "%s           %15s            %15s\n"
                + "%s           %15s            %15s\n"
                + "%s           %15s            %15s",
                tili.getNimi(), "Nykyinen jakso", "Edellinen jakso",
                alkuPvm.toString(), loppuPvm.toString(),
                edellisenJaksonAlku.toString(), edellisenJaksonLoppu.toString(),
                "Alkusaldo: ", tamanSaldoAlussa, edellisenSaldoAlussa,
                "Loppusaldo:", tamanSaldoLopussa, edellisenSaldoLopussa,
                "Muutos:    ", tamanSaldonMuutos, edellisenSaldonMuutos);
    }

    /**
     * Laskee tilin saldon tietyltä aikaväliltä. Palaute Summa-oliona.
     * @param alku  Aikavälin alku
     * @param loppu Aikavälin loppu
     * @return  Summa-muotoinen saldo
     */
    public Summa laskeSaldo(Paivamaara alku, Paivamaara loppu) {
        ArrayList<YksittainenTilitapahtuma> tapahtumat = tili.getKaikkiTilitapahtumatAjalta(alku, loppu);
        int muutos = 0;
        for (YksittainenTilitapahtuma t : tapahtumat){
            muutos += t.getSumma().getSummaInt();
        }
        return new Summa(muutos);
    }

    /**
     * Laskee tilin saldon tiettyyn päivämäärään saakka.
     * @param loppu Viimeinen päivämäärä joka otetaan huomioon.
     * @return Summa-muotoinen saldo.
     */
    public Summa laskeSaldo(Paivamaara loppu) { 
        int saldo = 0;
        saldo += tilitapahtumienSaldo(loppu);
        saldo += toistuvienTilitapahtumienSaldo(loppu);
        return new Summa(saldo);
    }

    /**
     * Laskee yksittäisten tilitapahtumien saldon ennen syötettyä päivämäärää.
     * @param loppu Viimeinen mukaan otettava päivämäärä.
     * @return Int-muotoinen saldo.
     */
    public int tilitapahtumienSaldo(Paivamaara loppu) {
        int saldo = 0;
        ArrayList<YksittainenTilitapahtuma> tapahtumat = tili.getTilitapahtumat();
        for (YksittainenTilitapahtuma t : tapahtumat){
            if (!t.getAikaleima().after(loppu)){
                saldo += t.getSumma().getSummaInt();
            }
        }
        return saldo;
    }
    
    /**
     * Laskee toistuvien tilitapahtumien kokonaissaldon ennen syötettyä päivämäärää.
     * @param loppu Viimeinen mukaan otettava päivämäärä.
     * @return Int-muotoinen saldo.
     */
    public int toistuvienTilitapahtumienSaldo(Paivamaara loppu){
        int saldo = 0;
        ArrayList<ToistuvaTilitapahtuma> toistuvatTapahtumat = tili.getToistuvatTilitapahtumat();
        for (ToistuvaTilitapahtuma tt : toistuvatTapahtumat){
            ArrayList<YksittainenTilitapahtuma> tapahtumat = tt.konvertoiYksittaisiksiTapahtumiksi(new Paivamaara(1,0,1), loppu);
            for (YksittainenTilitapahtuma t : tapahtumat){
                saldo += t.getSumma().getSummaInt();
            }
        }
        return saldo;
    }
    
}
