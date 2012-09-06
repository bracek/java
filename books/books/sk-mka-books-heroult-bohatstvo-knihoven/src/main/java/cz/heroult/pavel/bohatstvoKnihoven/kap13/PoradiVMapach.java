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

public class PoradiVMapach {
  private static String[] pole = {"1", "6", "2", "5", "4", "3"};

  static void nastaveniATisk(Map<String, String> m) {
    for (int i = 0; i < pole.length;  i++) {
      m.put(pole[i], "A");
    }
    System.out.println(m.getClass().getName() + ":\t" + m);
  }

  public static void main(String[] args) {
    nastaveniATisk(new HashMap<String, String>());
    nastaveniATisk(new TreeMap<String, String>());
    nastaveniATisk(new LinkedHashMap<String, String>());
  }
}
