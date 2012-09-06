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

public class RandomGaussMeze {
  static int pocet = 1000000;

  public static void main(String[] args) {
    if (args.length == 1)
      pocet = Integer.parseInt(args[0]);

    Random r = new Random(0);
    double d;
    double max = Double.MIN_VALUE;
    double min = Double.MAX_VALUE;
    for (int i = 0;  i < pocet;  i++) {
      d = r.nextGaussian();
      if (d > max)
        max = d;
      if (d < min)
        min = d;  
    }

    System.out.println("Pokusu: " + pocet);
    System.out.println("Min: " + min);
    System.out.println("Max: +" + max);
  }
}

