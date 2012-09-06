package cz.heroult.pavel.java.book.Kap04.S87;

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

import java.util.*;

public class NacitaniRealnychCisel {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    sc.useLocale(Locale.US); 
    
    System.out.print("Zadej 1. odvesnu: ");
    double d1 = sc.nextDouble();
    System.out.print("Zadej 2. odvesnu: ");
    double d2 = sc.nextDouble();
    double prepona = Math.sqrt(d1 * d1 + d2 * d2);
    System.out.println("Prepona je: " + prepona);
  }
}
