package cz.heroult.pavel.bohatstvoKnihoven.kap17;

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

public class RandomZakladniPouziti {
  public static void main(String[] args) {
    Random r1 = new Random();
    Random r2 = new Random();
    Random r3 = new Random(123);
    Random r4 = new Random(123);
    System.out.println("r1 = " + r1.nextInt());
    System.out.println("r2 = " + r2.nextInt());
    System.out.println("r3 = " + r3.nextInt());
    System.out.println("r4 = " + r4.nextInt());
  }
}

