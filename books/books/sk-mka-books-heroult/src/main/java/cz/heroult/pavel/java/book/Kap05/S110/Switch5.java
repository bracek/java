package cz.heroult.pavel.java.book.Kap05.S110;

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

public class Switch5 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    char c = 'a';

    while ((c = sc.nextLine().charAt(0)) != '*') {
      switch (c) {
        case ' '  :
        case '\t' :
          System.out.print('#');
          break;

        default:
          System.out.print(c);
          break;
      }
    }
  }
}
