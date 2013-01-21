package budjetoija.logiikka;


public class Summa {
    int summa;
    
    public Summa(int summa){
        this.summa = summa;
    }
    
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
    
    public boolean setSummaString(String summa){
        if (summa == null){
            return false;
        }
        summa = summa.replace('.', ',');
        if (summa.matches("[0-9]*[,][0-9]{2,}$")){
            String[] summaOsissa = summa.split(",");
            this.summa = Integer.parseInt(summaOsissa[0] + summaOsissa[1].subSequence(0, 2));
            return true;
        }
        if (summa.matches("[0-9]*[,][0-9]$")){
            String[] summaOsissa = summa.split(",");
            this.summa = Integer.parseInt(summaOsissa[0] + summaOsissa[1] + "0");
            return true;
        }
        if (summa.matches("[0-9]+[,]?$")){
            summa = summa.replaceAll("[,]", "");
            this.summa = Integer.parseInt(summa + "00");
            return true;
        }
        return false;
    }
    
    @Override
    public String toString(){
        return getSummaString();
    }
    
}