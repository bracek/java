package cz.heroult.pavel.bohatstvoKnihoven.kap13;

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

public class PoradiVSeznamech {
  public static void main(String[] args) {
    String[] pole = {"1", "6", "2", "5", "4", "3"};
    HashSet<String> hs = new HashSet<String>(Arrays.asList(pole));
    TreeSet<String> ts = new TreeSet<String>(Arrays.asList(pole));
    LinkedHashSet<String> lhs = new LinkedHashSet<String>(Arrays.asList(pole));
    System.out.println("Original:      " + Arrays.toString(pole));
    System.out.println("HashSet:       " + hs);
    System.out.println("TreeSet:       " + ts);
    System.out.println("LinkedHashSet: " + lhs);
  }
}
