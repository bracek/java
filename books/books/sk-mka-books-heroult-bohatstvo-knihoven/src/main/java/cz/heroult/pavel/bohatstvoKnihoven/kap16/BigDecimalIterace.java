package cz.heroult.pavel.bohatstvoKnihoven.kap16;

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

import java.math.*;

public class BigDecimalIterace {
  public static void main(String[] args) {
    double d = 0;
    System.out.println("Pi = " + Math.PI);
    long z = System.currentTimeMillis();
    for (int i = 1;  i <= 10000000;  i++) {
      d += Math.PI;
    }
    long k = System.currentTimeMillis();
    System.out.println("d  = " + d);
    System.out.println("cas = " + (k - z) + " [msec]");

    BigDecimal Pi = new BigDecimal("3.141592653589793");
    BigDecimal bd = new BigDecimal(BigInteger.ZERO, 0);
    System.out.println("Pi = " + Math.PI);
    z = System.currentTimeMillis();
    for (int i = 1;  i <= 10000000;  i++) {
      bd = bd.add(Pi);
    }
    k = System.currentTimeMillis();
    System.out.println("bd = " + bd);
    System.out.println("cas = " + (k - z) + " [msec]");
  }
}

