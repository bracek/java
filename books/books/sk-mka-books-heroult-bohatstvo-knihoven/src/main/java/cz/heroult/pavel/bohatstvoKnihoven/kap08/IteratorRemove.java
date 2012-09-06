package cz.heroult.pavel.bohatstvoKnihoven.kap08;

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

class HruskaX {

    private int cena;

    HruskaX(int cena) {
        this.cena = cena;
    }

    public String toString() {
        return "" + cena;
    }

    public int getCena() {
        return cena;
    }
}

public class IteratorRemove {

    public static void main(String[] args) {
        ArrayList<HruskaX> kosHrusek = new ArrayList<HruskaX>();
        for (int i = 0; i < 10; i++) {
            kosHrusek.add(new HruskaX(i + 20));
        }

        for (Iterator<HruskaX> it = kosHrusek.iterator(); it.hasNext();) {
            HruskaX h = it.next();
            System.out.print(h + ", ");
            if (h.getCena() % 2 == 0) {
                it.remove();
            }
        }
        System.out.println();

        for (Iterator<HruskaX> it = kosHrusek.iterator(); it.hasNext();) {
            System.out.print(it.next() + ", ");
        }
        System.out.println();
    }
}
 
