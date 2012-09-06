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

public class EliminaceDuplicit {
  public static void main(String[] args) {
    Collection<String> d = new ArrayList<String>();
    d.add("prvni");
    d.add("druhy");
    d.add("prvni");
    System.out.println("Duplicitni:   " + d);
    Collection<String> nd = new ArrayList<String>(
                 new HashSet<String>(d));
    System.out.println("NEduplicitni: " + nd);
  }
}
