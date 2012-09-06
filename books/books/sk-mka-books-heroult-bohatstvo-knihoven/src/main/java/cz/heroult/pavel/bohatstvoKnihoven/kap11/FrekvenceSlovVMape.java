package cz.heroult.pavel.bohatstvoKnihoven.kap11;

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

public class FrekvenceSlovVMape {
  static void cetnost(Map<String, Integer> m) {
    String[] s = {"lesni", "vily", "vence", "vily", "a", "psi",
                  "z", "vily", "na", "ne", "vyli"};
    for (int i = 0;  i < s.length;  i++) {
        Integer cet = (Integer) m.get(s[i]);
        cet = (cet == null ? new Integer(1) :
                             new Integer(cet.intValue() + 1));
        m.put(s[i], cet);
    }

    System.out.print(m.getClass().getName());
    System.out.println(" -- nalezeno " + m.size() + " rozdilnych slov");
    System.out.println(m);
  }

  public static void main(String args[]) {
    cetnost(new HashMap<String, Integer>());
    cetnost(new TreeMap<String, Integer>());
  }
}
