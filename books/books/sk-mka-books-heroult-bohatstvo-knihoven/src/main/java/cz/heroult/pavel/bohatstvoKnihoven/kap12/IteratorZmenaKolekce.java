package cz.heroult.pavel.bohatstvoKnihoven.kap12;

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

public class IteratorZmenaKolekce {
  public static void main(String[] args) {
    ArrayList<Integer> kont = new ArrayList<Integer>();
    for (int i = 0;  i < 10;  i++) {
      kont.add(new Integer(i));
    }

    Iterator<Integer> it = kont.iterator();
    System.out.println(it.next());
    kont.add(new Integer(20));
    // zde vyhodi vyjimku
    System.out.println(it.next());
  }
}
