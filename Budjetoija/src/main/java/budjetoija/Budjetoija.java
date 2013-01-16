package budjetoija;

import budjetoija.util.Tiedostonkasittelija;

public class Budjetoija {

    public static void main(String[] args) {
        System.out.println("Budjetoija");
        System.out.println("Versio 20130116-1212");
        
        Tiedostonkasittelija t = new Tiedostonkasittelija("t.txt");
        t.tallennaTiedosto("t.txt", "testi");
        System.out.println(t.lueTiedosto("t.txt"));
    }
}
