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

public class BigDecimalPresnaCisla {
  public static void main(String[] args) {
    BigDecimal bd = new BigDecimal("0.1");
    BigDecimal plus = new BigDecimal("0.1");
    double d = 0.1;
    for (int i = 1;  i < 11;  i++) {
      System.out.print("bd = " + bd);
      System.out.println(", d = " + d);
      bd = bd.add(plus);
      d += 0.1;
    }
  }
}

