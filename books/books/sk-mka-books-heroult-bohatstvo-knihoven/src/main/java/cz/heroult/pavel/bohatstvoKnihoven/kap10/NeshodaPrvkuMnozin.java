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

public class NeshodaPrvkuMnozin {

    public static void main(String[] args) {
        Set<String> m1 = new HashSet<String>();
        Collections.addAll(m1, "1", "2", "3", "4");
        Set<String> m2 = new HashSet<String>();
        Collections.addAll(m2, "5", "6", "7");
        Set<String> m3 = new HashSet<String>();
        Collections.addAll(m3, "4", "5");

        System.out.println(m1 + " a " + m2 + " jsou rozdilne: " + Collections.disjoint(m1, m2));
        System.out.println(m1 + " a " + m3 + " jsou rozdilne: " + Collections.disjoint(m1, m3));
    }
}
