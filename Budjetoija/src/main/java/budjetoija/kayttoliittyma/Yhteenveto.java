package budjetoija.kayttoliittyma;

import budjetoija.logiikka.Paivamaara;
import budjetoija.logiikka.Summa;
import budjetoija.logiikka.Tili;
import budjetoija.logiikka.Tilitapahtuma;
import budjetoija.logiikka.ToistuvaTilitapahtuma;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Valmistaa tekstimuotoisia yhteenvetoja tilin tapahtumista.
 */

public class Yhteenveto {
    Tili tili;
    
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
     * Palauttaa String-muotoisen yhteenvedon tilin tapahtumista aikavälillä.
     * @param alku  Aikavälin alku
     * @param loppu Aikavälin loppu
     * @return  String-muotoinen yhteenveto
     */
    public String yhteenvetoAikavalilta(Paivamaara alku, Paivamaara loppu){
        Summa saldoAlussa = laskeSaldo(alku);
        Summa saldoLopussa = laskeSaldo(loppu);
        Summa saldonMuutos = new Summa(saldoLopussa.getSummaInt() - saldoAlussa.getSummaInt());
        
        return String.format("%s\n"
                + "%s.%s.%s - %s.%s.%s\n"
                + "%s  %s \n"
                + "%s %s \n"
                + "%s %s",
                tili.getNimi(),
                ""+alku.get(Calendar.YEAR), ""+alku.get(Calendar.MONTH), ""+alku.get(Calendar.DAY_OF_MONTH),
                ""+loppu.get(Calendar.YEAR), ""+loppu.get(Calendar.MONTH), ""+loppu.get(Calendar.DAY_OF_MONTH),
                "Saldo tarkastelujakson alussa:", saldoAlussa,
                "Saldo tarkastelujakson lopulla:", saldoLopussa,
                "Muutos tarkastelujakson aikana:", saldonMuutos);
    }

    /**
     * Laskee tilin saldon tietyltä aikaväliltä. Palaute Summa-oliona.
     * @param alku  Aikavälin alku
     * @param loppu Aikavälin loppu
     * @return  Summa-muotoinen saldo
     */
    public Summa laskeSaldo(Paivamaara alku, Paivamaara loppu) {
        ArrayList<Tilitapahtuma> tapahtumat = tili.getKaikkiTilitapahtumatAjalta(alku, loppu);
        int muutos = 0;
        for (Tilitapahtuma t : tapahtumat){
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
        ArrayList<Tilitapahtuma> tapahtumat = tili.getTilitapahtumat();
        for (Tilitapahtuma t : tapahtumat){
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
            ArrayList<Tilitapahtuma> tapahtumat = tt.konvertoiYksittaisiksiTapahtumiksi(loppu);
            for (Tilitapahtuma t : tapahtumat){
                saldo += t.getSumma().getSummaInt();
            }
        }
        return saldo;
    }
    
}
