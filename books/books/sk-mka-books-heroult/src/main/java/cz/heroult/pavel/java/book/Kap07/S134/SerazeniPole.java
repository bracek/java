package cz.heroult.pavel.java.book.Kap07.S134;

/////////////////////////////////////////////////////////////////
//                                                             //
// Tento zdrojový kód je souèástí distribuce balíku programù,  //
//     poskytovaných jako doplòující informace ke knize        //
//                                                             //
//                  Uèebnice jazyka Java                       //
//                                                             //
//     Pøeètìte si, prosím, dùkladnì upozornìní v souboru      //
//                       CTI_ME.TXT                            //
//        který je nedílnou souèástí této distribuce           //
//                                                             //
//                 (c) Pavel Herout, 2000                      //
//                                                             //
/////////////////////////////////////////////////////////////////

import java.util.Arrays;

public class SerazeniPole {
  public static void main(String[] args) {
    int[] poleInt = { 1, 7, 5, 3 };
    System.out.println("pred: " + Arrays.toString(poleInt));
    Arrays.sort(poleInt);
    System.out.println("po:   " + Arrays.toString(poleInt));
  }
}
