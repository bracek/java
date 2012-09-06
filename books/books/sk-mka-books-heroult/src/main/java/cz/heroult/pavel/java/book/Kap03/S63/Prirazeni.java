package cz.heroult.pavel.java.book.Kap03.S63;

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

public class Prirazeni {
  public static void main(String[] args) {
    int i = 4, j = 3;

    j += i;         //    j bude 7
    System.out.println("j = " + j);

    j /= --i;       //    j bude 2, i bude 3
    System.out.println("j = " + j + ", i = " + i);

    j *= i - 2;     //    j = j * (i - 2) = 2
    System.out.println("j = " + j + ", i = " + i);
  }
}
