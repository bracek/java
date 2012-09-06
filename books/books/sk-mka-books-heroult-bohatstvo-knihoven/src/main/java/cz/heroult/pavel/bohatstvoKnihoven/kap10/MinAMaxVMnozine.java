package cz.heroult.pavel.bohatstvoKnihoven.kap10;

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

class Hruska implements Comparable<Hruska> {

    int cena;

    Hruska(int cena) {
        this.cena = cena;
    }

    @Override
    public String toString() {
        return "" + cena;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof Hruska == false) {
            return false;
        }
        boolean stejnaCena = (cena == ((Hruska) o).cena);
        return stejnaCena;
    }

    @Override
    public int hashCode() {
        return cena;
    }

    public int compareTo(Hruska h) {
        if (cena > h.cena) {
            return (+1);
        } else if (cena == h.cena) {
            return (0);
        } else {
            return (-1);
        }
    }
}

public class MinAMaxVMnozine {

    static void praceSHruskami(Set<Hruska> s) {
        for (int i = 20; i < 30; i++) {
            s.add(new Hruska(i));
        }
        System.out.println(s.getClass().getName());
        System.out.println("Cely kosik: " + s);
        System.out.println("Nejdrazsi hruska: " +
                Collections.max(s));
        System.out.println("Nejlevnejsi hruska: " +
                Collections.min(s));
    }

    public static void main(String[] args) {
        praceSHruskami(new HashSet<Hruska>());
        praceSHruskami(new TreeSet<Hruska>());
    }
}
