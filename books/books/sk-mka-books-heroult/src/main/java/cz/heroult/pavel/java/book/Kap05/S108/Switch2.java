package cz.heroult.pavel.java.book.Kap05.S108;

/////////////////////////////////////////////////////////////////
//                                                             //
// Tento zdrojov� k�d je sou��st� distribuce bal�ku program�,  //
//     poskytovan�ch jako dopl�uj�c� informace ke knize        //
//                                                             //
//                  U�ebnice jazyka Java                       //
//                                                             //
//     P�e�t�te si, pros�m, d�kladn� upozorn�n� v souboru      // 
//                       CTI_ME.TXT                            //
//        kter� je ned�lnou sou��st� t�to distribuce           //
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
