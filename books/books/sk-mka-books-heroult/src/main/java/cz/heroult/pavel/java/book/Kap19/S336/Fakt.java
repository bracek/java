package cz.heroult.pavel.java.book.Kap19.S336;

/////////////////////////////////////////////////////////////////
//                                                             //
// Tento zdrojovı kód je souèástí distribuce balíku programù,  //
//     poskytovanıch jako doplòující informace ke knize        //
//                                                             //
//                  Uèebnice jazyka Java                       //
//                                                             //
//     Pøeètìte si, prosím, dùkladnì upozornìní v souboru      // 
//                       CTI_ME.TXT                            //
//        kterı je nedílnou souèástí této distribuce           //
//                                                             //
//                 (c) Pavel Herout, 2000                      // 
//                                                             //
/////////////////////////////////////////////////////////////////

public class Fakt {
  public static long faktRek(long n) {
    if (n > 1) {
      return n * faktRek(n - 1);
    }
    else {
      return 1;
    }
  }

  public static long faktCykl(long n) {
    long f = 1;
    for (; n > 1; n--)
      f *= n;
    return f;
  }
}
