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

class Exp {
  static Random r = new Random();
  static double exp(double lambda) {
    double pom;
    do {
      pom = r.nextDouble();
    } while (pom == 0.0);
    return ((-1.0 / lambda) * Math.log(pom));
  }
}

public class RandomExponencialni {
  static int pocet = 21;

  public static void main(String[] args) {
    if (args.length == 1)
      pocet = Integer.parseInt(args[0]);

    double[] d = new double[100000];
    for (int i = 0;  i < d.length;  i++)
      d[i] = Exp.exp(10.0);

    Arrays.sort(d);
    double max = Math.max(Math.abs(d[0]), d[d.length - 1]);

    for (int i = 0;  i < d.length;  i++)
      d[i] /= max;

    double[] h = new double[pocet + 1];
    for (int i = 0;  i < d.length;  i++) {
      int j = (int) (d[i] * pocet);
      if (j >= pocet)
        j = pocet;
      h[j]++;
    }

    max = 0.0;
    for (int i = 0;  i < h.length;  i++) {
      if (max < h[i])
        max = h[i];
    }

    for (int i = 0;  i < h.length;  i++) {
      h[i] /= max;
      h[i] *= 60;
    }

    for (int i = 0;  i < h.length;  i++) {
      for (int j = 0;  j < h[i];  j++) {
        System.out.print("*");
      }
      System.out.println();
    }
  }
}
