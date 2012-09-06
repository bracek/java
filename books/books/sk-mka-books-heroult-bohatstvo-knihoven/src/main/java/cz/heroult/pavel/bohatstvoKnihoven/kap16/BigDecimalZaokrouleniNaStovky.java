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

public class BigDecimalZaokrouleniNaStovky {
  public static void main(String[] args) {
    BigDecimal bd = new BigDecimal("1234.56");
    System.out.println("bd = " + bd);
    bd = bd.movePointLeft(2);
    bd = bd.setScale(0, BigDecimal.ROUND_HALF_UP);
    bd = bd.movePointRight(2);
    System.out.println("bd = " + bd);
  }
}

