package cz.heroult.pavel.java.book.Kap05.S108;

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

public class Switch2 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    switch (sc.nextLine().charAt(0)) {
      case 'a' :
      case 'b' :
      case 'c' :
        System.out.print("1");
        break;

      case 'd' :
        System.out.print("2");
        break;

      default  :
        System.out.print("3");
        break;
    }
  }
}
