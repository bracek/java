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

public class BigDecimalPosunyDesetinneCarky {
  public static void main(String[] args) {
    BigDecimal bd, bdl5;
    bd = new BigDecimal(BigInteger.ONE, 5);
    System.out.println("bd      = " + bd);
    bdl5 = bd.movePointLeft(5);
    System.out.println("bd <- 5 = " + bdl5);
    System.out.println("bdl5 sc = " + bdl5.scale());
    System.out.println("bd -> 5 = " + bd.movePointRight(5));
    System.out.println("bdr5 sc = " + bd.movePointRight(5).scale());
  }
}

