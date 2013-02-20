package budjetoija.kayttoliittyma;

import budjetoija.logiikka.*;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Valmistaa tekstimuotoisia yhteenvetoja tilin tapahtumista.
 */
public class Yhteenveto {

    /**
     * Tili josta yhteenveto tehdään.
     */
    Tili tili;

    /**
     * Luokan konstruktori.
     *
     * @param tili tili josta yhteenveto tehdään.
     * @see Tili
     */
    public Yhteenveto(Tili tili) {
        this.tili = tili;
    }

    public void setTili(Tili tili) {
        this.tili = tili;
    }

    public Tili getTili() {
        return this.tili;
    }

    /**
     * Palauttaa String-muotoisen yhteenvedon tilin tapahtumista.
     *
     * @param alku Aikavälin alku.
     * @param loppu Aikavälin loppu.
     * @return String-muotoinen yhteenveto.
     */
    public String yhteenvetoAikavalilta(Paivamaara alku, Paivamaara loppu) {
        //Otetaan kloonit, jotta päivämäärien käsittely ei vaikuta metodin ulkopuolella.
        Paivamaara alkuPvm = (Paivamaara) alku.clone();
        Paivamaara loppuPvm = (Paivamaara) loppu.clone();

        Paivamaara edellisenJaksonLoppu = (Paivamaara) alku.clone();
        edellisenJaksonLoppu.add(Calendar.DAY_OF_MONTH, -1);
        
        Paivamaara edellisenJaksonAlku = maaritaEdellisenJaksonAlku(alkuPvm, loppuPvm);

        Summa tamanSaldoAlussa = laskeSaldo(alkuPvm);
        Summa tamanSaldoLopussa = laskeSaldo(loppuPvm);
        Summa tamanSaldonMuutos = new Summa(tamanSaldoLopussa.getSummaInt() - tamanSaldoAlussa.getSummaInt());

        Summa edellisenSaldoAlussa = laskeSaldo(edellisenJaksonAlku);
        Summa edellisenSaldoLopussa = laskeSaldo(edellisenJaksonLoppu);
        Summa edellisenSaldonMuutos = new Summa(edellisenSaldoLopussa.getSummaInt() - edellisenSaldoAlussa.getSummaInt());
        
        return String.format("%-20s   %s            %s\n"
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
     *
     * @param alku Aikavälin alku
     * @param loppu Aikavälin loppu
     * @return Summa-muotoinen saldo
     */
    public Summa laskeSaldo(Paivamaara alku, Paivamaara loppu) {
        ArrayList<YksittainenTilitapahtuma> tapahtumat = tili.getKaikkiTilitapahtumatAjalta(alku, loppu);
        int muutos = 0;
        for (YksittainenTilitapahtuma t : tapahtumat) {
            muutos += t.getSumma().getSummaInt();
        }
        return new Summa(muutos);
    }

    /**
     * Laskee tilin saldon tiettyyn päivämäärään saakka.
     *
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
     *
     * @param loppu Viimeinen mukaan otettava päivämäärä.
     * @return Int-muotoinen saldo.
     */
    public int tilitapahtumienSaldo(Paivamaara loppu) {
        int saldo = 0;
        ArrayList<YksittainenTilitapahtuma> tapahtumat = tili.getTilitapahtumat();
        for (YksittainenTilitapahtuma t : tapahtumat) {
            if (!t.getAikaleima().after(loppu)) {
                saldo += t.getSumma().getSummaInt();
            }
        }
        return saldo;
    }

    /**
     * Laskee toistuvien tilitapahtumien kokonaissaldon ennen syötettyä
     * päivämäärää.
     *
     * @param loppu Viimeinen mukaan otettava päivämäärä.
     * @return Int-muotoinen saldo.
     */
    public int toistuvienTilitapahtumienSaldo(Paivamaara loppu) {
        int saldo = 0;
        ArrayList<ToistuvaTilitapahtuma> toistuvatTapahtumat = tili.getToistuvatTilitapahtumat();
        for (ToistuvaTilitapahtuma tt : toistuvatTapahtumat) {
            ArrayList<YksittainenTilitapahtuma> tapahtumat = tt.konvertoiYksittaisiksiTapahtumiksi(new Paivamaara(1, 0, 1), loppu);
            for (YksittainenTilitapahtuma t : tapahtumat) {
                saldo += t.getSumma().getSummaInt();
            }
        }
        return saldo;
    }

    /**
     * Pyrkii määrittämään edellisen ajanjakson käyttäjälle loogisella tavalla.
     * Jos alkupvm on jonkin kuukauden ensimmäinen päivä ja loppupvm jonkin kuukauden
     * viimeinen päivä, on edellinen jakso sama määrä täysiä kuita välittömästi ennen
     * nykyistä ajanjaksoa, vaikka tämän seurauksena jaksojen välillä olisikin kuiden
     * pituuksien vaihteluista johtuva päivien määrän ero.
     * 
     * Muussa tapauksessa edellinen jakso on nykyistä jaksoa edeltävä jakso, jonka
     * pituus on yhtä monta päivää kuin nykyisessä jaksossa. Tällöin edellinen jakso
     * alkaa siten, että sen viimeinen päivä on nykyisen jakson ensimmäistä päivää
     * edeltävä päivä.
     * @param alkuPvm Nykyisen jakson ensimmäinen päivä.
     * @param loppuPvm Nykyisen jakson viimeinen päivä.
     * @return Päivämäärä-muotoinen esitys edellisen jakson alkupäivästä.
     */
    public Paivamaara maaritaEdellisenJaksonAlku(Paivamaara alkuPvm, Paivamaara loppuPvm) {     
        Paivamaara edellisenJaksonAlku = (Paivamaara) alkuPvm.clone();
        edellisenJaksonAlku.add(Calendar.DAY_OF_MONTH, -1);
        
        if(alkuPvm.get(Calendar.DAY_OF_MONTH) == 1
                && loppuPvm.get(Calendar.DAY_OF_MONTH) == loppuPvm.getActualMaximum(Calendar.DAY_OF_MONTH)){
            int vuosienErotus = alkuPvm.get(Calendar.YEAR ) - loppuPvm.get(Calendar.YEAR);
            edellisenJaksonAlku.add(Calendar.YEAR, vuosienErotus );
            
            int kuukausienErotus = alkuPvm.get(Calendar.MONTH) - loppuPvm.get(Calendar.MONTH);
            edellisenJaksonAlku.add(Calendar.MONTH, kuukausienErotus);
            
            edellisenJaksonAlku.set(Calendar.DAY_OF_MONTH, 1);
        }else{
            int paivienErotus = 0;
            Paivamaara loppuClone = (Paivamaara) loppuPvm.clone();
            while (loppuClone.after(alkuPvm)){
                loppuClone.add(Calendar.DAY_OF_MONTH, -1);
                paivienErotus--;
            }
            edellisenJaksonAlku.add(Calendar.DAY_OF_MONTH, paivienErotus);
        }
        return edellisenJaksonAlku;
    }
}
