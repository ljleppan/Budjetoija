package budjetoija.logiikka;

/**
 * Rahasummaa kuvaava luokka.
 */

public class Summa {
    /** Summan kuvaama rahamäärä sentteina. */
    int summa;
    
    /**
     * Luoka konstuktori.
     * @param summa rahamäärä sentteinä.
     */
    public Summa(int summa){
        this.summa = summa;
    }
    
    /**
     * Luokan konstruktori.
     * @param summa Rahamäärää kuvaava String.
     */
    public Summa(String summa){
        if (!setSummaString(summa)){
            this.summa = 0;
        }
    }
    
    public int getSummaInt(){
        return this.summa;
    }
    
    public void setSummaInt(int summa){
        this.summa = summa;
    }

    /**
     * Palauttaa summan kuvaaman rahasumma String-muotoisena.
     * @return rahasumman String-muotoisena.
     */
    public String getSummaString(){
        String summaStr = ""+this.summa;
        if (summaStr.length() > 2){
            String sentit = summaStr.substring(summaStr.length()-2);
            String eurot = summaStr.substring(0, summaStr.length()-2);
            return eurot+","+sentit;
        }
        if (summaStr.length() > 1){
            return "0,"+summaStr;
        }
        return "0,0" + summaStr;
    }

    /**
     * Vaihtaa rahasumman String-muotoisena annetun syötteen mukaiseksi.
     * @param   summa   String-muotoinen kuvaus summasta.
     */
    public boolean setSummaString(String summa){
        if (summa == null){
            return false;
        }
        summa = summa.replace('.', ',');
        try{
            if (kaksiTaiEnemmanDesimaalia(summa)) {
                return true;
            }
            else if (yksiDesimaali(summa)) {
                return true;
            }
            else if (eiDesimaaleja(summa)) {
                return true;
            }
        }
        catch (NumberFormatException e){
            return false;
        }
        return false;
    }
    
    @Override
    public String toString(){
        return getSummaString();
    }

    /**
     * Tarkistaa onko syöte muodoltaan kaksi tai enemmän desimaaleja sisältävä numeraali.
     * Hyväksyy syötteen joka on muotoa "0-1 miinus-merkki, n numeroa, pilkku, 2-n numeroa, EOL".
     * @param   summa   Tarkistettava String-muotoinen syöte.
     * @return  Syötteen täsmäävyys
     */
    private boolean kaksiTaiEnemmanDesimaalia(String summa) throws NumberFormatException {
        if (summa.matches("[-]?[0-9]*[,][0-9]{2,}$")) {
            String[] summaOsissa = summa.split(",");
            this.summa = Integer.parseInt(summaOsissa[0] + summaOsissa[1].subSequence(0, 2));
            return true;
        }
        return false;
    }
    
    /**
     * Tarkistaa onko syöte muodoltaan yhden desimaalin sisältävä numeraali.
     * Hyväksyy syötteen joka on muotoa "0-1 miinus-merkki, n numeroa, pilkku, numero, EOL".
     * @param   summa   Tarkistettava String-muotoinen syöte.
     * @return  Syötteen täsmäävyys
     */
    private boolean yksiDesimaali(String summa) throws NumberFormatException {
        if (summa.matches("[-]?[0-9]*[,][0-9]$")) {
            String[] summaOsissa = summa.split(",");
            this.summa = Integer.parseInt(summaOsissa[0] + summaOsissa[1] + "0");
            return true;
        }
        return false;
    }
    
    /**
     * Tarkistaa onko syöte muodoltaan desimaaleja sisältämätön numeraali.
     * Hyväksyy syötteen joka on muotoa "0-1 miinus-merkki, n numeroa, 0-1 pilkku, EOL".
     * @param   summa   Tarkistettava String-muotoinen syöte.
     * @return  Syötteen täsmäävyys
     */
    private boolean eiDesimaaleja(String summa) throws NumberFormatException {
        if (summa.matches("[-]?[0-9]+[,]?$")) {
            summa = summa.replaceAll("[,]", "");
            this.summa = Integer.parseInt(summa + "00");
            return true;
        }
        return false;
    }
    
    @Override
    public boolean equals(Object o){
        if (this == o){ return true;}
        if (!(o instanceof Summa)){ return false;}
        
        Summa s = (Summa)o;
        if (s.summa == this.summa){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.summa;
        return hash;
    }
    
}
