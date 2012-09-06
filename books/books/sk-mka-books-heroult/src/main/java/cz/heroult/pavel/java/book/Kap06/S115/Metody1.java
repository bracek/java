package cz.heroult.pavel.java.book.Kap06.S115;

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

public class Metody1 {
  static int max(int a, int b) {
    if (a > b)
      return (a);
    else
      return (b);
  }

  public static void main(String[] args) {
    int i = 1, j = 2;
    System.out.println(max(i, j));
    System.out.println(max(i + 3, j));
    System.out.println(max(i, 5));
    System.out.println(max(10 * i, j - 15));
  }
}
