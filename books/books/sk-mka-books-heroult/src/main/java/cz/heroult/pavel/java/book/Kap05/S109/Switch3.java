package cz.heroult.pavel.java.book.Kap05.S109;

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

import java.util.Scanner;

public class Switch3 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    switch (sc.nextLine().charAt(0))  {
      default :
        System.out.println("Nestiskl jsi ani '1' ani '2'");
        break;

      case '1' :
        System.out.println("Stiskl jsi '1'");
        break;

      case '2' :
        System.out.println("Stiskl jsi '2'");
        break;
     }
  }
}
