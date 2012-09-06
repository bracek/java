package cz.heroult.pavel.java.book.Kap06.S116;

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

public class Metody2 {
  
  static int secti() {
    Scanner sc = new Scanner(System.in);
    int a, b;

    a = sc.nextInt();
    b = sc.nextInt();
    return (a + b);
  }

  public static void main(String[] args) {    
    System.out.print("Zadej dve cela cisla: ");
    System.out.println(secti());
  }
}
