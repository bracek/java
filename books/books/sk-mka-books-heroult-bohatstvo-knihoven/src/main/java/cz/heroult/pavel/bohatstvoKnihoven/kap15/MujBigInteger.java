package cz.heroult.pavel.bohatstvoKnihoven.kap15;

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

public class MujBigInteger {
  public static void main(String[] args) {
    BigInteger bi = new BigInteger("-123456");
    System.out.println(bi);
    System.out.println(bi.toString(16));
    System.out.println(bi.abs());
    System.out.println(bi.add(new BigInteger("123455")));

    String s = "1";
    for (int i = 1;  i < 50;  i++) {
      s += "0";
    }

    bi = new BigInteger(s);
    System.out.println(bi);
    System.out.println(bi.bitLength());
    bi = new BigInteger("13");
    System.out.println(bi);
    System.out.println(bi.bitLength());
    System.out.println(bi.getLowestSetBit());
    System.out.println(bi.isProbablePrime(10));
  }
}
