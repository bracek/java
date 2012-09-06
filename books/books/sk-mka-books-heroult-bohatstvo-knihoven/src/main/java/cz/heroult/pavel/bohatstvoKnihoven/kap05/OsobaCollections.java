package cz.heroult.pavel.bohatstvoKnihoven.kap05;


/////////////////////////////////////////////////////////////////
//                                                             //
// Tento zdrojový kód je souèástí distribuce balíku programù,  //
//     poskytovaných jako doplòující informace ke knize        //
//                                                             //
//                   Java -- bohatství knihoven                //
//                II. opravené a rozšíøené vydání              //
//                                                             //
//     Pøeètìte si, prosím, dùkladnì upozornìní v souboru      //
//                       Cti_me.txt                            //
//        který je nedílnou souèástí této distribuce           //
//                                                             //
//                 (c) Pavel Herout, 2006                      //
//                                                             //
/////////////////////////////////////////////////////////////////
import java.util.*;

class Osoba implements Comparable<Osoba> {

    int vyska;
    double vaha;
    String popis;

    Osoba(int vyska, double vaha, String popis) {
        this.vyska = vyska;
        this.vaha = vaha;
        this.popis = popis;
    }

    public int compareTo(Osoba os) {
        return this.vyska - os.vyska;
    }

    public String toString() {
        return "\r\nvy = " + vyska + ", va = " + vaha + ", " + popis;
    }
}

class KomparatorOsobyPodleVysky implements Comparator<Osoba> {

    public int compare(Osoba os1, Osoba os2) {
        return os1.vyska - os2.vyska;
    }
}

class KomparatorOsobyPodleVahy implements Comparator<Osoba> {

    public int compare(Osoba os1, Osoba os2) {
        return (int) (os1.vaha - os2.vaha);
    }
}

public class OsobaCollections {

    public static void main(String[] args) {
        List<Osoba> sez = new ArrayList<Osoba>();
        sez.add(new Osoba(186, 82.5, "muz"));
        sez.add(new Osoba(172, 63.0, "zena"));
        sez.add(new Osoba(105, 26.1, "dite"));
        sez.add(new Osoba(116, 80.5, "obezni trpaslik"));

        Collections.sort(sez, new KomparatorOsobyPodleVahy());
        System.out.println("Abs. razeni podle vahy: " + sez);

        Collections.reverse(sez);
        System.out.println("Podle vahy sestupne: " + sez);

        Collections.shuffle(sez);
        System.out.println("Zamichano: " + sez);

        System.out.println("Nejvyssi:" + Collections.max(sez));
        System.out.println("Nejlehci:" + Collections.min(sez,
                new KomparatorOsobyPodleVahy()));

        Osoba robot = new Osoba(180, 75.0, "robot");
        Collections.fill(sez, robot);
        System.out.println("Vyplneno: " + sez);
        int pocet = Collections.frequency(sez, robot);
        System.out.println("Pocet robotu: " + pocet);
    }
}
