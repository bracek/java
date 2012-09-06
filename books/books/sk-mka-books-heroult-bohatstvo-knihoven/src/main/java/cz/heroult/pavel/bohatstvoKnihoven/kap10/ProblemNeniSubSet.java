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

public class ProblemNeniSubSet {

    public static void main(String[] args) {
        TreeSet<Integer> ts = new TreeSet<Integer>();
        for (int i = 10; i < 16; i++) {
            ts.add(new Integer(i));
        }
        ts.remove(new Integer(11));
        System.out.println("Cela mnozina: " + ts);
        SortedSet<Integer> sub = ts.subSet(new Integer(11), new Integer(15));
        System.out.println("Podmnozina:   " + sub);

        System.out.println("Zmena cele mnoziny (-13 +11)");
        ts.remove(new Integer(13));
        ts.add(new Integer(11));
        System.out.println("Cela mnozina: " + ts);
        System.out.println("Podmnozina:   " + sub);

        System.out.println("Zmena podmnoziny (-12 +13)");
        sub.remove(new Integer(12));
        sub.add(new Integer(13));
        System.out.println("Cela mnozina: " + ts);
        System.out.println("Podmnozina:   " + sub);

//    sub.add(new Integer(33));        // mimo rozsah podmnoziny
    }
}

