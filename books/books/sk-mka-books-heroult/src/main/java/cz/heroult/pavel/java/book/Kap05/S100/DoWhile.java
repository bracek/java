package cz.heroult.pavel.java.book.Kap05.S100;

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

public class DoWhile {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    char c;
    do {
      if ((c = sc.nextLine().charAt(0)) >= 'a')
        System.out.print(c);
    } while (c != 'z');

    System.out.println("\nCteni znaku bylo ukonceno.");
  }
}
