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

public class RandomMeze {
  public static void main(String[] args) {
    Random r = new Random(123);

    for (int i = 0;  i < 15;  i++)
      System.out.print(r.nextInt(10) + ", ");
    System.out.println();

    for (int i = 0;  i < 5;  i++)
      System.out.print(r.nextFloat() + ", ");
    System.out.println();

    for (int i = 0;  i < 5;  i++)
      System.out.print(r.nextBoolean() + ", ");
    System.out.println();
  }
}

