package cz.heroult.pavel.java.book.Kap05.S095;

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

public class Ternarni1 {
  public static void main(String[] args) {
    int i, k, j = 2;

    i = (j == 2) ? 1 : 3;   // i bude 1
    System.out.println("i = " + i);

    k = (i > j) ? i : j;    // k bude maximum z i a j, tedy 2
    System.out.println("k = " + k);
  }
}
