package cz.heroult.pavel.java.book.Kap10.S190;

/////////////////////////////////////////////////////////////////
//                                                             //
// Tento zdrojový kód je souèástí distribuce balíku programù,  //
//     poskytovaných jako doplòující informace ke knize        //
//                                                             //
//                  Uèebnice jazyka Java                       //
//                                                             //
//     Pøeètìte si, prosím, dùkladnì upozornìní v souboru      // 
//                       CTI_ME.TXT                            //
//        který je nedílnou souèástí této distribuce           //
//                                                             //
//                 (c) Pavel Herout, 2000                      // 
//                                                             //
/////////////////////////////////////////////////////////////////
class Datum {

    public int den,  mesic,  rok;

    public Datum(int den, int mesic, int rok) {
        this.den = den;
        this.mesic = mesic;
        this.rok = rok;
    }

    public Datum(Datum d) {
        this(d.den, d.mesic, d.rok);
    }

    public String toString() {
        return "" + den + "." + mesic + "." + rok;
    }
}

class Zamestnanec {

    public String jmeno;
    public Datum narozeni,  nastup;

    public Zamestnanec(String jmeno, Datum narozeni, Datum nastup) {
        this.jmeno = new String(jmeno);
        this.narozeni = new Datum(narozeni);
        this.nastup = new Datum(nastup);
    }

    public String toString() {
        String s;
        s = jmeno + ", narozen: " + narozeni.toString();
        s = s + "\nnastoupil: " + nastup.toString();
        return s;
    }
}

public class Kompozice {

    public static void main(String[] argv) {
        Datum narozeni = new Datum(21, 5, 1960);
//    Datum nastup = new Datum(1, 10, 1990);
//    Zamestnanec z = new Zamestnanec("Josef Novak", narozeni, nastup);
        Zamestnanec z = new Zamestnanec("Josef Novak", narozeni, new Datum(1, 10, 1990));
        System.out.println(z.toString());
    }
}

