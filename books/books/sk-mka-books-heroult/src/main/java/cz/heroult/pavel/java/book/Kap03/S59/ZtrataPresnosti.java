package cz.heroult.pavel.java.book.Kap03.S59;

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

public class ZtrataPresnosti {
  public static void main(String[] args) {
    int j, i = 1234567890;
    float f;
    double promile;

    f = i;
    j = (int) f;
    promile = ((double) (j - i) / (double) i) * 1000.0;
    System.out.println("i = " + i + "\nj = " + j);
    System.out.println("promile = " + promile);
  }
}
