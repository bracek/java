package cz.heroult.pavel.java.book.Kap06.S118;

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

public class Metody5 {
  static double secti(int a, double b) {
    return a + b;
  }

  static int secti(int a, int b) {  // pøetížená metoda secti()
    return a + b;
  }

  public static void main(String[] args) { 
    System.out.println(secti(1, 3.14));
    System.out.println(secti(2, 5));
  }
}
