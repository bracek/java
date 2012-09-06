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
import java.math.*;

class Gauss {
  static Random r = new Random();
  static double gauss(double stred, double sigma) {
    double sum = 0.0;
    for (int i = 1;  i <= 12;  i++) {
      sum += r.nextDouble();
    }
    return (sigma * (sum - 6.0) + stred);
  }
}

public class RandomObecnyGauss {
  static int pocet = 10;
  static double stred;
  static double sigma;
  
  public static void main(String[] args) {
    if (args.length == 0  ||  args.length == 1) {
      System.out.println("Pouziti: java RandomObecnyGauss "
                         + "stredniHodnota odchylka");
       return;
    }
     
    stred = Double.parseDouble(args[0]);
    sigma = Double.parseDouble(args[1]);

    double[] d = new double[pocet];
    for (int i = 0;  i < d.length;  i++)
      d[i] = Gauss.gauss(stred, sigma);

    Arrays.sort(d);

    for (int i = 0;  i < d.length;  i++) {
      BigDecimal bd = new BigDecimal(d[i]);
      bd = bd.movePointRight(2); 
      bd = bd.setScale(0, BigDecimal.ROUND_HALF_UP); 
      bd = bd.movePointLeft(2); 
      if (bd.signum() == +1)
        System.out.print("+" + bd + " ");
      else  
        System.out.print(bd + " ");  
    }
  }
}
