package cz.heroult.pavel.bohatstvoKnihoven.kap04;


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

public class ProblemSubList {

    public static void main(String[] args) {
        ArrayList<Integer> ar = new ArrayList<Integer>();
        for (int i = 0; i < 5; i++) {
            ar.add(new Integer(i + 10));
        }
        System.out.println("Cely seznam: " + ar);
        List<Integer> sl = ar.subList(2, 5);
        System.out.println("Podseznam:   " + sl);

        System.out.println("Ubrani prvku[2] z celeho seznamu");
        ar.remove(2);                  // "poskodi" sublist
//    ar.add(2, new Integer(77));  // "poskodi" sublist

        sl.remove(0);
        sl.add(2, new Integer(77));

        System.out.println("Cely seznam: " + ar);
        System.out.println("Podseznam:   " + sl);
    }
}

