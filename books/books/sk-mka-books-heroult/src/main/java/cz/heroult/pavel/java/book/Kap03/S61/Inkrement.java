package cz.heroult.pavel.java.book.Kap03.S61;

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

public class Inkrement {
  public static void main(String[] args) {
    int i = 5, j = 1, k;

    i++;             // i bude 6
    System.out.println("i = " + i);

    j = ++i;         // j bude 7, i bude 7
    System.out.println("j = " + j + ", i = " + i);

    j = i++;         // j bude 7, i bude 8
    System.out.println("j = " + j + ", i = " + i);

    k = --j + 2;     // k bude 8, j bude 6, i bude 8
    System.out.println("k = " + k + ", j = " + j);
  }
}
